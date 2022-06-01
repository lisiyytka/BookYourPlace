package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.placeAccount.PlaceAccountView
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationView
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter @Inject constructor() : MvpPresenter<RegistrationView> () {
}