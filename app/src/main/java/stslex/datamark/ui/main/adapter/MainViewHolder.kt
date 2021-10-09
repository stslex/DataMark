package stslex.datamark.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import stslex.datamark.data.model.ships_take.ShipsListItem
import stslex.datamark.databinding.ItemShipsBinding

class MainViewHolder(
    private val binding: ItemShipsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ShipsListItem) {
        binding.itemShipsCode.text = item.code
        binding.itemShipsLabel.text = item.received_count
    }
}