package com.company.projectName.android.home

import androidx.compose.Composable
import com.company.projectName.android.base.LiveUpdatable
import com.company.projectName.android.base.mvu.*
import com.company.projectName.android.home.view.Data
import com.company.projectName.android.home.view.Initial
import com.company.projectName.android.home.view.Invalidatable
import com.company.projectName.android.home.view.Progress
import kotlinx.coroutines.delay

typealias ViewState = @Composable() () -> Unit

@ExperimentalStdlibApi
class Presenter : Component {

    val viewState = LiveUpdatable<ViewState>(
        initialState = { Initial() }
    )

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

    private fun processInvalidateClick(state: ScreenState): ScreenCmdData {
        return when (state) {
            is HomeScreenState.Invalidatable -> state.oldState
            else -> state
        }.let {
            ScreenCmdData(
                state = HomeScreenState.Progress(it),
                cmd = HomeCmd.InvalidateData
            )
        }
    }

    private fun processNewDataReceived(
        state: ScreenState,
        msg: HomeMsg.NewDataReceived
    ): ScreenCmdData {
        return when (state) {
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

    private fun onInvalidateClick() {
        program.accept(HomeMsg.InvalidateClick)
    }

    //region Component

    override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
        return when (msg) {
            is HomeMsg.InvalidateClick -> {
                processInvalidateClick(state)
            }
            is HomeMsg.NewDataReceived -> {
                processNewDataReceived(state, msg)
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
        viewState.set {
            generateState(state)
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

    //endregion
}