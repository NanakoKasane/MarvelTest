package com.test.marvel.data

import com.test.marvel.domain.Character as CharacterDomain
import com.test.marvel.data.server.model.Data as CharacterServer

const val DOT = "."
const val WIKI = "wiki"
const val DETAIL = "detail"
const val COMIC_LINK = "comiclink"

fun CharacterServer.toDomainCharacterList(): List<CharacterDomain> {
    val characterDomainList = arrayListOf<CharacterDomain>()

    this.results.forEach {
        characterDomainList.add(CharacterDomain(it.id, it.name, it.description,
                it.thumbnail?.path + DOT + it.thumbnail?.extension, it.series?.items?.map { it.name },
                it.urls?.find { it.type == WIKI }?.url , it.urls?.find { it.type == DETAIL }?.url, it.urls?.find { it.type == COMIC_LINK }?.url ))
    }
    return characterDomainList
}