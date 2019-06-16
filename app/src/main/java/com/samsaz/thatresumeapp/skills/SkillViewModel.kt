package com.samsaz.thatresumeapp.skills

import androidx.lifecycle.MutableLiveData
import com.samsaz.shared.util.BaseViewModel
import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.data.DataStateListener
import com.samsaz.thatresumeapp.model.Skill
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SkillViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val repository: SkillRepository
) : BaseViewModel(dispatchers), ListViewStateProvider<Skill>, DataStateListener<List<Skill>> {

    override val liveData: MutableLiveData<List<Skill>> = MutableLiveData()
    override val loadingLiveData: MutableLiveData<ViewLoadingState> = MutableLiveData()
    private var latestData: List<Skill> = emptyList()
    private var filter: String? = null
    private var lastFilterJob: Job? = null

    init {
        refresh()
    }

    override fun refresh() {
        launch {
            repository.loadData(this@SkillViewModel)
        }
    }

    override fun onDataChange(data: List<Skill>) {
        latestData = data
        filterList(false)
    }

    override fun onStateChange(state: ViewLoadingState) {
        loadingLiveData.value = state
    }

    private fun filterList(debounce: Boolean) {
        lastFilterJob?.cancel()
        lastFilterJob = launch {
            if (debounce)
                delay(100)
            val filteredList = repository.filterData(latestData, dispatchers, filter)
            liveData.value = filteredList
        }
    }

    fun onSearchQuery(newText: String?) {
        filter = newText
        filterList(true)
    }
}