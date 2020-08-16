package com.company.projectName.android.clean.domain.feature.counter

import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.feature.counter.contract.ITimerProcessor
import com.company.projectName.android.clean.domain.feature.counter.contract.TimerContract
import kotlinx.coroutines.*

@ExperimentalStdlibApi
class DomainProcessor(
    private val messageQuery: MessageQuery
): ITimerProcessor {

    private var timerJob: Job? = null
    private var counter = 0

    override fun startTimer() {
        timerJob = CoroutineScope(Dispatchers.IO).launch {
            while(true){
                delay(1000)
                counter++

                messageQuery.accept(
                    TimerContract.Message.CounterNewValue(
                        counter
                    )
                )
            }
        }
    }

    override fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }
}