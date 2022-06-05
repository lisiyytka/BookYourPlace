package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens.RegistrationUser
import ru.lisiyytka.bookyourplace.presentation.view.roleSelection.RoleSelectionView
import javax.inject.Inject

@InjectViewState
class RoleSelectionPresenter @Inject constructor(private val router: Router) : MvpPresenter<RoleSelectionView>() {
    fun onRegisterUserClick() {
        router.navigateTo(RegistrationUser())
    }

    fun onRegisterPlaceClick() {
        router.navigateTo(Screens.RegistrationPlace())
    }
}