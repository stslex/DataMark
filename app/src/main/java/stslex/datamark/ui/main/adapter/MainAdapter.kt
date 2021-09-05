package stslex.datamark.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import stslex.datamark.data.model.ShipsLabelModel
import stslex.datamark.databinding.ItemShipsBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    private val items = mutableListOf<ShipsLabelModel>()
    private var code = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShipsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position], code)
    }

    override fun getItemCount(): Int = items.size

    fun addItems(list: List<ShipsLabelModel>, code: String) {
        items.addAll(list)
        this.code = code
        notifyDataSetChanged()
    }
}