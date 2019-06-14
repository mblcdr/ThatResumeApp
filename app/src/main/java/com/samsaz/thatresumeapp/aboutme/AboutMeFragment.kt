package com.samsaz.thatresumeapp.aboutme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.samsaz.thatresumeapp.R
import com.samsaz.thatresumeapp.model.AboutMe
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.item_about_me.*
import kotlinx.android.synthetic.main.item_user_info.*
import javax.inject.Inject

class AboutMeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: AboutMeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AboutMeViewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner, Observer<AboutMe> {
            it ?: return@Observer
            setData(it)
        })
        super.onActivityCreated(savedInstanceState)
    }

    fun setData(data: AboutMe) {
        tvName.text = data.name
        tvOccupation.text = data.occupation
        tvAboutMe.text = data.aboutMe
    }
}