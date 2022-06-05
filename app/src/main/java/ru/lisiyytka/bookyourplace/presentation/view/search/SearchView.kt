package ru.lisiyytka.bookyourplace.presentation.view.search

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity

@StateStrategyType(value = AddToEndStrategy::class)
interface SearchView : MvpView {
    fun showResult(placeList: ArrayList<PlaceFirebaseEntity>)
}