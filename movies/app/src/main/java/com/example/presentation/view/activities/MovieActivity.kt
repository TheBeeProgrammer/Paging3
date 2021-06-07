package com.example.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.R
import com.example.presentation.view.fragments.MovieDeatilFragment
import com.example.presentation.view.fragments.MovieFragment

class MovieActivity : AppCompatActivity(), MovieFragment.CallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        addFragment(currentFragment)
    }

    private fun addFragment(currentFragment: Any?) {
        if (currentFragment == null) {
            val fragment = MovieFragment.newInstance()
            supportFragmentManager
                .beginTransaction().setCustomAnimations(R.anim.slide_in, 0, 0, 0)
                .add(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    override fun navigateToMovieDetail(movieId: Int?) {
        val fragment = MovieDeatilFragment.newInstance(movieId)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }

}