package ru.lisiyytka.bookyourplace.presentation.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.data.AppValueEventListener
import ru.lisiyytka.bookyourplace.databinding.FragmentSearchBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.adapters.SearchAdapter
import ru.lisiyytka.bookyourplace.presentation.presenters.SearchPresenter
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import ru.lisiyytka.bookyourplace.utils.hideKeyboard
import ru.lisiyytka.bookyourplace.utils.startLoading
import ru.lisiyytka.bookyourplace.utils.stopLoading
import toothpick.Toothpick

class SearchFragment : MvpAppCompatFragment(), SearchView {
    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(SearchPresenter::class.java)

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        initRecyclerView()

        binding.message.visibility = View.GONE

        binding.searchBtn.setOnClickListener {
            it.hideKeyboard()
            if (binding.searchField.text.toString().isNotEmpty()){
                binding.placeList.adapter = SearchAdapter(ArrayList()) { placeId ->
                    searchPresenter.onListItemClick(
                        placeId
                    )
                }
                searchPresenter.search(binding.searchField.text.toString())
                startLoading(binding.progressView)
            }
        }

        return binding.root
    }

    private fun initRecyclerView() {
        startLoading(binding.progressView)
        binding.placeList.layoutManager = LinearLayoutManager(requireContext())
        REF_DATABASE_ROOT.child(NODE_PLACE).addValueEventListener(
            AppValueEventListener{
                val listResult = ArrayList(it.children.map { data -> data.getValue(PlaceFirebaseEntity::class.java)!!})
                stopLoading(binding.progressView)
                binding.placeList.adapter = SearchAdapter(listResult) { placeId ->
                    searchPresenter.onListItemClick(
                        placeId
                    )
                }
            }
        )
    }

    override fun showResult(placeList: ArrayList<PlaceFirebaseEntity>) {
        stopLoading(binding.progressView)
        if (placeList.isNotEmpty()){
            binding.placeList.adapter = SearchAdapter(placeList) { placeId ->
                searchPresenter.onListItemClick(
                    placeId
                )
            }
            binding.message.visibility = View.GONE
        } else {
            binding.placeList.adapter = SearchAdapter(ArrayList()) { placeId ->
                searchPresenter.onListItemClick(
                    placeId
                )
            }
            binding.message.visibility = View.VISIBLE
        }

    }
}