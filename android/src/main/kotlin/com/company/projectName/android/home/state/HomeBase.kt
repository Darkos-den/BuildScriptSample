package com.company.projectName.android.home.state

import com.company.projectName.android.base.BaseState
import com.company.projectName.android.base.StateBehavior
import com.company.projectName.android.base.StateData
import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.home.HomeCmd
import com.company.projectName.android.home.HomeMsg

interface IBehavior<Data : StateData, State : HomeBase<Data>> : StateBehavior {

    fun processInvalidateClick(currentState: State): ScreenCmdData {
        return HomeBase.BaseBehavior.processInvalidateClick(currentState)
    }

    fun processNewDataReceived(
        currentState: State,
        msg: HomeMsg.NewDataReceived
    ): ScreenCmdData {
        return HomeBase.BaseBehavior.processNewDataReceived(currentState, msg)
    }
}

abstract class HomeBase<Data : StateData>(
    data: Data,
    behavior: IBehavior<Data, HomeBase<Data>> = BaseBehavior
) : BaseState<Data, HomeBase.IBehavior<Data>>(
    data = data,
    behavior = behavior
) {

    fun processMsg(msg: HomeMsg): ScreenCmdData {
        return when (msg) {
            is HomeMsg.InvalidateClick -> behavior.processInvalidateClick(this)
            is HomeMsg.NewDataReceived -> behavior.processNewDataReceived(this, msg)
        }
    }

    open class BaseBehavior<Data : StateData, State : HomeBase<Data>> : IBehavior<Data, State> {

        override fun processInvalidateClick(currentState: State): ScreenCmdData {
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
            currentState: State,
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