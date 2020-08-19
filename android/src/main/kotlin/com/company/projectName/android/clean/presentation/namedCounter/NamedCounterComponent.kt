package com.company.projectName.android.clean.presentation.namedCounter

import android.content.Context
import android.util.Log
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.onDispose
import androidx.ui.layout.Column
import androidx.ui.livedata.observeAsState
import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.di.NamedCounterDi
import com.company.projectName.android.clean.domain.core.MessageQuery
import com.company.projectName.android.clean.domain.core.Program
import com.company.projectName.android.clean.domain.feature.counter.contract.CounterContract
import com.company.projectName.android.clean.domain.feature.hintedTextField.HintedTextFieldContract
import com.company.projectName.android.clean.domain.feature.namedCounter.NamedCounterContract
import com.company.projectName.android.clean.domain.feature.namedCounter.NamedCounterState
import com.company.projectName.android.clean.presentation.base.BaseComponent
import com.darkos.mylibrary.presentation.TextFiledWithHint

@ExperimentalStdlibApi
class NamedCounterComponent(
    parentContext: Context,
    messageQuery: MessageQuery
) : BaseComponent<NamedUiState>(
    parentContext,
    messageQuery
) {

    override fun injectProgram(): Program {
        return NamedCounterDi(messageQuery, this).createProgram()
    }

    override fun mapToUiModel(state: ScreenState): NamedUiState {
        return (state as NamedCounterState).toUiState()
    }

    @Composable
    override fun drawState() {
        val state by uiState.observeAsState()

        onDispose {
            Log.d("myLog", "dispose screen")
            //todo: send dispose message
            clear()
        }

        Column {
            NamedCounter(
                uiState = state ?: return@Column,
                timerClick = {
                    messageQuery.accept(CounterContract.Message.TimerClick)
                },
                nameChanged = {
                    messageQuery.accept(NamedCounterContract.Message.NameChanged(it))
                }
            )

            TextFiledWithHint(
                state = state?.textFiledState ?: return@Column,
                onFocusChanged = {
                    messageQuery.accept(HintedTextFieldContract.Message.FocusChanged(it))
                },
                onTextChanged = {
                    messageQuery.accept(HintedTextFieldContract.Message.TextChanged(it))
                }
            )
        }
    }

}