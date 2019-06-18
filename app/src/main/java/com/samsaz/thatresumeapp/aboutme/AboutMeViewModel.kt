package com.samsaz.thatresumeapp.aboutme

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.samsaz.shared.util.BaseViewModel
import com.samsaz.shared.util.CoroutineDispatchers
import com.samsaz.shared.util.Result
import com.samsaz.shared.util.tryResult
import com.samsaz.thatresumeapp.R
import com.samsaz.thatresumeapp.base.ui.ViewLoadingState
import com.samsaz.thatresumeapp.base.ui.ViewStateProvider
import com.samsaz.thatresumeapp.data.DataStateListener
import com.samsaz.thatresumeapp.model.AboutMe
import com.samsaz.thatresumeapp.model.Social
import com.samsaz.thatresumeapp.util.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class AboutMeViewModel @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val repository: AboutMeRepository
) : BaseViewModel(dispatchers), ViewStateProvider<AboutMe>, DataStateListener<AboutMe> {

    override val liveData: MutableLiveData<AboutMe> = MutableLiveData()
    override val loadingLiveData: MutableLiveData<ViewLoadingState> = MutableLiveData()
    val messageLiveData = SingleLiveEvent<String>()

    init {
        refresh()
    }

    override fun refresh() {
        launch {
            repository.loadData(this@AboutMeViewModel)
        }
    }

    override fun onDataChange(data: AboutMe) {
        liveData.value = data
    }

    override fun onStateChange(state: ViewLoadingState) {
        loadingLiveData.value = state
    }

    fun socialClick(context: Context?, social: Social?) {
        if (context == null || social == null) {
            return
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(social.link))
        val result = tryResult { context.startActivity(intent) }
        if (result is Result.Error) {
            messageLiveData.value = context.getString(R.string.noBrowserInstalled)
        }
    }
}