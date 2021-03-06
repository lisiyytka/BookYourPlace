package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.cash.CashOwner
import ru.lisiyytka.bookyourplace.data.repository.FirebaseRepository
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.TimeOfReserveFirebaseEntity
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.UserFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationView
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import ru.lisiyytka.bookyourplace.utils.Constants.ROLE_USER
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val router: Router,
    private val cashOwner: CashOwner
) :
    MvpPresenter<RegistrationView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setPhoneNumber(AUTH.currentUser!!.phoneNumber.toString())
    }

    fun saveUser(name: String, surname: String) {
        firebaseRepository.saveUser(
            UserFirebaseEntity(
                id = AUTH.currentUser!!.uid,
                phoneNumber = AUTH.currentUser!!.phoneNumber.toString(),
                name = name,
                surname = surname,
                type = ROLE_USER
            )
        )
        cashOwner.lastFragmentCash = "user"
    }

    fun onBackPressed() {
        router.backTo(Screens.RoleSelection())
    }
}