package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.registrationTable.RegistrationTableView
import javax.inject.Inject

@InjectViewState
class RegistrationTablePresenter @Inject constructor(private val router: Router) :
    MvpPresenter<RegistrationTableView>() {
}