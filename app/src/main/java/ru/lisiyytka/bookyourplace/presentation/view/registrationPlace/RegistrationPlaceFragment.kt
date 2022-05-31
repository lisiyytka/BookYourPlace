package ru.lisiyytka.bookyourplace.presentation.view.registrationPlace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentRegistrationPlaceBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationPlacePresenter
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationView
import toothpick.Toothpick

class RegistrationPlaceFragment : MvpAppCompatFragment(), RegistrationPlaceView {
    @InjectPresenter
    lateinit var registrationPlacePresenter: RegistrationPlacePresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(RegistrationPlacePresenter::class.java)

    private var _binding: FragmentRegistrationPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationPlaceBinding.inflate(inflater, container, false)

        return binding.root
    }
}