package com.company.projectName.android.home

import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.projectName.android.base.*
import com.company.projectName.android.home.view.Data
import com.company.projectName.android.home.view.Initial
import com.company.projectName.android.home.view.Invalidatable
import com.company.projectName.android.home.view.Progress
import kotlinx.coroutines.delay

@ExperimentalStdlibApi
class Presenter : Component {

    private var currentState: Drawer = Drawer { Initial() }
        set(value) {
            field = value
            viewStateSource.postValue(value)
        }
    private val viewStateSource = MutableLiveData<Drawer>()
    val viewState: LiveData<Drawer> = viewStateSource

    private val program: Program by lazy {
        Program()
    }

    init {
        program.init(
            initialState = HomeScreenState.Initial,
            component = this
        )

        program.accept(HomeMsg.InvalidateClick)
    }

    override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
        return when (msg) {
            is HomeMsg.InvalidateClick -> {
                when (state) {
                    is HomeScreenState.Invalidatable -> state.oldState
                    else -> state
                }.let {
                    ScreenCmdData(
                        state = HomeScreenState.Progress(it),
                        cmd = HomeCmd.InvalidateData
                    )
                }
            }
            is HomeMsg.NewDataReceived -> {
                when (state) {
                    is HomeScreenState.Progress -> state.oldState
                    else -> state
                }.let {
                    HomeScreenState.Data(msg.data).let {
                        ScreenCmdData(
                            state = HomeScreenState.Invalidatable(it),
                            cmd = None()
                        )
                    }
                }
            }
            else -> {
                ScreenCmdData(
                    state = state,
                    cmd = None()
                )
            }
        }
    }

    override fun render(state: ScreenState) {
        val state = state as HomeScreenState
        currentState = Drawer {
            generateState(state)
        }
    }

    @Composable
    private fun generateState(state: HomeScreenState) {
        when (state) {
            is HomeScreenState.Initial -> {
                Initial()
            }
            is HomeScreenState.Invalidatable -> {
                Invalidatable(
                    oldState = {
                        generateState(state.oldState as HomeScreenState)
                    }, onClick = this::onInvalidateClick
                )
            }
            is HomeScreenState.Data -> {
                Data(data = state.data)
            }
            is HomeScreenState.Progress -> {
                Progress {
                    generateState(state.oldState as HomeScreenState)
                }
            }
        }
    }

    override suspend fun call(cmd: Cmd): Msg {
        val cmd = cmd as HomeCmd

        return when (cmd) {
            is HomeCmd.InvalidateData -> {
                delay(4000)//todo: for test

                HomeMsg.NewDataReceived(
                    data = "data data data data data data data data data"
                )
            }
        }
    }

    private fun onInvalidateClick() {
        program.accept(HomeMsg.InvalidateClick)
    }
}