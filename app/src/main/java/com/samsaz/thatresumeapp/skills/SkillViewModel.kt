package com.samsaz.thatresumeapp.skills

import androidx.lifecycle.MutableLiveData
import com.samsaz.shared.util.BaseViewModel
import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.model.Skill
import kotlinx.coroutines.launch
import javax.inject.Inject

class SkillViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val repository: SkillRepository
) : BaseViewModel(dispatchers), ListViewStateProvider<Skill> {

    override val liveData: MutableLiveData<List<Skill>> = MutableLiveData()
    override val loadingLiveData: MutableLiveData<ViewLoadingState> = MutableLiveData()

    init {
        refresh()
    }

    override fun refresh() {
        launch {
            repository.loadData(liveData, loadingLiveData)
        }
    }

}