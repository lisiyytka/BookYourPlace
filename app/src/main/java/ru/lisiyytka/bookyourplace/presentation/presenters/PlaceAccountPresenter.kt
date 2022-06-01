package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.placeAccount.PlaceAccountView
import javax.inject.Inject

@InjectViewState
class PlaceAccountPresenter @Inject constructor() : MvpPresenter<PlaceAccountView>() {
}