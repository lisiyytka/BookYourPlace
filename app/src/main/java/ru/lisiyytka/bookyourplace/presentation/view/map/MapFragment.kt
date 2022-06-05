package ru.lisiyytka.bookyourplace.presentation.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.databinding.FragmentLoginBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentMapBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.MapPresenter
import ru.lisiyytka.bookyourplace.presentation.view.login.LoginView
import toothpick.Toothpick

class MapFragment : MvpAppCompatFragment(), MapView {
    @InjectPresenter
    lateinit var mapPresenter: MapPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(MapPresenter::class.java)

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    lateinit var map: SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        map = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        map.onCreate(savedInstanceState)
        map.getMapAsync(OnMapReadyCallback {
            googleMap = it
            val ekbBounds = LatLngBounds(
                LatLng((56.821496), 	60.578646),// SW bounds
                LatLng((56.892695), 60.652224)// NE bounds
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ekbBounds.center, 11.5f))
        })

//        val toolbar = requireActivity().findViewById<FrameLayout>(R.id.bottom_nav_user)
//        val bottomNavigation = toolbar.findViewById<ConstraintLayout>(R.id.btm_nav_user_const)
//        toolbar.visibility = View.VISIBLE
        val bottomNavigation = requireActivity().findViewById<ConstraintLayout>(R.id.bottom_nav_user)
        bottomNavigation.visibility = View.VISIBLE
        val searchButtonActive = bottomNavigation.findViewById<FrameLayout>(R.id.search_active)
        val searchButtonInactive = bottomNavigation.findViewById<FrameLayout>(R.id.search_inactive)
        val mapButtonActive = bottomNavigation.findViewById<FrameLayout>(R.id.map_active)
        val mapButtonInactive = bottomNavigation.findViewById<FrameLayout>(R.id.map_inactive)
        val accountButtonActive = bottomNavigation.findViewById<FrameLayout>(R.id.account_active)
        val accountButtonInactive = bottomNavigation.findViewById<FrameLayout>(R.id.account_inactive)

        mapButtonActive.visibility = View.VISIBLE
        searchButtonInactive.visibility = View.VISIBLE
        accountButtonInactive.visibility = View.VISIBLE
        mapButtonInactive.visibility = View.GONE
        searchButtonActive.visibility = View.GONE
        accountButtonActive.visibility = View.GONE

        return binding.root
    }
}