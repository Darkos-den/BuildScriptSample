package com.company.projectName.android.di

import com.company.projectName.android.clean.domain.core.Program

@ExperimentalStdlibApi
object ComponentDI {

    private var program: Program? = null

//    @Deprecated("Синглтон тут не подходит")
//    fun providerProgram(): Program {
//        return program ?: Program(
//            reducer =,
//            initialState =,
//            component =,
//            effectHandler =,
//            messageQuery =
//        ).also {
//            program = it
//        }
//    }
}