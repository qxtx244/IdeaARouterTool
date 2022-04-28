package com.qxtx.idea.gradle.plugin.aroutertool.impl

import com.qxtx.idea.gradle.plugin.aroutertool.AndroidTool
import com.qxtx.idea.gradle.plugin.aroutertool.Tools
import com.qxtx.idea.gradle.plugin.aroutertool.extension.DocConfigExtension
import org.gradle.api.Project

/**
 * @author QXTX-WIN
 * createDate 2022/3/31 10:25
 * Description ARouter的文档增强实现，收集project及其所有子project中ARouter产生的doc文档。
 */
class DocConfigImpl extends Base {

    def getDocExtension() {
        return config.docConfig
    }

    @Override
    void start(Project target) {
        def destUri = "ARouterDocs"
        //每次先清理之前输出的结果
        def oldResultFile = new File("${target.projectDir.absolutePath}/$destUri")
        if (oldResultFile.exists()) {
            oldResultFile.listFiles().each { it.delete() }
            oldResultFile.delete()
        }

        //在每个project完成build后，寻找ARouter的doc
        target.rootProject.allprojects {
            project.gradle.buildFinished {
                def projPath = project.path
//                println "$TAG project[$projName] build finished, we can start doc config..."
                DocConfigExtension extension = getDocExtension()
                if (extension == null) {
                    println "$TAG doc功能不可用"
                    return
                }
                def enable = extension.docEnable
                if (!enable) {
                    println "$TAG project[$projPath] 禁止收集ARouter doc"
                    return
                }

                //如果不是android module，跳过
                if (!Tools.isAndroidModule(project)) return

                def includes = extension.includes
                if (!includes.contains(projPath)) {
                    def isMatch = false
                    includes.each {
                        def includeProj = project(it)
                        if (includeProj.allprojects.find { it.path == projPath } != null) isMatch = true
                    }
                    if (!isMatch) return
                }

                def excludes = extension.excludes
                if (excludes != null && excludes.contains(projPath)) {
                    //println "$TAG 跳过project[$projName]"
                    return
                }

                // def destDir = extension.destDir
                // if (destDir == null || destDir.isEmpty()) destDir = "${target.projectDir.abstractPath}/ARouterDocs"
                def destDir = target.projectDir.absolutePath
                collect(project, "$destDir/$destUri")
            }
        }
    }

    def collect(def project, def destDir) {
        //检查ARouter是否已经生成doc
        def outDir = "${project.buildDir.absolutePath}/generated/ap_generated_sources"
        //laiyx 2022/4/28 13:36 如果是存在flavor的project，那么目标目录并不是debug，而是flavor + Debug
        new File(outDir).with {
            if (!it.exists()) return
            listFiles().each {
                def uri = 'out/com/alibaba/android/arouter/docs'
                def docDirectory = new File("${it.absolutePath}/$uri")
                if (!docDirectory.isDirectory()) return

                docDirectory.listFiles().each {srcFile ->
                    srcFile.withInputStream { is ->
                        def destFile = new File("$destDir\\${srcFile.name}")
                        destFile.parentFile.mkdirs()
                        destFile.withOutputStream { os ->
                            os << is
                        }
                    }
                    println "$TAG doc收集：${srcFile.absolutePath}"
                }
            }
        }
    }
}