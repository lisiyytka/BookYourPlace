package ru.lisiyytka.bookyourplace.presentation.view.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.Auth
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentRegistrationBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.RegistrationPresenter
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import toothpick.Toothpick

class RegistrationFragment : MvpAppCompatFragment(), RegistrationView {

    @InjectPresenter
    lateinit var registrationPresenter: RegistrationPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(RegistrationPresenter::class.java)

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.next.setOnClickListener {
            registrationPresenter.saveUser(binding.nameField.getText(), binding.surnameField.getText())
        }

        binding.include.back.setOnClickListener { registrationPresenter.onBackPressed() }

        return binding.root
    }

    override fun setPhoneNumber(phoneNumber: String) {
        binding.phoneField.setText(phoneNumber)
    }
}