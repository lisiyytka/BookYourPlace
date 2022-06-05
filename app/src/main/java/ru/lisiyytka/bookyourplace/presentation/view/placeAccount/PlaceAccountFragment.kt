package ru.lisiyytka.bookyourplace.presentation.view.placeAccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.R
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

        val toolbar = requireActivity().findViewById<FrameLayout>(R.id.bottom_nav_user)
        val bottomNav = toolbar.findViewById<ConstraintLayout>(R.id.constraint_owner_nav_bot)
        toolbar.visibility = View.VISIBLE

        val bookActive = bottomNav.findViewById<FrameLayout>(R.id.book_active)
        val bookInactive = bottomNav.findViewById<FrameLayout>(R.id.book_inactive)
        val placeAccountActive = bottomNav.findViewById<FrameLayout>(R.id.place_account_active)
        val placeAccountInactive = bottomNav.findViewById<FrameLayout>(R.id.place_account_inactive)

        bookActive.visibility = View.VISIBLE
        placeAccountInactive.visibility = View.VISIBLE
        bookInactive.visibility = View.GONE
        placeAccountActive.visibility = View.GONE

        return binding.root
    }
}