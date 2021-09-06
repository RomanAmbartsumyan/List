package com.example.list.list

import androidx.recyclerview.widget.DiffUtil
import com.example.list.objects.Card
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CardAdapter(onDelete: (id: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<Card>(CardDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(CardDelegate(onDelete))
    }

    class CardDiffUtilCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }

    }

}