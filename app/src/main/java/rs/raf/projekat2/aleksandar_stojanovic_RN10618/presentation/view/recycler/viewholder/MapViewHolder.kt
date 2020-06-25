package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_map.*
import kotlinx.android.synthetic.main.layout_item_map.view.*
import kotlinx.android.synthetic.main.layout_item_map.view.datum
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import java.text.SimpleDateFormat
import java.util.*

class MapViewHolder(
    override val containerView : View,
    private val onEditBtnClicked : (Int) -> Unit
) : RecyclerView.ViewHolder(containerView),LayoutContainer {

    init {
        initListeners()
    }


    private fun initListeners() {
        containerView.editBtn.setOnClickListener {
            if(adapterPosition != RecyclerView.NO_POSITION){
                onEditBtnClicked.invoke(adapterPosition)
            }
        }
    }

    fun bind(map: Map) {
        containerView.titleTv.text = map.title
        containerView.bodyTv.text = map.note
        containerView.datum.text = map.date
    }

}