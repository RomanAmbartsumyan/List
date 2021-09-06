package com.example.list.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.list.databinding.CardBinding
import com.example.list.objects.Card
import com.example.list.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class CardDelegate(private val onDelete: (id: Int) -> Unit) :
    AbsListItemAdapterDelegate<Card, Card, CardDelegate.Holder>() {

    override fun isForViewType(item: Card, items: MutableList<Card>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(CardBinding::inflate), onDelete)
    }

    override fun onBindViewHolder(item: Card, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }


    class Holder(
        private val binding: CardBinding,
        onDelete: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.imageButtonDelete.setOnClickListener {
                onDelete(absoluteAdapterPosition)
            }
        }

        fun bind(card: Card) {
            with(binding) {
                textViewId.text = card.id.toString()
            }
        }
    }
}