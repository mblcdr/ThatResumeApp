package com.samsaz.thatresumeapp.experience

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.model.Experience
import javax.inject.Inject

class ExperienceViewModel @Inject constructor() : ViewModel(), ListViewStateProvider<Experience> {
    override val listData: MutableLiveData<List<Experience>> = MutableLiveData()
    override val loadingState: MutableLiveData<ViewLoadingState> = MutableLiveData()

    init {
        refresh()
    }

    override fun refresh() {
        val experience = Experience(
            "Jun 2012 - Aug 2017",
            "Android Developer",
            "I worked on some fancy apps in this role",
            "Google",
            null
        )
        listData.postValue(listOf(experience, experience))
        loadingState.postValue(ViewLoadingState.Success)
    }

}