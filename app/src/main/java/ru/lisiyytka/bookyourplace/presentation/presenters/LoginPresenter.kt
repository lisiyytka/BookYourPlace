package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.view.accountInfo.AccountInfoView
import ru.lisiyytka.bookyourplace.presentation.view.login.LoginView
import ru.lisiyytka.bookyourplace.presentation.view.search.SearchFragment
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(private val router: Router): MvpPresenter<LoginView>() {

    fun onLoginButtonClick() {
        router.navigateTo(Screens.Map())
    }
}