package ru.lisiyytka.bookyourplace.presentation.view.registrationInfoAboutPlace

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface RegistrationAboutPlaceView : MvpView {
}