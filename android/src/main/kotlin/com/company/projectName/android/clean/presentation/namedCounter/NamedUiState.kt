package com.company.projectName.android.clean.presentation.namedCounter

import com.company.projectName.android.base.mvu.ScreenState
import com.company.projectName.android.clean.domain.feature.namedCounter.NamedCounterState
import com.company.projectName.android.clean.presentation.counter.CounterUiState

data class NamedUiState(
    val name: String,
    val counter: CounterUiState
) : ScreenState(){

    companion object{
        fun fromDomain(model: NamedCounterState): NamedUiState{
            return NamedUiState(
                name = model.name,
                counter = CounterUiState.fromDomain(model.counter)
            )
        }
    }
}