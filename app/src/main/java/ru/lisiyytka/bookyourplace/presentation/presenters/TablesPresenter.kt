package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.tables.TablesView
import javax.inject.Inject

@InjectViewState
class TablesPresenter @Inject constructor() : MvpPresenter<TablesView>() {
}