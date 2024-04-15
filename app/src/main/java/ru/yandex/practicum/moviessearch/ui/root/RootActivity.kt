package ru.yandex.practicum.moviessearch.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.yandex.practicum.moviessearch.R
import ru.yandex.practicum.moviessearch.databinding.ActivityRootBinding
import ru.yandex.practicum.moviessearch.ui.movies.MoviesFragment

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}