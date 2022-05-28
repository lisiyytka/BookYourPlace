package ru.lisiyytka.bookyourplace.presentation.view.placeAccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentPlaceAccountBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.PlaceAccountPresenter
import toothpick.Toothpick

class PlaceAccountFragment : MvpAppCompatFragment(), PlaceAccountView {

    @InjectPresenter
    lateinit var placeAccountPresenter: PlaceAccountPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(PlaceAccountPresenter::class.java)

    private var _binding: FragmentPlaceAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceAccountBinding.inflate(inflater, container, false)

        return binding.root
    }
}