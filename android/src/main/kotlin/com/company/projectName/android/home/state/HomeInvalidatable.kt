package com.company.projectName.android.home.state

import com.company.projectName.android.base.StateData
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.home.HomeCmd
import com.company.projectName.android.home.HomeMsg

class HomeInvalidatable(
    data: Data
) : HomeBase<HomeInvalidatable.Data>(data) {

    class Data(
        val oldState: HomeBase<*>
    ) : StateData()

    object Behavior: HomeBase.IBehavior {

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
    }
}