package ru.lisiyytka.bookyourplace.presentation.view.favoritePlaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentAccountBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentFavouritePlacesBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.AccountPresenter
import toothpick.Toothpick

class FavoritePlacesFragment : MvpAppCompatFragment(), FavoritePlacesView {
    @InjectPresenter
    lateinit var accountPresenter: AccountPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(AccountPresenter::class.java)

    private var _binding: FragmentFavouritePlacesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritePlacesBinding.inflate(inflater, container, false)

        return binding.root
    }
}