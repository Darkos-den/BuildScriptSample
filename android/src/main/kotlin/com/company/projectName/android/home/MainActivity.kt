package com.company.projectName.android.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.onDispose
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.company.projectName.android.clean.presentation.counter.CounterComponent
import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.home.view.Data
import com.company.projectName.android.home.view.Initial
import com.company.projectName.android.home.view.Invalidatable
import com.company.projectName.android.home.view.Progress

class MainActivity : AppCompatActivity() {

//    @ExperimentalStdlibApi
//    private val presenter: Presenter by lazy {
//        Presenter()
//    }

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            onCommit {
                Log.d("myLog", "commit")
            }
            onDispose {
                Log.d("myLog", "dispose")
            }

            val messageQuery = MessageQuery()

            CounterComponent(
                parentContext = this,
                messageQuery = messageQuery
            ).drawState()
        }
    }
}

//class HomeDi {
//
//    @ExperimentalStdlibApi
//    fun createProgram(): Program {
//        return Program(
//            reducer = HomeComponent.DefaultReducer(),
//            effectHandler = HomeComponent.DefaultEffectHandler()
//        )
//    }
//}

/**
 * Play btn click -> Play effect -> Played state
 */

abstract class UiComponent

//@ExperimentalStdlibApi
//class HomeComponent(
//    private val parentContext: Context,
//    private val program: Program
//) : UiComponent() {
//
//    private val initialState = HomeScreenState.Initial
//
//    var uiState = MutableLiveData<HomeScreenState>()
//
//    init {
//        program.init(
//            initialState = initialState,
//            component = object : Component {
//                override fun render(state: ScreenState) {
//                    uiState.postValue(state as HomeScreenState)
//                }
//            }
//        )
//
//        program.accept(HomeMsg.InvalidateClick)
//    }
//
//    @Composable
//    fun show() {
//        val state by uiState.observeAsState()
//
//        onDispose {
//            Log.d("myLog", "dispose screen")
//            dispose()
//        }
//
//        buildView(
//            onInvalidate = {
//                program.accept(HomeMsg.InvalidateClick)
//            },
//            state = state ?: HomeScreenState.Initial
//        )
//    }
//
//    @Composable
//    private fun buildView(
//        onInvalidate: () -> Unit,
//        state: HomeScreenState
//    ) {
//        when (state) {
//            is HomeScreenState.Initial -> {
//                Initial()
//            }
//            is HomeScreenState.Invalidatable -> {
//                Invalidatable(
//                    oldState = {
//                        mainScreen(onInvalidate, state.oldState as HomeScreenState)
//                    }, onClick = onInvalidate
//                )
//            }
//            is HomeScreenState.Data -> {
//                Data(data = state.data)
//            }
//            is HomeScreenState.Progress -> {
//                Progress {
//                    mainScreen(onInvalidate, state.oldState as HomeScreenState)
//                }
//            }
//        }
//    }
//
//    private fun dispose() {
//        program.clear()
//    }
//
//    class DefaultReducer : Reducer {
//
//        override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
//            return when (msg) {
//                is HomeMsg.InvalidateClick -> {
//                    (state as HomeScreenState).processInvalidateClick()
//                }
//                is HomeMsg.NewDataReceived -> {
//                    (state as HomeScreenState).processNewDataReceived(state, msg)
//                }
//                else -> {
//                    ScreenCmdData(
//                        state = state,
//                        cmd = None()
//                    )
//                }
//            }
//        }
//    }
//
//    class DefaultEffectHandler : EffectHandler {
//
//        override suspend fun call(cmd: Cmd): Msg {
//            return when (cmd as HomeCmd) {
//                is HomeCmd.InvalidateData -> {
//                    delay(4000)//todo: for test
//
//                    HomeMsg.NewDataReceived(
//                        data = "data data data data data data data data data"
//                    )
//                }
//            }
//        }
//    }
//}
//
//@ExperimentalStdlibApi
//@Composable
//fun mainComponent() {
//    val program = remember {
//        Program(
//            reducer = object : Reducer {
//
//                override fun update(state: ScreenState, msg: Msg): ScreenCmdData {
//                    return when (msg) {
//                        is HomeMsg.InvalidateClick -> {
//                            (state as HomeScreenState).processInvalidateClick()
//                        }
//                        is HomeMsg.NewDataReceived -> {
//                            (state as HomeScreenState).processNewDataReceived(state, msg)
//                        }
//                        else -> {
//                            ScreenCmdData(
//                                state = state,
//                                cmd = None()
//                            )
//                        }
//                    }
//                }
//            },
//            effectHandler = object : EffectHandler {
//                override suspend fun call(cmd: Cmd): Msg {
//                    return when (cmd as HomeCmd) {
//                        is HomeCmd.InvalidateData -> {
//                            delay(4000)//todo: for test
//
//                            HomeMsg.NewDataReceived(
//                                data = "data data data data data data data data data"
//                            )
//                        }
//                    }
//                }
//            }
//        )
//    }
//
//    var screenState: HomeScreenState by state {
//        HomeScreenState.Initial as HomeScreenState
//    }
//
//    remember {
//        program.init(
//            initialState = HomeScreenState.Initial,
//            component = object : Component {
//                override fun render(state: ScreenState) {
//                    screenState = state as HomeScreenState
//                }
//            }
//        )
//
//        program.accept(HomeMsg.InvalidateClick)
//    }
//
//    mainScreen(
//        onInvalidate = {
//            program.accept(HomeMsg.InvalidateClick)
//        },
//        state = screenState
//    )
//}

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
