package com.company.projectName.android.home

import com.company.projectName.android.base.mvu.Cmd
import com.company.projectName.android.base.mvu.Msg
import com.company.projectName.android.base.mvu.ScreenState

sealed class HomeCmd : Cmd() {
    object InvalidateData : HomeCmd()
}

sealed class HomeMsg : Msg() {
    object InvalidateClick : HomeMsg()
    class NewDataReceived(
        val data: String
    ): HomeMsg()
}

sealed class HomeScreenState: ScreenState(){
    object Initial : HomeScreenState()

    class Data(
        val data: String
    ) : HomeScreenState()

    class Progress(
        val oldState: ScreenState
    ): HomeScreenState()

    class Invalidatable(
        val oldState: ScreenState
    ) : HomeScreenState()
}