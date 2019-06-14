package com.samsaz.thatresumeapp.skills

import android.view.ViewGroup
import com.samsaz.thatresumeapp.base.ui.BaseRecyclerAdapter
import com.samsaz.thatresumeapp.model.Skill

class SkillAdapter : BaseRecyclerAdapter<Skill, SkillViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        return SkillViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}