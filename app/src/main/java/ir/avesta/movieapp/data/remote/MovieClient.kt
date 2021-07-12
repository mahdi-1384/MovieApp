package ir.avesta.movieapp.data.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieClient {
    companion object {

        private var INSTANCE: Retrofit? = null
        private val baseUrl = "https://www.omdbapi.com/"

        private val gson = GsonBuilder()
            .setLenient()
            .disableHtmlEscaping()
            .create()

        fun getClient(): Retrofit? {
            if (INSTANCE == null)
                INSTANCE = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(baseUrl)
                    .build()

            return INSTANCE
        }
    }
}