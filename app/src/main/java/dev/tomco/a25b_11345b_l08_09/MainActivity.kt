package dev.tomco.a25b_11345b_l08_09

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.tomco.a25b_11345b_l08_09.adapters.MovieAdapter
import dev.tomco.a25b_11345b_l08_09.databinding.ActivityMainBinding
import dev.tomco.a25b_11345b_l08_09.interfaces.MovieCallback
import dev.tomco.a25b_11345b_l08_09.model.DataManager
import dev.tomco.a25b_11345b_l08_09.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieAdapter = MovieAdapter(DataManager.generateMovieList())

        movieAdapter.movieCallback = object :MovieCallback{
            override fun favoriteButtonClicked(movie: Movie, position: Int) {
                movie.isFavorite = !movie.isFavorite
                movieAdapter.notifyItemChanged(position)
            }

        }

        binding.mainRVList.adapter = movieAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.mainRVList.layoutManager = linearLayoutManager
    }
}