package ru.lisiyytka.bookyourplace.presentation.view.registrationInfoAboutPlace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentRegistrationInfoAboutPlaceBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentRoleSelectionBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationInfoAboutPlacePresenter
import ru.lisiyytka.bookyourplace.presentation.presenters.RoleSelectionPresenter
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationView
import toothpick.Toothpick

class RegistrationInfoAboutPlaceFragment :  MvpAppCompatFragment(), RegistrationAboutPlaceView {
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

        return binding.root
    }
}