package stslex.datamark.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import stslex.datamark.data.model.ShipsLabelModel
import stslex.datamark.databinding.ItemShipsBinding

class MainViewHolder(
    private val binding: ItemShipsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ShipsLabelModel, code: String) {
        binding.itemShipsName.text = item.label
        binding.itemShipsCode.text = code
    }
}