package com.samsaz.thatresumeapp.experience

import androidx.lifecycle.MutableLiveData
import com.samsaz.shared.util.BaseViewModel
import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.shared.util.Result
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.model.Experience
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExperienceViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val dataSource: ExperienceAssetDataSource
) : BaseViewModel(dispatchers), ListViewStateProvider<Experience> {

    override val listData: MutableLiveData<List<Experience>> = MutableLiveData()
    override val loadingState: MutableLiveData<ViewLoadingState> = MutableLiveData()
    //val scope = CoroutineScope(dispatchers.main)

    init {
        refresh()
    }

    override fun refresh() {
        launch {
            when (val result = dataSource.getData()) {
                is Result.Success -> listData.value = result.data
            }
            loadingState.value = ViewLoadingState.Success
        }
    }

}