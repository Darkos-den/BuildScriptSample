package com.company.projectName.android.home

import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.core.Program

interface MVUProvider {
    @ExperimentalStdlibApi
    fun getProgram(): Program

    @ExperimentalStdlibApi
    fun getMessageQuery(): MessageQuery
}