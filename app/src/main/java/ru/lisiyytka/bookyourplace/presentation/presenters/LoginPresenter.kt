package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.accountInfo.AccountInfoView
import ru.lisiyytka.bookyourplace.presentation.view.login.LoginView

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
}