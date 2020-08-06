package com.company.projectName.android.home.state

import com.company.projectName.android.base.StateData
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.home.HomeCmd

class HomeInvalidatable(
    data: Data
) : HomeBase<HomeInvalidatable.Data>(
    data = data,
    behavior = Behavior
) {

    class Data(
        val oldState: HomeBase<*>
    ) : StateData()

    object Behavior : HomeBase.BaseBehavior() {

        override fun processInvalidateClick(currentState: HomeBase<*>): ScreenCmdData {
            currentState as HomeInvalidatable
            return ScreenCmdData(
                state = HomeProgress.Data(
                    oldState = currentState.data.oldState
                ).let {
                    HomeProgress(it)
                },
                cmd = HomeCmd.InvalidateData
            )
        }
    }
}