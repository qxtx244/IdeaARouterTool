package com.qxtx.idea.gradle.plugin.aroutertool.extension

/**
 * Doc功能扩展
 */
class DocConfigExtension {

    /** 功能开关 */
    boolean docEnable = false

    /**
     * destDir 目标doc文档目录的绝对路径，默认为project目录/ARouterDocs
     * @deprecated 忽略此配置，因为这可能会增加使用难度。总是设置为project的根路径。
     */
    @Deprecated
    String destDir = null

    /**
     * 指定收集doc的project列表，列表元素为project的path。
     * 如果该列表为空，则功能不生效。
     * 如果指定一个父project，则也将包括其子project。
     */
    ArrayList<String> includes = new ArrayList<>()

    /** 禁止收集doc的project列表，列表元素为project的path */
    ArrayList<String> excludes = new ArrayList<>()

    def docEnable(boolean b) {
        this.docEnable = b
    }

    def destDir(String dir) {
        this.destDir = dir
    }

    def includes(String... modulePaths) {
        this.includes = modulePaths
    }

    def excludes(String... modulePaths) {
        this.excludes = modulePaths
    }
}