package ru.lisiyytka.bookyourplace.presentation.view.place

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.data.AppValueEventListener
import ru.lisiyytka.bookyourplace.databinding.FragmentPlaceBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.presenters.PlacePresenter
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_USERS
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import ru.lisiyytka.bookyourplace.utils.downloadAndSetImage
import ru.lisiyytka.bookyourplace.utils.placeUid
import toothpick.Toothpick

class PlaceFragment : MvpAppCompatFragment(), PlaceView {
    @InjectPresenter
    lateinit var placePresenter: PlacePresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(PlacePresenter::class.java)

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!
    lateinit var idPlace: String

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        arguments?.takeIf { it.containsKey("REPOSITORY") }?.apply {
            idPlace = getString("REPOSITORY").toString()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        REF_DATABASE_ROOT.child(NODE_PLACE).child(idPlace).addValueEventListener(
            AppValueEventListener {
                val place = it.getValue(PlaceFirebaseEntity::class.java)
                binding.image.downloadAndSetImage(place!!.imgOfPlaceUrl)
                binding.nameOfPlace.text = place.nameOfPlace
                binding.categoryPlace.text = place.typeOfPlace
                binding.location.text = place.address
                binding.schedule.text = place.schedule
                binding.phoneNumbers.text = place.phoneNumbersOnProfile
                binding.btnSavePlace.setOnClickListener {
                    REF_DATABASE_ROOT.child(NODE_USERS)
                        .child(AUTH.currentUser!!.uid)
                        .child("favoritePlaces")
                        .child(idPlace)
                        .setValue(place)
                    binding.btnSavePlace.isSelected = true
                }
            }
        )
    }

    companion object {
        fun instance(placeId: String): PlaceFragment {
            val fragment = PlaceFragment()
            fragment.arguments = Bundle().apply { putString("REPOSITORY", placeId) }
            return fragment
        }
    }
}