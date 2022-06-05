package ru.lisiyytka.bookyourplace.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.TableFirebaseEntity
import ru.lisiyytka.bookyourplace.utils.downloadAndSetImage

class TablesAdapter(private val tables: ArrayList<TableFirebaseEntity>) :
    RecyclerView.Adapter<TablesAdapter.TableHolder>() {

    class TableHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageOfTable: ImageView = view.findViewById(R.id.image_of_table)
        val nameOfTable: TextView = view.findViewById(R.id.name_of_table)
        val countOfPlaces: TextView = view.findViewById(R.id.count_of_places)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_list, parent, false)
        return TableHolder(view)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        holder.imageOfTable.downloadAndSetImage(tables[position].imgOfTableUrl)
        holder.nameOfTable.text = tables[position].nameOfTable
        holder.countOfPlaces.text = tables[position].numbersOfPersonAtTheTable
    }

    override fun getItemCount(): Int = tables.size
}