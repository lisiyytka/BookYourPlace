package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.place.PlaceView
import javax.inject.Inject

@InjectViewState
class PlacePresenter @Inject constructor() : MvpPresenter<PlaceView>() {
}