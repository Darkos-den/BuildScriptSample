package com.company.projectName.android.home.state

import com.company.projectName.android.base.StateData

class HomeInfo(
    data: Data
) : HomeBase<HomeInfo.Data>(data) {

    class Data(
        val text: String
    ) : StateData()
}