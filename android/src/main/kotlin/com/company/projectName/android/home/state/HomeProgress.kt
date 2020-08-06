package com.company.projectName.android.home.state

import com.company.projectName.android.base.StateData
import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.home.HomeMsg

class HomeProgress(
    data: Data
) : HomeBase<HomeProgress.Data>(
    data = data,
    behavior = Behavior
) {

    class Data(
        val oldState: HomeBase<*>
    ) : StateData()

    object Behavior : HomeBase.BaseBehavior() {

        override fun processNewDataReceived(
            currentState: HomeBase<*>,
            msg: HomeMsg.NewDataReceived
        ): ScreenCmdData {
            currentState as HomeProgress
            return ScreenCmdData(
                state = HomeInvalidatable.Data(
                    oldState = currentState.data.oldState
                ).let {
                    HomeInvalidatable(it)
                },
                cmd = None()
            )
        }
    }
}