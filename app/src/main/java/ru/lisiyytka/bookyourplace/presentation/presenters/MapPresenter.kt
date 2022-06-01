package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.map.MapView
import javax.inject.Inject

@InjectViewState
class MapPresenter @Inject constructor() : MvpPresenter<MapView>(){
}