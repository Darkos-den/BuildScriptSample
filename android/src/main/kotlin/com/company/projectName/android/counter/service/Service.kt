package com.company.projectName.android.counter.service

import com.company.projectName.android.counter.MessageQuery
import com.company.projectName.android.counter.contract.ServiceContract
import kotlinx.coroutines.*

@ExperimentalStdlibApi
class Service: IService {

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