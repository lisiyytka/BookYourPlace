package ru.lisiyytka.bookyourplace.presentation.view.placeAccount

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface PlaceAccountView : MvpView {
}