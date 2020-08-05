package com.company.projectName.android.home

import androidx.compose.Composable
import com.company.projectName.android.base.LiveUpdatable
import com.company.projectName.android.base.mvu.*
import com.company.projectName.android.home.view.*
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

    private val viewGenerator: ViewGenerator by lazy {
        ViewGenerator(
            onInvalidate = this::onInvalidateClick
        )
    }

    init {
        program.init(
            initialState = HomeScreenState.Initial,
            component = this
        )

        program.accept(HomeMsg.InvalidateClick)
    }

    private fun onInvalidateClick() {
        program.accept(HomeMsg.InvalidateClick)
    }

    //region Component

    override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
        return when (msg) {
            is HomeMsg.InvalidateClick -> {
                (state as HomeScreenState).processInvalidateClick()
            }
            is HomeMsg.NewDataReceived -> {
                (state as HomeScreenState).processNewDataReceived(state, msg)
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
        viewState.set {
            viewGenerator.generateState(state as HomeScreenState)
        }
    }

    override suspend fun call(cmd: Cmd): Msg {
        return when (cmd as HomeCmd) {
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