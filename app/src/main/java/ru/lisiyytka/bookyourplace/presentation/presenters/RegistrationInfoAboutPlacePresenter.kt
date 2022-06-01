package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.placeAccount.PlaceAccountView
import ru.lisiyytka.bookyourplace.presentation.view.registrationInfoAboutPlace.RegistrationAboutPlaceView
import javax.inject.Inject


@InjectViewState
class RegistrationInfoAboutPlacePresenter @Inject constructor() : MvpPresenter<RegistrationAboutPlaceView>() {
}