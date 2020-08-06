package com.company.projectName.android.home

import androidx.compose.Composable
import com.company.projectName.android.base.LiveUpdatable
import com.company.projectName.android.base.mvu.*
import com.company.projectName.android.home.state.*
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
            initialState = HomeInitial,
            component = this
        )

        program.accept(HomeMsg.InvalidateClick)
    }

    //region UiProcessor

    override fun render(state: ScreenState) {
        viewState.set {
            generateState(state as HomeBase<*>)
        }
    }

    @Composable
    private fun generateState(state: HomeBase<*>) {
        when (state) {
            is HomeInitial -> {
                Initial()
            }
            is HomeInvalidatable -> {
                Invalidatable(
                    oldState = {
                        generateState(state.data.oldState)
                    }, onClick = {
                        program.accept(HomeMsg.InvalidateClick)
                    }
                )
            }
            is HomeInfo -> {
                Data(data = state.data)
            }
            is HomeProgress -> {
                Progress {
                    generateState(state.data.oldState)
                }
            }
        }
    }

    //endregion

    //region reducer

    override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
        state as HomeBase<*>
        return state.processMsg(msg as HomeMsg)
    }

    //endregion

    //region EffectHandler

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