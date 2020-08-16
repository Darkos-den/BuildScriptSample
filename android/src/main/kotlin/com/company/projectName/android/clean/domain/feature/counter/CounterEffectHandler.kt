package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.Cmd
import com.company.projectName.android.base.mvu.EffectHandler
import com.company.projectName.android.base.mvu.Idle
import com.company.projectName.android.base.mvu.Msg

class CounterEffectHandler(
    private val service: IService
) : EffectHandler {

    override suspend fun call(cmd: Cmd): Msg {
        when (cmd) {
            is CounterContract.Command.StartTimer -> {
                service.startTimer()
            }
            is CounterContract.Command.StopTimer -> {
                service.stopTimer()
            }
        }

        return Idle()
    }
}