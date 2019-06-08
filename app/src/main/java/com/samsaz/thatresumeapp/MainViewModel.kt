package com.samsaz.thatresumeapp

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {
    val currentPageLiveData = MutableLiveData<MainViewState>()
        .apply { value = MainViewState("AboutMe", Page.AboutMe) }


    fun changePage(@IdRes itemId: Int) {
        when(itemId) {
            R.id.tab_about_me -> currentPageLiveData.value = MainViewState("AboutMe", Page.AboutMe)
            R.id.tab_experience -> currentPageLiveData.value = MainViewState("Experience", Page.Experience)
        }
    }

    enum class Page {
        AboutMe, Experience
    }
}