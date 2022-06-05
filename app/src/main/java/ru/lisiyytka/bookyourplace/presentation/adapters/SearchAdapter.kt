package ru.lisiyytka.bookyourplace.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.utils.downloadAndSetImage

class SearchAdapter(private val places: ArrayList<PlaceFirebaseEntity>) :
    RecyclerView.Adapter<SearchAdapter.SingleChatHolder>() {

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleOfPlaceField: TextView = view.findViewById(R.id.title_of_place)
        val locationFiled: TextView = view.findViewById(R.id.location)
        val scheduleField: TextView = view.findViewById(R.id.schedule)
        val phoneField: TextView = view.findViewById(R.id.phone)
        val averageCheck: TextView = view.findViewById(R.id.average_check)
        val cardBg: ImageView = view.findViewById(R.id.card_bg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_list, parent, false)
        return SingleChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        holder.titleOfPlaceField.text = places[position].nameOfPlace
        holder.locationFiled.text = places[position].address
        holder.scheduleField.text = places[position].schedule
        holder.phoneField.text = places[position].phoneNumbersOnProfile
        holder.averageCheck.text = places[position].averageCheck
        holder.cardBg.downloadAndSetImage(places[position].imgOfPlaceUrl)
    }

    override fun getItemCount(): Int = places.size
}