package com.company.projectName.android.di

@ExperimentalStdlibApi
class Component(
    parentContext: MessageQueryProvider
) {

    val lifecycleQuery = parentContext.messageQuery
//    val program = ComponentDI.providerProgram()

    init {

    }
}