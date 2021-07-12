package ir.avesta.movieapp.data.remote

import ir.avesta.movieapp.data.remote.interfaces.MovieApi

class Repository(private val api: MovieApi?) {

    fun getMovieByTitle(title: String) = api?.getMovieByTitle(title)
}