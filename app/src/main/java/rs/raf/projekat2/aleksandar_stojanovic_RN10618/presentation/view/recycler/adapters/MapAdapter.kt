package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.recycler.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.R
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.recycler.diff.MapDiffCallback
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.recycler.viewholder.MapViewHolder

class MapAdapter (
    private val onEditBtnClicked : (Map) -> Unit
) : ListAdapter<Map,MapViewHolder>(MapDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_item_map, parent, false)

        return MapViewHolder(
            view,
            { onEditBtnClicked.invoke(getItem(it)) }
        )
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}