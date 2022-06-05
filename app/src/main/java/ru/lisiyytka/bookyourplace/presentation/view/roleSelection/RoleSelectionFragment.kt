package ru.lisiyytka.bookyourplace.presentation.view.roleSelection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentRegistrationBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentRoleSelectionBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationPresenter
import ru.lisiyytka.bookyourplace.presentation.presenters.RoleSelectionPresenter
import toothpick.Toothpick

class RoleSelectionFragment : MvpAppCompatFragment(), RoleSelectionView {
    @InjectPresenter
    lateinit var roleSelectionPresenter: RoleSelectionPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(RoleSelectionPresenter::class.java)

    private var _binding: FragmentRoleSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoleSelectionBinding.inflate(inflater, container, false)

        binding.btnUser.setOnClickListener { roleSelectionPresenter.onRegisterUserClick() }
        binding.btnOwner.setOnClickListener { roleSelectionPresenter.onRegisterPlaceClick() }

        return binding.root
    }
}