package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.favoritePlaces.FavoritePlacesView
import javax.inject.Inject

@InjectViewState
class FavoritePlacesPresenter @Inject constructor() : MvpPresenter<FavoritePlacesView>() {
}