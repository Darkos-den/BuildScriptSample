package com.company.projectName.android.base

import com.company.projectName.android.base.mvu.ScreenState

abstract class StateData

interface StateBehavior

abstract class BaseState<Data : StateData, Behavior : StateBehavior>(
    val data: Data,
    val behavior: Behavior
): ScreenState()