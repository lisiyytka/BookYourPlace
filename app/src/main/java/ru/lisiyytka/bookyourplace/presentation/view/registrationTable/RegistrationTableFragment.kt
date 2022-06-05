package ru.lisiyytka.bookyourplace.presentation.view.registrationTable

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentPlaceBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentRegisterTableBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.TableFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.presenters.PlacePresenter
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationTablePresenter
import ru.lisiyytka.bookyourplace.presentation.view.place.PlaceView
import ru.lisiyytka.bookyourplace.utils.*
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_TABLES
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import toothpick.Toothpick

class RegistrationTableFragment : MvpAppCompatFragment(), RegistrationTableView {

    @InjectPresenter
    lateinit var registrationTablePresenter: RegistrationTablePresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(RegistrationTablePresenter::class.java)

    private var _binding: FragmentRegisterTableBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterTableBinding.inflate(inflater, container, false)

        binding.choosePhoto.setOnClickListener {
            isRegister = false
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(requireActivity())
        }

        binding.next.setOnClickListener {
            val table = TableFirebaseEntity(
                nameOfTable = binding.name.getText(),
                description = binding.description.getText(),
                numbersOfPersonAtTheTable = binding.spinner.selectedItem.toString()
            )
            REF_DATABASE_ROOT.child(NODE_PLACE).child(placeUid).child(NODE_TABLES).child(tableUid).setValue(table)
            createPathToFolderOfTableImage().downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    val imgUrl = it.result.toString()
                    REF_DATABASE_ROOT.child(NODE_PLACE).child(placeUid).child(NODE_TABLES).child(tableUid).setValue(imgUrl)
                }
            }
            registrationTablePresenter.navigateTo()
        }

        return binding.root
    }
}