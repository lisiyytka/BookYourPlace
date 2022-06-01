package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens.Account
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens.Login
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens.Map
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens.Search
import ru.lisiyytka.bookyourplace.presentation.view.main.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(private val router: Router) : MvpPresenter<MainView>() {

    fun startApp() {
        router.navigateTo(Login())
    }

    fun onMapButtonClick() {
        router.navigateTo(Map())
    }

    fun onSearchButtonClick() {
        router.navigateTo(Search())
    }

    fun onAccountButtonClick() {
        router.navigateTo(Account())
    }
}