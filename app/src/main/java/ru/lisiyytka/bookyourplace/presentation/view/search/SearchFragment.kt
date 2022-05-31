package ru.lisiyytka.bookyourplace.presentation.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentRegistrationBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentSearchBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationPresenter
import ru.lisiyytka.bookyourplace.presentation.presenters.RoleSelectionPresenter
import ru.lisiyytka.bookyourplace.presentation.presenters.SearchPresenter
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationView
import toothpick.Toothpick

class SearchFragment : MvpAppCompatFragment(), SearchView {
    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(SearchPresenter::class.java)

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }
}