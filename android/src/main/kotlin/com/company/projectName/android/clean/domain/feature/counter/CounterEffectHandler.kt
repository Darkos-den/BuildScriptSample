package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.Cmd
import com.company.projectName.android.base.mvu.EffectHandler
import com.company.projectName.android.base.mvu.Idle
import com.company.projectName.android.base.mvu.Msg
import com.company.projectName.android.clean.domain.feature.counter.contract.CounterContract
import com.company.projectName.android.clean.domain.feature.counter.contract.ITimerProcessor

class CounterEffectHandler(
    private val processor: ITimerProcessor
) : EffectHandler {

    override suspend fun call(cmd: Cmd): Msg {
        when (cmd) {
            is CounterContract.Command.StartTimer -> {
                processor.startTimer()
            }
            is CounterContract.Command.StopTimer -> {
                processor.stopTimer()
            }
        }

        return Idle()
    }
}