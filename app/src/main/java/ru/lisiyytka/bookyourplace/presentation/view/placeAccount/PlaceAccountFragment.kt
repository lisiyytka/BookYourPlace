package ru.lisiyytka.bookyourplace.presentation.view.placeAccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.data.AppValueEventListener
import ru.lisiyytka.bookyourplace.databinding.FragmentPlaceAccountBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.TableFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.adapters.TablesAdapter
import ru.lisiyytka.bookyourplace.presentation.presenters.PlaceAccountPresenter
import ru.lisiyytka.bookyourplace.utils.Constants
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_TABLES
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import ru.lisiyytka.bookyourplace.utils.downloadAndSetImage
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

        REF_DATABASE_ROOT.child(NODE_PLACE).child(AUTH.currentUser!!.uid).addValueEventListener(
            AppValueEventListener {
                val result = it.getValue(PlaceFirebaseEntity::class.java)
                binding.nameOfPlace.text = result!!.nameOfPlace
                binding.locationOfPlace.text = result.address
                binding.schedule.text = result.schedule
                binding.phoneNumbers.text = result.phoneNumber
                binding.image.downloadAndSetImage(result.imgOfPlaceUrl)
            }
        )

        binding.btnAddTable.setOnClickListener {
            placeAccountPresenter.onRegisterFragmentClick()
        }
        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        REF_DATABASE_ROOT.child(Constants.NODE_PLACE).child(AUTH.currentUser!!.uid).child(NODE_TABLES)
            .addValueEventListener(
                AppValueEventListener {
                    val listResult =
                        ArrayList(it.children.map { data -> data.getValue(TableFirebaseEntity::class.java)!! })
                    binding.recyclerView.adapter = TablesAdapter(listResult)
                }
            )
    }
}