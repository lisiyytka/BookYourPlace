package ru.lisiyytka.bookyourplace.presentation.cicerone

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.lisiyytka.bookyourplace.presentation.view.login.LoginFragment
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationFragment
import ru.lisiyytka.bookyourplace.presentation.view.roleSelection.RoleSelectionFragment

object Screens {
    fun Login() = FragmentScreen { LoginFragment() }

    fun Registration() = FragmentScreen { RoleSelectionFragment() }

    fun RegistrationUser() = FragmentScreen { RegistrationFragment() }

}