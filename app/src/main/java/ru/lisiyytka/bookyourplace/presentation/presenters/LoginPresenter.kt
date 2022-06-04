package ru.lisiyytka.bookyourplace.presentation.presenters

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.lisiyytka.bookyourplace.presentation.cicerone.Screens
import ru.lisiyytka.bookyourplace.presentation.view.accountInfo.AccountInfoView
import ru.lisiyytka.bookyourplace.presentation.view.login.LoginView
import ru.lisiyytka.bookyourplace.presentation.view.main.MainActivity
import ru.lisiyytka.bookyourplace.presentation.view.search.SearchFragment
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class LoginPresenter @Inject constructor(private val router: Router): MvpPresenter<LoginView>() {

    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onFirstViewAttach(){
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                //Данная функция запускается в двух случаях:
                //мобильный телефон проверяется автоматически без необходимости вводить код подтверждения.
                //сервисы Google Play обнаруживают входящие SMS и запускают процесс проверки без каких-либо действий со стороны пользователя.
                Log.d("AUTH", "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("AUTH", "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // ошибка верефикации номера телефона
                    viewState.showToast("Неверный номер телефона")
                } else if (e is FirebaseTooManyRequestsException) {
                    // привышена квота SMS
                    viewState.showToast("Привышена квота SMS")
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("AUTH", "onCodeSent:$verificationId")
                // сохранение верификационного ID и переопределение токена
                storedVerificationId = verificationId
                resendToken = token
            }
        }
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        AUTH.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    router.navigateTo(Screens.Map())
                } else {
                    Log.w("AUTH", "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun startPhoneNumberVerification(phoneNumber: String, activity: MainActivity) {
        AUTH.setLanguageCode("ru")
        val options = PhoneAuthOptions.newBuilder(AUTH)
            .setPhoneNumber(phoneNumber)       // номер телефона для верефикации
            .setTimeout(60L, TimeUnit.SECONDS) // таймер на повторную отправку кода
            .setActivity(activity)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun registration(){
        router.navigateTo(Screens.Map())
    }

    fun verifyPhoneNumberWithCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(AUTH)
            .setPhoneNumber(phoneNumber)       // номер телефона для верефикации
            .setTimeout(60L, TimeUnit.SECONDS) // таймер на повторную отправку кода
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // передача нового токена
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }
}