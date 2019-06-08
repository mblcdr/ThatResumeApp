package com.samsaz.thatresumeapp.experience

import android.view.ViewGroup
import com.samsaz.thatresumeapp.base.ui.BaseRecyclerAdapter
import com.samsaz.thatresumeapp.model.Experience

class ExperienceAdapter : BaseRecyclerAdapter<Experience, ExperienceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        return ExperienceViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}