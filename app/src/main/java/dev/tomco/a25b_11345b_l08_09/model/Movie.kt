package dev.tomco.a25b_11345b_l08_09.model

import java.time.LocalDate

data class Movie private constructor(
    val poster: String,
    val genre: List<String>,
    val actors: List<String>,
    val name: String,
    val length: Int,
    val overview: String,
    val releaseDate: LocalDate,
    val rating: Float,
    var isFavorite: Boolean,
    var isCollapsed: Boolean = false
) {

    fun toggleCollapse() = apply { this.isCollapsed = !this.isCollapsed }

    class Builder(
        var poster: String = "",
        var genre: List<String> = emptyList(),
        var actors: List<String> = emptyList(),
        var name: String = "",
        var length: Int = 0,
        var overview: String = "",
        var releaseDate: LocalDate = LocalDate.now(),
        var rating: Float = 0.0F,
        var isFavorite: Boolean = false
    ) {
        fun poster(poster: String) = apply { this.poster = poster }
        fun genre(genre: List<String>) = apply { this.genre = genre }
        fun actors(actors: List<String>) = apply { this.actors = actors }
        fun name(name: String) = apply { this.name = name }
        fun length(length: Int) = apply { this.length = length }
        fun overview(overview: String) = apply { this.overview = overview }
        fun releaseDate(releaseDate: LocalDate) = apply { this.releaseDate = releaseDate }
        fun rating(rating: Float) = apply { this.rating = rating }
        fun isFavorite(isFavorite: Boolean) = apply { this.isFavorite = isFavorite }
        fun build() = Movie(
            poster,
            genre,
            actors,
            name,
            length,
            overview,
            releaseDate,
            rating,
            isFavorite
        )
    }
}
