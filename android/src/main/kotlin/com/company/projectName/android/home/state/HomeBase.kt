package com.company.projectName.android.home.state

import com.company.projectName.android.base.BaseState
import com.company.projectName.android.base.StateBehavior
import com.company.projectName.android.base.StateData
import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.home.HomeCmd
import com.company.projectName.android.home.HomeMsg

abstract class HomeBase<Data : StateData>(
    data: Data,
    behavior: IBehavior = BaseBehavior()
) : BaseState<Data, HomeBase.IBehavior>(
    data = data,
    behavior = behavior
) {

    interface IBehavior : StateBehavior {
        fun processInvalidateClick(currentState: HomeBase<*>): ScreenCmdData
        fun processNewDataReceived(
            currentState: HomeBase<*>,
            msg: HomeMsg.NewDataReceived
        ): ScreenCmdData
    }

    fun processMsg(msg: HomeMsg): ScreenCmdData {
        return when (msg) {
            is HomeMsg.InvalidateClick -> behavior.processInvalidateClick(this)
            is HomeMsg.NewDataReceived -> behavior.processNewDataReceived(this, msg)
        }
    }

    open class BaseBehavior : IBehavior {

        override fun processInvalidateClick(currentState: HomeBase<*>): ScreenCmdData {
            return ScreenCmdData(
                state = HomeProgress.Data(
                    oldState = currentState
                ).let {
                    HomeProgress(it)
                },
                cmd = HomeCmd.InvalidateData
            )
        }

        override fun processNewDataReceived(
            currentState: HomeBase<*>,
            msg: HomeMsg.NewDataReceived
        ): ScreenCmdData {
            return ScreenCmdData(
                state = HomeInvalidatable.Data(
                    oldState = currentState
                ).let {
                    HomeInvalidatable(it)
                },
                cmd = None()
            )
        }
    }
}