package ru.lisiyytka.bookyourplace.presentation.view.placeAccount

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
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
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.presenters.PlaceAccountPresenter
import ru.lisiyytka.bookyourplace.utils.Constants
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_TABLES
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import ru.lisiyytka.bookyourplace.utils.downloadAndSetImage
import ru.lisiyytka.bookyourplace.utils.placeUid
import toothpick.Toothpick
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("CheckResult")
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

        Observable.create<DataSnapshot>{ subscriber ->
            REF_DATABASE_ROOT.child(NODE_PLACE).child(placeUid).addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    subscriber.onNext(snapshot)
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }.subscribe({ response ->
            onResponse(response)
        }, { t -> onFailure(t) })


//        REF_DATABASE_ROOT.child(NODE_PLACE).child(AUTH.currentUser!!.uid).addValueEventListener(
//            AppValueEventListener {
//                val result = it.getValue(PlaceFirebaseEntity::class.java)
//                binding.nameOfPlace.text = result!!.nameOfPlace
//                binding.locationOfPlace.text = result.address
//                binding.schedule.text = result.schedule
//                binding.phoneNumbers.text = result.phoneNumber
//                binding.image.downloadAndSetImage(result.imgOfPlaceUrl)
//            }
//        )

        binding.btnAddTable.setOnClickListener {
            placeAccountPresenter.onRegisterFragmentClick()
        }
        initRecyclerView()

        return binding.root
    }

    private fun onResponse(response: DataSnapshot) {
        if (response.exists()) {
            val result = response.getValue(PlaceFirebaseEntity::class.java)
            binding.nameOfPlace.text = result!!.nameOfPlace
            binding.locationOfPlace.text = result.address
            binding.schedule.text = result.schedule
            binding.phoneNumbers.text = result.phoneNumber
            binding.image.downloadAndSetImage(result.imgOfPlaceUrl)
        }
    }

    private fun onFailure(t: Throwable) {
        Log.w("Users", "getUserFromFirebaseFail", t)
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