package com.test.marvel.domain

data class Character(
        val id : Int,
        val name : String,
        val description : String,
        val thumbnail : String,
        val series : List<String>
)