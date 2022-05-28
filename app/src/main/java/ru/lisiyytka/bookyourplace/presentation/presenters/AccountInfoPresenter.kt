package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.account.AccountView
import ru.lisiyytka.bookyourplace.presentation.view.accountInfo.AccountInfoView

@InjectViewState
class AccountInfoPresenter : MvpPresenter<AccountInfoView>() {
}