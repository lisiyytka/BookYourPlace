package ru.lisiyytka.bookyourplace.presentation.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.databinding.FragmentLoginBinding
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.LoginPresenter
import ru.lisiyytka.bookyourplace.presentation.view.main.MainActivity
import ru.lisiyytka.bookyourplace.utils.AppTextWatcher
import toothpick.Toothpick

class LoginFragment : MvpAppCompatFragment(), LoginView {
    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(LoginPresenter::class.java)

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.phoneNumber.addTextChangedListener(AppTextWatcher {
            if (binding.phoneNumber.text.toString().length == 12) {
                loginPresenter.startPhoneNumberVerification(
                    binding.phoneNumber.text.toString(),
                    activity as MainActivity
                )
            }
        })
        binding.verifierCode.addTextChangedListener(AppTextWatcher {
            if (binding.phoneNumber.text.toString().length == 6) {
                binding.loginBtn.visibility = View.VISIBLE
                loginPresenter.verifyPhoneNumberWithCode(binding.phoneNumber.text.toString())
            }
        })
        return binding.root
    }

    override fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
    }
}