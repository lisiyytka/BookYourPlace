package ru.lisiyytka.bookyourplace.presentation.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.view.roleSelection.RoleSelectionView
import javax.inject.Inject

@InjectViewState
class RoleSelectionPresenter @Inject constructor() : MvpPresenter<RoleSelectionView>() {
}