package ru.lisiyytka.bookyourplace.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity

class SearchAdapter() :
    RecyclerView.Adapter<SearchAdapter.SingleChatHolder>() {

    private var mListMessagesCache = emptyList<PlaceFirebaseEntity>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleOfPlaceField: TextView = view.findViewById(R.id.title_of_place)
        val locationFiled: TextView = view.findViewById(R.id.location)
        val scheduleField: TextView = view.findViewById(R.id.schedule)
        val phoneField: TextView = view.findViewById(R.id.phone)
        val averageCheck: TextView = view.findViewById(R.id.average_check)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_list, parent, false)
        return SingleChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        holder.titleOfPlaceField.text = mListMessagesCache[position].nameOfPlace
        holder.locationFiled.text = mListMessagesCache[position].address
        holder.scheduleField.text = mListMessagesCache[position].schedule
        holder.phoneField.text = mListMessagesCache[position].phoneNumber
        holder.averageCheck.text = mListMessagesCache[position].averageCheck
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    fun setList(list: List<PlaceFirebaseEntity>) {
        mListMessagesCache = list
        notifyDataSetChanged()
    }
}