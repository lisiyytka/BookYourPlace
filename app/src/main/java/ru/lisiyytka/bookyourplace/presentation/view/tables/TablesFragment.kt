package ru.lisiyytka.bookyourplace.presentation.view.tables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.data.AppValueEventListener
import ru.lisiyytka.bookyourplace.databinding.FragmentTablesBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.TableFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.adapters.TablesAdapter
import ru.lisiyytka.bookyourplace.presentation.presenters.TablesPresenter
import ru.lisiyytka.bookyourplace.utils.Constants
import toothpick.Toothpick

class TablesFragment : MvpAppCompatFragment(), TablesView {
    @InjectPresenter
    lateinit var tablesPresenter: TablesPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(TablesPresenter::class.java)

    private var _binding: FragmentTablesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTablesBinding.inflate(inflater, container, false)
        Constants.REF_DATABASE_ROOT.child(Constants.NODE_PLACE).child(Constants.AUTH.currentUser!!.uid)
            .child("nameOfPlace")
            .addValueEventListener(
                AppValueEventListener {
                    val result = it.getValue(String::class.java)
                    binding.nameOfPlace.text = result!!
                }
            )
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        Constants.REF_DATABASE_ROOT.child(Constants.NODE_PLACE).child(Constants.AUTH.currentUser!!.uid)
            .child(Constants.NODE_TABLES)
            .addValueEventListener(
                AppValueEventListener {
                    val listResult =
                        ArrayList(it.children.map { data -> data.getValue(TableFirebaseEntity::class.java)!! })
                    binding.recyclerView.adapter = TablesAdapter(listResult)
                }
            )
    }
}