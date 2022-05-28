package ru.lisiyytka.bookyourplace.presentation.view.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentPlaceBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.PlacePresenter
import ru.lisiyytka.bookyourplace.presentation.view.accountInfo.AccountInfoView
import toothpick.Toothpick

class PlaceFragment : MvpAppCompatFragment(), PlaceView {
    @InjectPresenter
    lateinit var placePresenter: PlacePresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(PlacePresenter::class.java)

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)

        return binding.root
    }
}