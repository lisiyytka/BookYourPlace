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
import ru.lisiyytka.bookyourplace.utils.hideKeyboard
import ru.lisiyytka.bookyourplace.utils.startLoading
import ru.lisiyytka.bookyourplace.utils.stopLoading
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
                binding.loading.visibility = View.VISIBLE
                startLoading(binding.progressView)
                binding.phoneNumber.hideKeyboard()
                loginPresenter.startPhoneNumberVerification(
                    binding.phoneNumber.text.toString(),
                    activity as MainActivity
                )
            }
        })
        binding.verifierCode.addTextChangedListener(AppTextWatcher {
            if (binding.verifierCode.text.toString().length == 6) {
                loginPresenter.verifyPhoneNumberWithCode(binding.verifierCode.text.toString())
                binding.verifierCode.hideKeyboard()
            }
        })

        return binding.root
    }

    override fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
    }

    override fun stopLoadingAndShowFieldOfCode() {
        binding.loading.visibility = View.GONE
        stopLoading(binding.progressView)
        binding.infoAboutCode.visibility = View.VISIBLE
        binding.verifierCode.visibility = View.VISIBLE
        binding.resendBtn.visibility = View.VISIBLE
        binding.phoneNumber.isEnabled = false
    }
}