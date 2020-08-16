package com.company.projectName.android.counter

import android.content.Context
import android.util.Log
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.onDispose
import androidx.lifecycle.MutableLiveData
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Button
import com.company.projectName.android.base.mvu.*
import com.company.projectName.android.counter.contract.CounterContract
import com.company.projectName.android.counter.contract.ServiceContract
import com.company.projectName.android.counter.service.IService
import com.company.projectName.android.home.HomeMsg
import com.company.projectName.android.home.Program

@ExperimentalStdlibApi
class CounterComponent(
    private val parentContext: Context,
    private val program: Program,
    private val messageQuery: MessageQuery
) {

    private val initialState = CounterState(
        isProgress = false,
        counter = 0
    )

    var uiState = MutableLiveData<CounterState>()

    init {
        program.init(
            initialState = initialState,
            component = component {
                uiState.postValue(it as CounterState)
            }
        )
    }

    @Composable
    fun show() {
        val state by uiState.observeAsState()

        onDispose {
            Log.d("myLog", "dispose screen")
            dispose()
        }

        buildView(
            state = state ?: initialState,
            timerClick = {
                if (state?.isProgress == true) {
                    CounterContract.Message.StopTimerClick
                } else {
                    CounterContract.Message.StartTimerClick
                }.let {
                    messageQuery.accept(it)
                }
            }
        )
    }

    @Composable
    private fun buildView(
        timerClick: () -> Unit,
        state: CounterState
    ) {
        Column {
            Text(text = "counter: ${state.counter}")
            Button(onClick = timerClick) {
                val text = if (state.isProgress) {
                    "stop timer"
                } else {
                    "start timer"
                }
                Text(text = text)
            }
        }
    }

    private fun dispose() {
        program.clear()
    }

    class DefaultReducer : Reducer {

        override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
            state as CounterState
            return when (msg) {
                is CounterContract.Message.StartTimerClick -> {
                    ScreenCmdData(
                        state = state.copy(isProgress = true),
                        cmd = CounterContract.Command.StartTimer
                    )
                }
                is CounterContract.Message.StopTimerClick -> {
                    ScreenCmdData(
                        state = state.copy(isProgress = false),
                        cmd = CounterContract.Command.StopTimer
                    )
                }
                is ServiceContract.Message.CounterNewValue -> {
                    ScreenCmdData(
                        state = state.copy(counter = msg.value),
                        cmd = None()
                    )
                }
                else -> {
                    ScreenCmdData(
                        state = state,
                        cmd = None()
                    )
                }
            }
        }

    }

    class DefaultEffectHandler(
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
}