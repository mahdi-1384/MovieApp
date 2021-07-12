package ir.avesta.movieapp

import android.app.Application
import ir.avesta.movieapp.data.remote.MovieClient
import ir.avesta.movieapp.data.remote.Repository
import ir.avesta.movieapp.data.remote.interfaces.MovieApi

class MyApplication : Application() {

    val movieApi: MovieApi? by lazy { MovieClient.getClient()?.create(MovieApi::class.java) }
    val repository: Repository by lazy { Repository(movieApi) }

}