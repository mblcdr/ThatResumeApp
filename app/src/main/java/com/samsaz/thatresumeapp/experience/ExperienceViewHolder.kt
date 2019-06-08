package com.samsaz.thatresumeapp.experience

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsaz.thatresumeapp.R
import com.samsaz.thatresumeapp.model.Experience
import kotlinx.android.synthetic.main.item_experience.view.*

class ExperienceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): ExperienceViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_experience, parent, false)
            return ExperienceViewHolder(view)
        }
    }

    fun bind(item: Experience) = with(itemView) {
        tvDuration.text = item.duration
        tvJobTitle.text = item.title
        tvJobDescription.text = item.description

        if (item.companyName != null) {
            tvCompanyName.text = item.companyName
            tvCompanyName.visibility = View.VISIBLE
        } else {
            tvCompanyName.visibility = View.GONE
        }
        if (item.companyImageUrl != null) {
            //TODO Load company image
            ivCompanyImage.visibility = View.VISIBLE
        } else {
            ivCompanyImage.visibility = View.GONE
        }
    }
}