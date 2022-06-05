package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.data.AppValueEventListener
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.view.search.SearchView
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import java.util.ArrayList
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor( private val router: Router) : MvpPresenter<SearchView>() {
    fun onListItemClick(placeId: String) {
        router.navigateTo(Screens.Place(placeId))
    }

    fun search(query: String) {
        REF_DATABASE_ROOT.child(NODE_PLACE).addValueEventListener(
            AppValueEventListener {
                val list = ArrayList(it.children.map { data -> data.getValue(PlaceFirebaseEntity::class.java) })
                val result = arrayListOf<PlaceFirebaseEntity>()

                for (place in list){
                    if (place!!.nameOfPlace.lowercase().contains(query.lowercase())
                        || place.cuisine.lowercase().contains(query.lowercase())
                        || place.typeOfPlace.lowercase().contains(query.lowercase())){
                        result.add(place)
                    }
                }
                viewState.showResult(result)
            }
        )
    }
}