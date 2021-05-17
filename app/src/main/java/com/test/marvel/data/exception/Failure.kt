package com.test.marvel.data.exception

sealed class Failure {
    object ServerError : Failure()
    object DatabaseError : Failure()

    class CustomFailure(val message : String) : Failure()
    class IllegalArgumentFailure() : Failure()
}