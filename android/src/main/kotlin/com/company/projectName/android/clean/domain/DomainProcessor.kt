package com.company.projectName.android.clean.domain

import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.feature.counter.ITimerProcessor
import kotlinx.coroutines.*

@ExperimentalStdlibApi
class DomainProcessor: ITimerProcessor {

    private var timerJob: Job? = null
    private var counter = 0

    lateinit var messageQuery: MessageQuery

    override fun startTimer() {
        timerJob = CoroutineScope(Dispatchers.IO).launch {
            while(true){
                delay(1000)
                counter++

                messageQuery.accept(ServiceContract.Message.CounterNewValue(counter))
            }
        }
    }

    override fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }
}