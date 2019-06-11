package com.samsaz.thatresumeapp.experience

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.model.Experience
import javax.inject.Inject

class ExperienceViewModel @Inject constructor(
    val dataSource: ExperienceAssetDataSource
) : ViewModel(), ListViewStateProvider<Experience> {
    override val listData: MutableLiveData<List<Experience>> = MutableLiveData()
    override val loadingState: MutableLiveData<ViewLoadingState> = MutableLiveData()

    init {
        refresh()
    }

    override fun refresh() {
        listData.postValue(dataSource.getData())
        loadingState.postValue(ViewLoadingState.Success)
    }

}