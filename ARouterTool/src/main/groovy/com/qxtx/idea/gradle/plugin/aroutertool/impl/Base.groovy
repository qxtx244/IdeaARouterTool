package com.qxtx.idea.gradle.plugin.aroutertool.impl

import com.qxtx.idea.gradle.plugin.aroutertool.Consts
import com.qxtx.idea.gradle.plugin.aroutertool.extension.ARouterToolExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class Base implements Plugin<Project> {

    protected def EXT_NAME = Consts.EXTENSION_NAME
    protected def TAG = Consts.TAG
    protected ARouterToolExtension config = null

    @Override
    void apply(Project target) {
        def extension = target.getExtensions()
        config = extension.findByName(Consts.EXTENSION_NAME)
        if (config == null) {
            config = extension.create(Consts.EXTENSION_NAME, ARouterToolExtension)
        }
        start(target)
    }

    abstract void start(Project target)
}