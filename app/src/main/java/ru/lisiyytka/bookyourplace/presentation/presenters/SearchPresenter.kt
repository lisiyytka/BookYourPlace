package ru.lisiyytka.bookyourplace.presentation.presenters

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.view.search.SearchView
import java.util.ArrayList
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor( private val router: Router) : MvpPresenter<SearchView>() {
    fun onListItemClick(placeId: String) {
        router.navigateTo(Screens.Place(placeId))
    }
}