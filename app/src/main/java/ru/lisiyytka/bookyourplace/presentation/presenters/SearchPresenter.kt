package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.search.SearchView
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor() : MvpPresenter<SearchView>() {
}