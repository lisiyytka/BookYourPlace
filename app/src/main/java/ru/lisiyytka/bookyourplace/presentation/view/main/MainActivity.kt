package ru.lisiyytka.bookyourplace.presentation.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.theartofdev.edmodo.cropper.CropImage
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.presentation.presenters.MainPresenter
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_USERS
import ru.lisiyytka.bookyourplace.utils.Constants.PHOTO_URL
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import ru.lisiyytka.bookyourplace.utils.createPath
import ru.lisiyytka.bookyourplace.utils.downloadAndSetImage
import ru.lisiyytka.bookyourplace.utils.placeUid
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val changeUserPhotoImage = findViewById<ImageView>(R.id.img)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            createPath().putFile(uri).addOnCompleteListener {
                if (it.isSuccessful) {
                    createPath().downloadUrl.addOnCompleteListener {
                        if (it.isSuccessful) {
                            val photoUrl = it.result.toString()
                            REF_DATABASE_ROOT.child(NODE_PLACE)
                                .child(placeUid).child(PHOTO_URL)
                                .setValue(photoUrl)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        changeUserPhotoImage.downloadAndSetImage(photoUrl)
                                    }
                                }
                        }
                    }
                }   
            }
        }
    }
}