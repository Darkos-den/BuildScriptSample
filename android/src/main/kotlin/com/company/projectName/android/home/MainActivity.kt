package com.company.projectName.android.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.company.projectName.android.base.mvu.*
import com.company.projectName.android.home.view.*
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

//    @ExperimentalStdlibApi
//    private val presenter: Presenter by lazy {
//        Presenter()
//    }

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainComponent()
        }
    }
}

class Wrapper(
    val state: ViewState
)

@ExperimentalStdlibApi
@Composable
fun mainComponent() {
    val program = remember {
        Program()
    }

    var screenState: HomeScreenState by state {
        HomeScreenState.Initial as HomeScreenState
    }

    remember {
        program.init(
            HomeScreenState.Initial,
            object : Component {
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
                    screenState = state as HomeScreenState
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
            }
        )

        program.accept(HomeMsg.InvalidateClick)
    }

    mainScreen(
        onInvalidate = {
            program.accept(HomeMsg.InvalidateClick)
        },
        state = screenState
    )
}

@Composable
fun mainScreen(
    onInvalidate: () -> Unit,
    state: HomeScreenState
) {
    when (state) {
        is HomeScreenState.Initial -> {
            Initial()
        }
        is HomeScreenState.Invalidatable -> {
            Invalidatable(
                oldState = {
                    mainScreen(onInvalidate, state.oldState as HomeScreenState)
                }, onClick = onInvalidate
            )
        }
        is HomeScreenState.Data -> {
            Data(data = state.data)
        }
        is HomeScreenState.Progress -> {
            Progress {
                mainScreen(onInvalidate, state.oldState as HomeScreenState)
            }
        }
    }
}

@ExperimentalStdlibApi
@Composable
fun App(state: @Composable() () -> Unit) {
    MaterialTheme {
        Scaffold(topAppBar = { MyAppBar() }) {
            // observe the viewmodel here. compose will recompose when it changes.
            state()
        }
    }
}

@Composable
fun MyAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "My app"
            )
        },
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        elevation = 12.dp
    )
}

@Preview
@Composable
fun preview() {
    MaterialTheme {
        Scaffold(topAppBar = { MyAppBar() }) {
            Initial()
        }
    }
}
