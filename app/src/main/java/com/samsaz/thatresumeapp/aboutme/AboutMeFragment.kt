package com.samsaz.thatresumeapp.aboutme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.snackbar.Snackbar
import com.samsaz.thatresumeapp.R
import com.samsaz.thatresumeapp.model.AboutMe
import com.samsaz.thatresumeapp.model.Social
import com.samsaz.thatresumeapp.util.GlideApp
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_about_me.*
import kotlinx.android.synthetic.main.item_about_me.*
import kotlinx.android.synthetic.main.item_user_info.*
import javax.inject.Inject

class AboutMeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: AboutMeViewModel
    private val circleCrop = CircleCrop()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AboutMeViewModel::class.java)
        observeLiveData()
        super.onActivityCreated(savedInstanceState)
    }

    private fun observeLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner, Observer<AboutMe> {
            it ?: return@Observer
            setData(it)
        })
        viewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            showSnack(it)
        })
    }

    private fun showSnack(message: String?) {
        val view = rootView
        if (view == null || message == null)
            return

        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    fun setData(data: AboutMe) {
        tvName.text = data.name
        tvOccupation.text = data.occupation
        tvAboutMe.text = data.aboutMe
        GlideApp.with(ivImage).load(data.imageLink).transform(circleCrop).into(ivImage)
        addSocialIcons(data.socials)
    }

    private fun addSocialIcons(socials: Array<Social>?) {
        socials ?: return
        socialNetworksLayout.removeAllViews()
        val color = ContextCompat.getColor(requireContext(), R.color.socialTint)
        val size = resources.getDimensionPixelSize(R.dimen.socialIconSize)
        val margin = resources.getDimensionPixelOffset(R.dimen.socialIconMargin)

        for (social in socials) {
            val image = ImageView(context)
            val layoutParams = LinearLayout.LayoutParams(size, size)
            layoutParams.marginStart = margin
            layoutParams.marginEnd = margin
            image.layoutParams = layoutParams
            image.contentDescription = social.name
            image.setColorFilter(color)
            image.setOnClickListener {
                viewModel.socialClick(context, social)
            }
            socialNetworksLayout.addView(image)
            GlideApp.with(image).load(social.imageLink).into(image)
        }
    }
}