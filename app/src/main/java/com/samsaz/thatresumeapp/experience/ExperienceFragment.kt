package com.samsaz.thatresumeapp.experience

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.samsaz.thatresumeapp.base.ui.BaseListFragment
import com.samsaz.thatresumeapp.base.ui.BaseRecyclerAdapter
import com.samsaz.thatresumeapp.base.ui.ListViewStateProvider
import com.samsaz.thatresumeapp.model.Experience
import com.samsaz.thatresumeapp.util.analytics.AnalyticsHelper
import javax.inject.Inject

class ExperienceFragment : BaseListFragment<Experience>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var analyticsHelper: AnalyticsHelper
    lateinit var viewModel: ExperienceViewModel
    val adapter = ExperienceAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analyticsHelper.sendScreenView("Experience", requireActivity())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ExperienceViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
    }

    override fun getListViewStateProvider(): ListViewStateProvider<Experience>? {
        return viewModel
    }

    override fun getAdapter(): BaseRecyclerAdapter<Experience, out RecyclerView.ViewHolder> {
        return adapter
    }

}