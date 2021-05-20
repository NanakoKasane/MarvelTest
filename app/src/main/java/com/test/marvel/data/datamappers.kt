package com.test.marvel.data

import com.test.marvel.data.server.model.CharactersResponse
import com.test.marvel.domain.Character as CharacterDomain
import com.test.marvel.data.server.model.Data as CharacterServer

const val DOT = "."

fun CharacterServer.toDomainCharacter(): List<CharacterDomain> {
    val characterDomainList = arrayListOf<CharacterDomain>()
    this.results.forEach {
        characterDomainList.add(CharacterDomain(it.id, it.name, it.description,
                it.thumbnail.path + DOT + it.thumbnail.extension, it.series.items.map { it.name }))
    }
    return characterDomainList
}