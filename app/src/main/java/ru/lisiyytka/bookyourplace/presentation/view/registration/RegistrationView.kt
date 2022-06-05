package ru.lisiyytka.bookyourplace.presentation.view.registration

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface RegistrationView : MvpView {
    fun setPhoneNumber(phoneNumber: String)
}