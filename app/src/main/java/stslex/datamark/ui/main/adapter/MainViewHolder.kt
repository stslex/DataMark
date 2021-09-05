package stslex.datamark.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import stslex.datamark.databinding.ItemShipsBinding
import stslex.datamark.data.ShipModel

class MainViewHolder(
    private val binding: ItemShipsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ShipModel) {
        binding.itemShipsName.text = item.name
        binding.itemShipsCode.text = item.code
    }
}