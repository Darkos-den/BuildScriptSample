package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.base.mvu.None
import com.company.projectName.android.base.mvu.ScreenCmdData
import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.domain.feature.counter.contract.CounterContract

sealed class CounterState : ScreenState() {
    open val counter: Int = 0

    abstract fun timerClick(): ScreenCmdData
    abstract fun newCounterValue(value: Int): ScreenCmdData

    data class Active(
        override val counter: Int
    ) : CounterState() {

        override fun timerClick(): ScreenCmdData {
            return ScreenCmdData(
                state = NotActive(counter),
                cmd = CounterContract.Command.StopTimer
            )
        }

        override fun newCounterValue(value: Int): ScreenCmdData {
            return ScreenCmdData(
                state = this.copy(counter = value),
                cmd = None()
            )
        }
    }

    data class NotActive(
        override val counter: Int
    ) : CounterState() {

        override fun timerClick(): ScreenCmdData {
            return ScreenCmdData(
                state = Active(counter),
                cmd = CounterContract.Command.StartTimer
            )
        }

        override fun newCounterValue(value: Int): ScreenCmdData {
            return ScreenCmdData(
                state = this.copy(counter = value),
                cmd = None()
            )
        }
    }
}