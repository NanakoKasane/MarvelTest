package com.test.marvel.ui.dashboard.characters.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.marvel.R
import com.test.marvel.databinding.ItemCharacterBinding
import com.test.marvel.domain.Character

class CharactersAdapter(
        private val items: List<Character>?,
        private val itemClickListener : (Character?) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>(), Filterable {
    var charactersFilterList : List<Character>? = null

    init {
        charactersFilterList = items as ArrayList<Character>
    }


    override fun getItemCount(): Int {
        return charactersFilterList?.size ?:0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
            CharactersViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.item_character,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(charactersFilterList?.get(position))
    }


    // FILTER by name
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    charactersFilterList = items
                } else {
                    val resultList = ArrayList<Character>()
                    items?.forEach {
                        if (it.name?.toLowerCase()?.contains(constraint.toString().toLowerCase()) == true ) {
                            resultList.add(it)
                        }
                    }
                    charactersFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = charactersFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                charactersFilterList = results?.values as? ArrayList<Character>
                notifyDataSetChanged()
            }
        }
    }


    // VIEW HOLDER
    inner class CharactersViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character?) {
            binding.character = character

            binding.cvContainer.setOnClickListener {
                itemClickListener(character)
            }

        }
    }
}

