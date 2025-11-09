package edu.javieh.simpsonsapi.application.features.domain

sealed class ErrorApp : Throwable() {
    object ServerError : ErrorApp()
    object InternetError : ErrorApp()
}