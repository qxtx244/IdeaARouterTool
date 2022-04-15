package com.qxtx.idea.gradle.plugin.aroutertool

import com.qxtx.idea.gradle.plugin.aroutertool.impl.Base
import com.qxtx.idea.gradle.plugin.aroutertool.impl.DocConfigImpl
import org.gradle.api.Project

/**
 * @author QXTX-WORK
 * <p>
 * <b>Create Date</b><p> 2022/3/31 14:58
 * <p>
 * <b>Description</b>
 * <pre>
 *  · ARouter增强插件，如收集ARouter生成的doc文档等。
 *  · 这个插件是针对父module设计的，因此，将其配置到父module的build.gradle中，以作用于它的子module。
 *      如，将其配置到根build.gradle中，则最大作用范围为所有module。
 *  · 支持的ARouter版本: 1.5.2
 * </pre>
 */
class ARouterTool extends Base {

    @Override
    void start(Project target) {
        target.afterEvaluate {
            if (config == null) {
                println 'config is null!'
                return
            }
            if (config.docConfig == null) {
                println("$TAG 未被配置。请修改后，重新build工程。参考示例：\n" +
                        "$EXT_NAME {\n" +
                        "\tdocConfig {\n" +
                        "\t\t//功能开关\n" +
                        "\t\tdocEnable true\n" +
                        "\t\t//可选配置，跳过指定的project，如“demo”和“modA”。\n" +
                        "\t\texcludes 'demo', 'modA'\n" +
                        "\t}\n" +
                        "}")
                return
            }

            new DocConfigImpl().apply(target)
        }
    }
}