package com.company.projectName.android.home

import com.company.projectName.android.base.mvu.*

sealed class HomeCmd : Cmd() {
    object InvalidateData : HomeCmd()
}

sealed class HomeMsg : Msg() {
    object InvalidateClick : HomeMsg()
    class NewDataReceived(
        val data: String
    ) : HomeMsg()
}

sealed class HomeScreenState : ScreenState() {

    open fun processInvalidateClick(): ScreenCmdData {
        return ScreenCmdData(
            state = Progress(this),
            cmd = HomeCmd.InvalidateData
        )
    }

    open fun processNewDataReceived(
        state: ScreenState,
        msg: HomeMsg.NewDataReceived
    ): ScreenCmdData {
        return ScreenCmdData(
            state = Invalidatable(this),
            cmd = None()
        )
    }

    object Initial : HomeScreenState()

    class Data(
        val data: String
    ) : HomeScreenState()

    class Progress(
        val oldState: ScreenState
    ) : HomeScreenState() {

        override fun processNewDataReceived(
            state: ScreenState,
            msg: HomeMsg.NewDataReceived
        ): ScreenCmdData {
            return ScreenCmdData(
                state = Invalidatable(oldState),
                cmd = HomeCmd.InvalidateData
            )
        }
    }

    class Invalidatable(
        val oldState: ScreenState
    ) : HomeScreenState() {

        override fun processInvalidateClick(): ScreenCmdData {
            return ScreenCmdData(
                state = Progress(oldState),
                cmd = HomeCmd.InvalidateData
            )
        }
    }
}