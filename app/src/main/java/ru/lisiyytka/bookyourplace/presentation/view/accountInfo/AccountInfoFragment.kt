package ru.lisiyytka.bookyourplace.presentation.view.accountInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentAccountBinding
import ru.lisiyytka.bookyourplace.databinding.FragmentAccountInfoBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.AccountInfoPresenter
import ru.lisiyytka.bookyourplace.presentation.presenters.AccountPresenter
import toothpick.Toothpick

class AccountInfoFragment : MvpAppCompatFragment(), AccountInfoView {
    @InjectPresenter
    lateinit var accountInfoPresenter: AccountInfoPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(AccountInfoPresenter::class.java)

    private var _binding: FragmentAccountInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountInfoBinding.inflate(inflater, container, false)

        return binding.root
    }
}