package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.view.place.PlaceView
import javax.inject.Inject

@InjectViewState
class PlacePresenter @Inject constructor(private val router: Router) : MvpPresenter<PlaceView>() {

}