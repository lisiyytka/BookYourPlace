package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.registrationPlace.RegistrationPlaceView
import javax.inject.Inject

@InjectViewState
class RegistrationPlacePresenter @Inject constructor() : MvpPresenter<RegistrationPlaceView>() {
}