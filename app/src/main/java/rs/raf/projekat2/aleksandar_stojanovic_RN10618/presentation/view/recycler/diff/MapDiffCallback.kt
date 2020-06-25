package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map

class MapDiffCallback : DiffUtil.ItemCallback<Map>() {

    override fun areItemsTheSame(oldItem: Map, newItem: Map): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Map, newItem: Map): Boolean {
        return oldItem.title == newItem.title
                && oldItem.note == newItem.note
    }

}