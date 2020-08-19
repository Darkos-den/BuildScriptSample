package com.company.projectName.android.di

import com.company.projectName.android.clean.domain.core.MessageQuery

@ExperimentalStdlibApi
interface MessageQueryProvider {
    val messageQuery: MessageQuery
}