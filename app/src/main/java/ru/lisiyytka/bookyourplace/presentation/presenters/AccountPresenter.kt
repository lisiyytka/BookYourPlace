package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.account.AccountView
import javax.inject.Inject

@InjectViewState
class AccountPresenter @Inject constructor() : MvpPresenter<AccountView>() {
}