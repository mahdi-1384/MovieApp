package ir.avesta.movieapp.data.viewModels

import android.text.Editable
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.avesta.movieapp.data.remote.Repository
import ir.avesta.movieapp.data.remote.model.Movie
import ir.avesta.movieapp.data.remote.model.Rating
import ir.avesta.movieapp.util.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repo: Repository) : ViewModel() {

    private val _title = MutableLiveData<String?>()
    val title: MutableLiveData<String?>
        get() = _title

    private val _year = MutableLiveData<String?>()
    val year: MutableLiveData<String?>
        get() = _year

    private val _rated = MutableLiveData<String?>()
    val rated: MutableLiveData<String?>
        get() = _rated

    private val _released = MutableLiveData<String?>()
    val released: MutableLiveData<String?>
        get() = _released

    private val _runtime = MutableLiveData<String?>()
    val runtime: MutableLiveData<String?>
        get() = _runtime

    private val _genre = MutableLiveData<String?>()
    val genre: MutableLiveData<String?>
        get() = _genre

    private val _director = MutableLiveData<String?>()
    val director: MutableLiveData<String?>
        get() = _director

    private val _writer = MutableLiveData<String?>()
    val writer: MutableLiveData<String?>
        get() = _writer

    private val _actors = MutableLiveData<String?>()
    val actors: MutableLiveData<String?>
        get() = _actors

    private val _plot = MutableLiveData<String?>()
    val plot: MutableLiveData<String?>
        get() = _plot

    private val _language = MutableLiveData<String?>()
    val language: MutableLiveData<String?>
        get() = _language

    private val _country = MutableLiveData<String?>()
    val country: MutableLiveData<String?>
        get() = _country

    private val _awards = MutableLiveData<String?>()
    val awards: MutableLiveData<String?>
        get() = _awards

    private val _poster = MutableLiveData<String?>()
    val poster: MutableLiveData<String?>
        get() = _poster

    private val _ratings = MutableLiveData<List<Rating>?>()
    val ratings: MutableLiveData<List<Rating>?>
        get() = _ratings

    private val _metaScore = MutableLiveData<String?>()
    val metaScore: MutableLiveData<String?>
        get() = _metaScore

    private val _imdbRating = MutableLiveData<String?>()
    val imdbRating: MutableLiveData<String?>
        get() = _imdbRating

    private val _imdbVotes = MutableLiveData<String?>()
    val imdbVotes: MutableLiveData<String?>
        get() = _imdbVotes

    private val _imdbId = MutableLiveData<String?>()
    val imdbId: MutableLiveData<String?>
        get() = _imdbId

    private val _type = MutableLiveData<String?>()
    val type: MutableLiveData<String?>
        get() = _type

    private val _dvd = MutableLiveData<String?>()
    val dvd: MutableLiveData<String?>
        get() = _dvd

    private val _boxOffice = MutableLiveData<String?>()
    val boxOffice: MutableLiveData<String?>
        get() = _boxOffice

    private val _production = MutableLiveData<String?>()
    val production: MutableLiveData<String?>
        get() = _production

    private val _website = MutableLiveData<String?>()
    val website: MutableLiveData<String?>
        get() = _website

    private val _response = MutableLiveData<String?>()
    val response: MutableLiveData<String?>
        get() = _response

    val state = MutableLiveData<State>(State.noContent)

    fun getMovieByTitle(value: Editable?) {
        val title = value.toString()

        if (TextUtils.isEmpty(title))
            return

        state.value = State.loadingStart

        repo.getMovieByTitle(title)?.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {

                val movie = response.body()

                state.value = State.loadingFinished

                if (movie?.Response == "False") {
                    state.value = State.movieNotExist
                    return
                }

                if (movie != null) {
                    with(movie) {
                        _title.value = Title
                        _year.value = Year
                        _rated.value = Rated
                        _released.value = Released
                        _runtime.value = Runtime
                        _genre.value = Genre
                        _director.value = Director
                        _writer.value = Writer
                        _actors.value = Actors
                        _plot.value = Plot
                        _language.value = Language
                        _country.value = Country
                        _awards.value = Awards
                        _poster.value = Poster
                        _ratings.value = Ratings
                        _metaScore.value = Metascore
                        _type.value = Type
                        _dvd.value = DVD
                        _boxOffice.value = BoxOffice
                        _production.value = Production
                        _website.value = Website
                        _response.value = Response
                    }
                }
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                state.value = State.error
            }
        })
    }
}


class MainViewModelFactory(private val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}