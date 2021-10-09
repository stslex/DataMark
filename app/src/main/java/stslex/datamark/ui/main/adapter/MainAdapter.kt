package stslex.datamark.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import stslex.datamark.data.model.ui.ShipsListItem
import stslex.datamark.databinding.ItemShipsBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val items = mutableListOf<ShipsListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShipsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItems(list: List<ShipsListItem>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}