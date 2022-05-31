package ru.lisiyytka.bookyourplace.presentation.view.tables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentSearchBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentTablesBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.SearchPresenter
import ru.lisiyytka.bookyourplace.presentation.presenters.TablesPresenter
import ru.lisiyytka.bookyourplace.presentation.view.registration.RegistrationView
import toothpick.Toothpick

class TablesFragment : MvpAppCompatFragment(), TablesView {
    @InjectPresenter
    lateinit var tablesPresenter: TablesPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(TablesPresenter::class.java)

    private var _binding: FragmentTablesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTablesBinding.inflate(inflater, container, false)

        return binding.root
    }
}