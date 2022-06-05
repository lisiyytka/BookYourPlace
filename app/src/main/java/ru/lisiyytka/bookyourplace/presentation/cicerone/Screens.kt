package ru.lisiyytka.bookyourplace.presentation.cicerone

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationTablePresenter
import ru.lisiyytka.bookyourplace.presentation.view.account.AccountFragment
import ru.lisiyytka.bookyourplace.presentation.view.login.LoginFragment
import ru.lisiyytka.bookyourplace.presentation.view.map.MapFragment
import ru.lisiyytka.bookyourplace.presentation.view.place.PlaceFragment
import ru.lisiyytka.bookyourplace.presentation.view.placeAccount.PlaceAccountFragment
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationFragment
import ru.lisiyytka.bookyourplace.presentation.view.registrationInfoAboutPlace.RegistrationInfoAboutPlaceFragment
import ru.lisiyytka.bookyourplace.presentation.view.registrationTable.RegistrationTableFragment
import ru.lisiyytka.bookyourplace.presentation.view.roleSelection.RoleSelectionFragment
import ru.lisiyytka.bookyourplace.presentation.view.search.SearchFragment

object Screens {

    //Registration and Login navigation

    fun Login() = FragmentScreen { LoginFragment() }

    fun RoleSelection() = FragmentScreen { RoleSelectionFragment() }

    fun RegistrationUser() = FragmentScreen { RegistrationFragment() }

    fun RegistrationPlace() = FragmentScreen { RegistrationInfoAboutPlaceFragment() }

    //Main navigation by bottom bar

    fun Map() = FragmentScreen { MapFragment() }

    fun Search() = FragmentScreen { SearchFragment() }

    fun Account() = FragmentScreen { AccountFragment() }

    //Search navigation

    fun PlaceAccount() = FragmentScreen { PlaceAccountFragment() }

    fun Place(placeId: String) = FragmentScreen { PlaceFragment.instance(placeId) }

    fun Tables() = FragmentScreen { RegistrationTableFragment() }

}