package ir.avesta.movieapp.data.remote.model

import com.google.gson.annotations.Expose

class Movie {

    var Title = ""
    var Year = ""
    var Rated = ""
    var Released = ""
    var Runtime = ""
    var Genre = ""
    var Director = ""
    var Writer = ""
    var Actors = ""
    var Plot = ""
    var Language = ""
    var Country = ""
    var Awards = ""
    var Poster = ""
    var Ratings: List<Rating>? = null
    var Metascore = ""
    var imdbRating = ""
    var imdbVotes = ""
    var imdbID = ""
    var Type = ""
    var DVD = ""
    var BoxOffice = ""
    var Production = ""
    var Website = ""
    var Response = ""
}
