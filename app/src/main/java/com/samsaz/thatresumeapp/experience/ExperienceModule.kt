package com.samsaz.thatresumeapp.experience

import androidx.lifecycle.ViewModel
import com.samsaz.shared.di.scope.FragmentScope
import com.samsaz.shared.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
internal abstract class ExperienceModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeFragment(): ExperienceFragment

    @Binds
    @IntoMap
    @ViewModelKey(ExperienceViewModel::class)
    internal abstract fun bindViewModel(viewModel: ExperienceViewModel): ViewModel

    @Binds
    @Named("remote")
    internal abstract fun bindRemoteDataSource(dataSource: ExperienceRemoteDataSource):
            ExperienceDataSource

    @Binds
    @Named("assets")
    internal abstract fun bindAssetsDataSource(dataSource: ExperienceAssetDataSource):
            ExperienceDataSource
}