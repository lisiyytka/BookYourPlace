package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.account.AccountView
import ru.lisiyytka.bookyourplace.presentation.view.accountInfo.AccountInfoView
import javax.inject.Inject

@InjectViewState
class AccountInfoPresenter @Inject constructor() : MvpPresenter<AccountInfoView>() {
}