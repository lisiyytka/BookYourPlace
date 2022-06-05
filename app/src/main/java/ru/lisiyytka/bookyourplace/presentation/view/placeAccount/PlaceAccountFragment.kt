package ru.lisiyytka.bookyourplace.presentation.view.placeAccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
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

        val bottomNavigation = requireActivity().findViewById<ConstraintLayout>(R.id.bottom_nav_owner)
        bottomNavigation.visibility = View.VISIBLE

        val bookActive = bottomNavigation.findViewById<FrameLayout>(R.id.book_active)
        val bookInactive = bottomNavigation.findViewById<ImageButton>(R.id.book_inactive)
        val placeAccountActive = bottomNavigation.findViewById<FrameLayout>(R.id.place_account_active)
        val placeAccountInactive = bottomNavigation.findViewById<ImageButton>(R.id.place_account_inactive)

        bookActive.visibility = View.GONE
        placeAccountInactive.visibility = View.GONE
        bookInactive.visibility = View.VISIBLE
        placeAccountActive.visibility = View.VISIBLE

        binding.btnAddTable.setOnClickListener {
            placeAccountPresenter.onRegisterFragmentClick()
        }

        return binding.root
    }
}