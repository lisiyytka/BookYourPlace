package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.view.placeAccount.PlaceAccountView
import javax.inject.Inject

@InjectViewState
class PlaceAccountPresenter @Inject constructor(private val router: Router) : MvpPresenter<PlaceAccountView>() {
    fun onRegisterFragmentClick() {
        router.navigateTo(Screens.Tables())
    }
}