package dev.tomco.a25b_11345b_l08_09.interfaces

import dev.tomco.a25b_11345b_l08_09.model.Movie

interface MovieCallback {
    fun favoriteButtonClicked(movie: Movie, position: Int)
}