package com.company.projectName.android.home

import com.company.projectName.android.base.mvu.Cmd
import com.company.projectName.android.base.mvu.Msg

sealed class HomeCmd : Cmd() {
    object InvalidateData : HomeCmd()
}

sealed class HomeMsg : Msg() {
    object InvalidateClick : HomeMsg()
    class NewDataReceived(
        val data: String
    ): HomeMsg()
}