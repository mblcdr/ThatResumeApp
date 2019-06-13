package com.samsaz.thatresumeapp.experience

import androidx.lifecycle.MutableLiveData
import com.samsaz.shared.util.BaseViewModel
import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.shared.util.Result
import com.samsaz.shared.util.tryResult
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.data.Api
import com.samsaz.thatresumeapp.data.CacheMode
import com.samsaz.thatresumeapp.model.Experience
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExperienceViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val api: Api,
    private val dataSource: ExperienceAssetDataSource
) : BaseViewModel(dispatchers), ListViewStateProvider<Experience> {

    override val listData: MutableLiveData<List<Experience>> = MutableLiveData()
    override val loadingState: MutableLiveData<ViewLoadingState> = MutableLiveData()

    init {
        refresh()
    }

    override fun refresh() {
        loadingState.value = ViewLoadingState.Loading
        launch {
            val cachedResult = tryResult { api.getExperiences(CacheMode.Cache) }
            if (cachedResult is Result.Success) {
                listData.value = cachedResult.data
            } else {
                val assetResult = dataSource.getData()
                if (assetResult is Result.Success) {
                    listData.value = assetResult.data
                }
            }

            val networkResult = tryResult { api.getExperiences(CacheMode.Network) }
            if (networkResult is Result.Success) {
                listData.value = networkResult.data
                loadingState.value = ViewLoadingState.Success
            } else {
                loadingState.value = ViewLoadingState.Error("Connection Error")
            }
        }
    }

}