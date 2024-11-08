package com.basic.pokedex

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(private val listPokemon: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgThumbnail: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_pokemon, parent, false)
        return ListViewHolder(view)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (code, photo, name, desc) = listPokemon[position]
        holder.tvName.text = name
        holder.tvDescription.text = desc
        holder.imgThumbnail.setImageResource(photo)
        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, PokemonDetailActivity::class.java)
            intentDetail.putExtra("key_pokemon", listPokemon[position])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listPokemon.size

}