package com.example.list.fragments

import com.example.list.objects.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainFragmentRepository {
    private var listCard = mutableListOf<Card>()
    private var deletedCard = mutableListOf<Card>()
    private var lastIndex = 15

    fun initList(): List<Card> {
        (1..15).forEach { listCard.add(Card(it)) }
        return listCard
    }

    fun delete(position: Int): List<Card> {
        val a = listCard[position].id
        deletedCard += deletedCard + listCard.filterIndexed { index, _ -> index == position }
            .toMutableList()
        listCard = listCard.filterIndexed { index, _ -> index != position }.toMutableList()
        return listCard
    }


    suspend fun addCard(): List<Card> = withContext(Dispatchers.Default) {
        if (deletedCard.isEmpty()) {
            lastIndex++
            val position = try {
                (listCard.indices).random()
            } catch (ex: Exception) {
                0
            }
            listCard.add(
                position,
                Card(lastIndex)
            )
            listCard.toList()
        } else {
            listCard.add(deletedCard.removeFirst())
            listCard.toList()
        }
    }
}