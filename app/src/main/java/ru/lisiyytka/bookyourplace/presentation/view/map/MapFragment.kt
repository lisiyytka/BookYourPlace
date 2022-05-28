package ru.lisiyytka.bookyourplace.presentation.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentLoginBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentMapBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.MapPresenter
import ru.lisiyytka.bookyourplace.presentation.view.login.LoginView
import toothpick.Toothpick

class MapFragment : MvpAppCompatFragment(), LoginView {
    @InjectPresenter
    lateinit var mapPresenter: MapPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(MapPresenter::class.java)

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        return binding.root
    }
}