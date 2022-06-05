package ru.lisiyytka.bookyourplace.presentation.view.registrationInfoAboutPlace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentRegistrationInfoAboutPlaceBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationInfoAboutPlacePresenter
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.PHOTO_URL
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import ru.lisiyytka.bookyourplace.utils.placeUid
import toothpick.Toothpick


class RegistrationInfoAboutPlaceFragment : MvpAppCompatFragment(), RegistrationAboutPlaceView {
    @InjectPresenter
    lateinit var registrationInfoAboutPlacePresenter: RegistrationInfoAboutPlacePresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(RegistrationInfoAboutPlacePresenter::class.java)

    private var _binding: FragmentRegistrationInfoAboutPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationInfoAboutPlaceBinding.inflate(inflater, container, false)

        binding.choosePhoto.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(requireActivity())
        }

        binding.next.setOnClickListener {
            registrationInfoAboutPlacePresenter.savePlace(
                binding.nameOfPlaceField.getText(),
                binding.spinner.selectedItem.toString(),
                binding.addressField.getText(),
                binding.phonesField.text.toString(),
                binding.cuisineField.text.toString(),
                binding.scheduleField.text.toString(),
                binding.averageCheckField.getText()
            )
            registrationInfoAboutPlacePresenter.onNextClick()
        }

        binding.include.back.setOnClickListener { registrationInfoAboutPlacePresenter.onBackPressed() }

        return binding.root
    }

}