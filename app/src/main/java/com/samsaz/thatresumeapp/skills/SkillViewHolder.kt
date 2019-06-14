package com.samsaz.thatresumeapp.skills

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.samsaz.thatresumeapp.R
import com.samsaz.thatresumeapp.model.Skill
import kotlinx.android.synthetic.main.item_skill.view.*

class SkillViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    @ColorInt
    val defaultRibbonColor: Int

    companion object {
        fun create(parent: ViewGroup): SkillViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)
            return SkillViewHolder(view)
        }
    }

    init {
        defaultRibbonColor = ContextCompat.getColor(itemView.context, R.color.defaultRibbonColor)
    }

    fun bind(item: Skill) = with(itemView) {
        tvSkillName.text = item.name
        tvSkillDescription.text = item.description
        val color = if (item.color != null) {
            Color.parseColor(item.color)
        } else {
            defaultRibbonColor
        }
        ribbonView.setBackgroundColor(color)
        if (item.imageLink != null) {
            //TODO Load Skill Image
            ivSkillImage.visibility = View.VISIBLE
        } else {
            ivSkillImage.visibility = View.GONE
        }
    }
}