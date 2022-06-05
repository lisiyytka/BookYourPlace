package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.cash.CashOwner
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.TableFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.view.registrationInfoAboutPlace.RegistrationAboutPlaceView
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import ru.lisiyytka.bookyourplace.utils.Constants.FOLDER_PLACE_IMAGE
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import ru.lisiyytka.bookyourplace.utils.Constants.REF_STORAGE_ROOT
import ru.lisiyytka.bookyourplace.utils.placeUid
import javax.inject.Inject


@InjectViewState
class RegistrationInfoAboutPlacePresenter @Inject constructor(
    private val router: Router,
    private val cashOwner: CashOwner
) :
    MvpPresenter<RegistrationAboutPlaceView>() {

    private val pathForImg = REF_STORAGE_ROOT.child(FOLDER_PLACE_IMAGE).child(AUTH.currentUser!!.uid)

    fun onBackPressed() {
        router.backTo(Screens.RoleSelection())
    }

    fun savePlace(
        namePlace: String,
        typeOfPlace: String,
        address: String,
        phonesOfPlace: String,
        cuisine: String,
        schedule: String,
        averageCheck: String
    ) {
        REF_DATABASE_ROOT.child(NODE_PLACE).child(placeUid).setValue(
            PlaceFirebaseEntity(
                id = placeUid,
                phoneNumber = AUTH.currentUser!!.phoneNumber.toString(),
                nameOfPlace = namePlace,
                typeOfPlace = typeOfPlace,
                address = address,
                phoneNumbersOnProfile = phonesOfPlace,
                cuisine = cuisine,
                schedule = schedule,
                averageCheck = averageCheck,
                tables = TableFirebaseEntity()
            )
        )
    }

    fun onNextClick() {
        router.navigateTo(Screens.Place())
    }
}