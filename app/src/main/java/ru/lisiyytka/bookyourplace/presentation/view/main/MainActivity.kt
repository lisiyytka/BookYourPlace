package ru.lisiyytka.bookyourplace.presentation.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.theartofdev.edmodo.cropper.CropImage
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.cash.CashOwner
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.MainPresenter
import ru.lisiyytka.bookyourplace.utils.createPathToFolderOfPlaceImage
import ru.lisiyytka.bookyourplace.utils.createPathToFolderOfTableImage
import ru.lisiyytka.bookyourplace.utils.isRegister
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

    @Inject
    lateinit var cashOwner: CashOwner

    private val navigator = AppNavigator(this, R.id.container)

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this@MainActivity, Toothpick.openScope(Scopes.APP_SCOPE))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityPresenter.startApp()

        val bottomNavigationUser: ConstraintLayout = findViewById(R.id.bottom_nav_user)
        bottomNavigationUser.visibility = View.GONE
        val bottomNavigationOwner: ConstraintLayout = findViewById(R.id.bottom_nav_owner)
        bottomNavigationOwner.visibility = View.GONE

        val mapButtonInactive = findViewById<ImageButton>(R.id.map_inactive)
        val mapButtonActive = findViewById<FrameLayout>(R.id.map_active)
        val searchButtonInactive = findViewById<ImageButton>(R.id.search_inactive)
        val searchButtonActive = findViewById<FrameLayout>(R.id.search_active)
        val accountButtonInactive = findViewById<ImageButton>(R.id.account_inactive)
        val accountButtonActive = findViewById<FrameLayout>(R.id.account_active)

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

        val bookInactive = findViewById<ImageButton>(R.id.book_inactive)
        val bookActive = findViewById<FrameLayout>(R.id.book_active)
        val placeAccountInactive = findViewById<ImageButton>(R.id.place_account_inactive)
        val placeAccountActive = findViewById<FrameLayout>(R.id.place_account_active)

        bookInactive.setOnClickListener {
            mainActivityPresenter.onBookClick()
            bookActive.visibility = View.VISIBLE
            placeAccountInactive.visibility = View.VISIBLE
            bookInactive.visibility = View.GONE
            placeAccountActive.visibility = View.GONE
        }

        placeAccountInactive.setOnClickListener {
            mainActivityPresenter.onPlaceAccountClick()
            bookActive.visibility = View.GONE
            placeAccountInactive.visibility = View.GONE
            bookInactive.visibility = View.VISIBLE
            placeAccountActive.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null
        ) {
            if (isRegister) {
                val changeUserPhotoImageRegister = findViewById<ImageView>(R.id.img)
                val uri = CropImage.getActivityResult(data).uri

                createPathToFolderOfPlaceImage().putFile(uri)
                changeUserPhotoImageRegister.setImageURI(uri)
            } else {
                val changeUserPhotoImageRegister = findViewById<ImageView>(R.id.img_table_reg)
                val uri = CropImage.getActivityResult(data).uri
                createPathToFolderOfTableImage().putFile(uri)
                Log.d("IMG", uri.toString())
                changeUserPhotoImageRegister.setImageURI(uri)
            }

        }
    }
}
