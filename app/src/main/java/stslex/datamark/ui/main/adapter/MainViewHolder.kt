package stslex.datamark.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import stslex.datamark.data.model.LabelModel
import stslex.datamark.databinding.ItemShipsBinding

class MainViewHolder(
    private val binding: ItemShipsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LabelModel, code: String) {
        binding.itemShipsCode.text = code
        binding.itemShipsLabel.text = item.label
    }
}