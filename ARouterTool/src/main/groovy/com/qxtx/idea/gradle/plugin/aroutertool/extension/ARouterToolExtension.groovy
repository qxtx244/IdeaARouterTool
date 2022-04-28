package com.qxtx.idea.gradle.plugin.aroutertool.extension

import org.gradle.api.Action

/**
 * @author QXTX-WORK
 * <p>
 * <b>Create Date</b><p> 2022/3/31 14:53
 * <p>
 * <b>Description</b>
 * <pre>
 *     gradle插件配置，ARouter扩展
 * @see com.qxtx.idea.gradle.plugin.aroutertool.ARouterTool
 * </pre>
 */
class ARouterToolExtension {

    DocConfigExtension docConfig = null

    def docConfig(Action<DocConfigExtension> action) {
        docConfig = new DocConfigExtension()
        action.execute(docConfig)
    }
}