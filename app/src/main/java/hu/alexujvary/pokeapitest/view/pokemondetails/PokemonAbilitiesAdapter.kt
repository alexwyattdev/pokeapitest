package hu.alexujvary.pokeapitest.view.pokemondetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.alexujvary.pokeapitest.R
import hu.alexujvary.pokeapitest.extensions.capitalizeFirstChar

class PokemonAbilitiesAdapter(
    private var items: List<String> = mutableListOf(),
) : RecyclerView.Adapter<PokemonAbilitiesAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ability_list_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]
        viewHolder.tvPokemonAbility.text = item.capitalizeFirstChar()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPokemonAbility: TextView = view.findViewById(R.id.tvPokemonAbility)
    }
}