package ru.lisiyytka.bookyourplace.presentation.view.roleSelection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentRegistrationBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationPresenter
import ru.lisiyytka.bookyourplace.presentation.presenters.RoleSelectionPresenter
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationView
import toothpick.Toothpick

class RoleSelectionFragment : MvpAppCompatFragment(), RoleSelectionView{
    @InjectPresenter
    lateinit var registrationPresenter: RegistrationPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(RoleSelectionPresenter::class.java)

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }
}