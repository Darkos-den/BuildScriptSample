package com.company.projectName.android.home.state

import com.company.projectName.android.base.StateData

class HomeProgress(
    data: Data
) : HomeBase<HomeProgress.Data>(data) {
    class Data(
        val oldState: HomeBase<*>
    ) : StateData()
}