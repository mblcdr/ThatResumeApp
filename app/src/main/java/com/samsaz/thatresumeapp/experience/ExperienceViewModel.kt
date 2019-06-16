package com.samsaz.thatresumeapp.experience

import androidx.lifecycle.MutableLiveData
import com.samsaz.shared.util.BaseViewModel
import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.data.DataStateListener
import com.samsaz.thatresumeapp.model.Experience
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExperienceViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val repository: ExperienceRepository
) : BaseViewModel(dispatchers), ListViewStateProvider<Experience>,
    DataStateListener<List<Experience>> {

    override val liveData: MutableLiveData<List<Experience>> = MutableLiveData()
    override val loadingLiveData: MutableLiveData<ViewLoadingState> = MutableLiveData()

    init {
        refresh()
    }

    override fun refresh() {
        launch {
            repository.loadData(this@ExperienceViewModel)
        }
    }

    override fun onDataChange(data: List<Experience>) {
        liveData.value = data
    }

    override fun onStateChange(state: ViewLoadingState) {
        loadingLiveData.value = state
    }
}