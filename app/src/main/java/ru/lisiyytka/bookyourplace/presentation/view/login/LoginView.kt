package ru.lisiyytka.bookyourplace.presentation.view.login

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface LoginView : MvpView {
    fun showToast(string: String)
}