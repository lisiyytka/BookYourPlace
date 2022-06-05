package ru.lisiyytka.bookyourplace.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.utils.Constants.AUTH
import ru.lisiyytka.bookyourplace.utils.Constants.FOLDER_PLACE_IMAGE
import ru.lisiyytka.bookyourplace.utils.Constants.REF_STORAGE_ROOT
import java.util.*

class AppTextWatcher(val onSuccess: (Editable?) -> Unit) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        onSuccess(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}

val placeUid: String = AUTH.currentUser!!.uid

val tableUid: String = UUID.randomUUID().toString()

var isRegister: Boolean = true

fun startLoading(loading: CircularProgressView) {
    loading.startAnimation()
    loading.visibility = View.VISIBLE
}

fun stopLoading(loading: CircularProgressView) {
    loading.stopAnimation()
    loading.visibility = View.GONE
}

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}

fun ImageView.downloadAndSetImage(url: String) {
    if (url.isEmpty()) {
        Picasso.get().load(R.drawable.group_43).into(this)
    } else{
        Picasso.get()
            .load(url)
            .into(this)
    }
}

fun createPathToFolderOfPlaceImage(): StorageReference {
    return REF_STORAGE_ROOT
        .child(FOLDER_PLACE_IMAGE)
        .child(placeUid)
}
fun createPathToFolderOfTableImage(): StorageReference {
    return REF_STORAGE_ROOT
        .child(FOLDER_PLACE_IMAGE)
        .child(tableUid)
}
