package com.samsaz.thatresumeapp

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.samsaz.thatresumeapp.MainViewModel.Page
import com.samsaz.thatresumeapp.aboutme.AboutMeFragment
import com.samsaz.thatresumeapp.experience.ExperienceFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.currentPageLiveData.observe(this, Observer {
            setState(it)
        })
        navView.setOnNavigationItemSelectedListener {
            viewModel.changePage(it.itemId)
            true
        }
    }

    private fun setState(state: MainViewState?) {
        state ?: return

        title = state.title
        val fragment = when(state.page) {
            Page.AboutMe -> AboutMeFragment()
            Page.Experience -> ExperienceFragment()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container_layout, fragment).commit()
    }
}
