package ru.lisiyytka.bookyourplace.presentation.view.registrationTable

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface RegistrationTableView : MvpView {
}