package ru.lisiyytka.bookyourplace.presentation.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.MainPresenter
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainActivityPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() =
        Toothpick.openScope(Scopes.APP_SCOPE).getInstance(MainPresenter::class.java)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this@MainActivity, Toothpick.openScope(Scopes.APP_SCOPE))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityPresenter.startApp()

        val mapButtonInactive = findViewById<ImageButton>(R.id.map_inactive)
        val mapButtonActive = findViewById<FrameLayout>(R.id.map_active)
        val searchButtonInactive = findViewById<ImageButton>(R.id.search_inactive)
        val searchButtonActive = findViewById<FrameLayout>(R.id.search_active)
        val accountButtonInactive = findViewById<ImageButton>(R.id.account_inactive)
        val accountButtonActive = findViewById<FrameLayout>(R.id.account_active)

        mapButtonActive.visibility = View.VISIBLE
        searchButtonInactive.visibility = View.VISIBLE
        accountButtonInactive.visibility = View.VISIBLE
        mapButtonInactive.visibility = View.GONE
        searchButtonActive.visibility = View.GONE
        accountButtonActive.visibility = View.GONE

        mapButtonInactive.setOnClickListener {
            mainActivityPresenter.onMapButtonClick()
            mapButtonActive.visibility = View.VISIBLE
            mapButtonInactive.visibility = View.GONE
            searchButtonActive.visibility = View.GONE
            accountButtonActive.visibility = View.GONE
            searchButtonInactive.visibility = View.VISIBLE
            accountButtonInactive.visibility = View.VISIBLE
        }

        searchButtonInactive.setOnClickListener {
            mainActivityPresenter.onSearchButtonClick()
            mapButtonActive.visibility = View.GONE
            searchButtonActive.visibility = View.VISIBLE
            searchButtonInactive.visibility = View.GONE
            accountButtonActive.visibility = View.GONE
            mapButtonInactive.visibility = View.VISIBLE
            accountButtonInactive.visibility = View.VISIBLE
        }

        accountButtonInactive.setOnClickListener {
            mainActivityPresenter.onAccountButtonClick()
            mapButtonActive.visibility = View.GONE
            searchButtonActive.visibility = View.GONE
            accountButtonActive.visibility = View.VISIBLE
            accountButtonInactive.visibility = View.GONE
            mapButtonInactive.visibility = View.VISIBLE
            searchButtonInactive.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }
}