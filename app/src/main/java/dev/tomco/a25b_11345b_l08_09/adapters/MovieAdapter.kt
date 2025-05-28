package dev.tomco.a25b_11345b_l08_09.adapters

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.tomco.a25b_11345b_l08_09.R
import dev.tomco.a25b_11345b_l08_09.databinding.MovieItemBinding
import dev.tomco.a25b_11345b_l08_09.interfaces.MovieCallback
import dev.tomco.a25b_11345b_l08_09.model.Movie
import dev.tomco.a25b_11345b_l08_09.utilities.Constants
import dev.tomco.a25b_11345b_l08_09.utilities.ImageLoader
import dev.tomco.a25b_11345b_l08_09.utilities.TimeFormatter
import java.time.format.DateTimeFormatter
import kotlin.math.max

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieCallback: MovieCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    private fun getItem(position: Int) = movies[position]

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                binding.movieLBLTitle.text = name
                binding.movieLBLReleaseDate.text =
                    releaseDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")).toString()
                binding.movieLBLDuration.text = TimeFormatter.formatTime(length)
                binding.movieLBLGenres.text = genre.joinToString(", ")
                binding.movieLBLActors.text = actors.joinToString(", ")
                binding.movieLBLOverview.text = overview
                binding.movieRBRating.rating = rating / 2
                ImageLoader.getInstance().loadImage(poster, binding.movieIMGPoster)
                if (isFavorite) binding.movieIMGFavorite.setImageResource(R.drawable.heart)
                else binding.movieIMGFavorite.setImageResource(R.drawable.empty_heart)

                binding.movieCVData.setOnClickListener {
                    val animatorSet = ArrayList<ObjectAnimator>()
                    if (isCollapsed){
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLActors,
                                        "maxLines",
                                        binding.movieLBLActors.lineCount
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLActors.lineCount - Constants.DataCard.ACTORS_MIN_LINES).toDouble(),
                                            0.0
                                        ) * 50L).toLong()
                                    )
                            )
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLOverview,
                                        "maxLines",
                                        binding.movieLBLOverview.lineCount
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLOverview.lineCount - Constants.DataCard.OVERVIEW_MIN_LINES).toDouble(),
                                            0.0
                                        ) * 50L).toLong()
                                    )
                            )
                    }else{
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLActors,
                                        "maxLines",
                                        Constants.DataCard.ACTORS_MIN_LINES
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLActors.lineCount - Constants.DataCard.ACTORS_MIN_LINES).toDouble(),
                                            0.0
                                        ) * 50L).toLong()
                                    )
                            )
                        animatorSet
                            .add(
                                ObjectAnimator
                                    .ofInt(
                                        binding.movieLBLOverview,
                                        "maxLines",
                                        Constants.DataCard.OVERVIEW_MIN_LINES
                                    ).setDuration(
                                        (max(
                                            (binding.movieLBLOverview.lineCount - Constants.DataCard.OVERVIEW_MIN_LINES).toDouble(),
                                            0.0
                                        ) * 50L).toLong()
                                    )
                            )
                    }

                    toggleCollapse()
                    animatorSet.forEach { o: ObjectAnimator -> o.start() }

                }

            }
        }
    }


    inner class MovieViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.movieIMGFavorite.setOnClickListener {
                movieCallback?.favoriteButtonClicked(
                    getItem(adapterPosition),
                    adapterPosition
                )
            }
        }
    }
}
