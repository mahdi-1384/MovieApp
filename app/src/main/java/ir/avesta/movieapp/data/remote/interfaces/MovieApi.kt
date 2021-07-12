package ir.avesta.movieapp.data.remote.interfaces

import ir.avesta.movieapp.data.remote.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("?&apikey=dec1db33")
    fun getMovieByTitle(@Query("t") title: String): Call<Movie?>

}