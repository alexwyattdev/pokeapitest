package hu.alexujvary.pokeapitest.view.pokemonlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.alexujvary.pokeapitest.R
import hu.alexujvary.pokeapitest.extensions.capitalizeFirstChar
import hu.alexujvary.pokeapitest.interfaces.PokemonSelectionListener
import hu.alexujvary.pokeapitest.model.PokemonItem
import hu.alexujvary.pokeapitest.util.PokemonDataUtil

class PokemonListAdapter(
    private var context: Context,
    private var items: MutableList<PokemonItem> = mutableListOf(),
    private val listener: PokemonSelectionListener?
) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    fun update(itemsList: List<PokemonItem>, isPagingContent: Boolean) {
        if (!isPagingContent) {
            val itemsSize = items.size
            items.clear()
            notifyItemRangeRemoved(0, itemsSize)
        }
        if (itemsList.isEmpty()) {
            return
        }
        items.addAll(itemsList)
        notifyItemRangeInserted(items.size, itemsList.size)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]

        viewHolder.tvPokemonId.text = PokemonDataUtil.getIdFromUrl(item.url).toString()

        Glide.with(context).load(PokemonDataUtil.getImageUrlFromUrl(item.url)).centerInside().error(R.drawable.placeholder).placeholder(R.drawable.placeholder).into(viewHolder.ivPokemonImage)

        viewHolder.tvPokemonName.text = item.pokemonName?.capitalizeFirstChar()

        viewHolder.root.setOnClickListener {
            listener?.pokemonSelected(item)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: ViewGroup = view.findViewById(R.id.clPokemonListItemRoot)
        val tvPokemonId: TextView = view.findViewById(R.id.tvPokemonId)
        val ivPokemonImage: ImageView = view.findViewById(R.id.ivPokemonImage)
        val tvPokemonName: TextView = view.findViewById(R.id.tvPokemonName)
    }
}