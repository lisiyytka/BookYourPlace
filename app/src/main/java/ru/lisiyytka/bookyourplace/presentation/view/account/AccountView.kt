package ru.lisiyytka.bookyourplace.presentation.view.account

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface AccountView : MvpView {
}