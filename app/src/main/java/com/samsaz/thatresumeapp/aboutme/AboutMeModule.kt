package com.samsaz.thatresumeapp.aboutme

import androidx.lifecycle.ViewModel
import com.samsaz.shared.di.scope.FragmentScope
import com.samsaz.shared.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class AboutMeModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeFragment(): AboutMeFragment

    @Binds
    @IntoMap
    @ViewModelKey(AboutMeViewModel::class)
    internal abstract fun bindViewModel(viewModel: AboutMeViewModel): ViewModel

    @Binds
    @Named("remote")
    internal abstract fun bindRemoteDataSource(dataSource: AboutMeRemoteDataSource):
            AboutMeDataSource

    @Binds
    @Named("assets")
    internal abstract fun bindAssetsDataSource(dataSource: AboutMeAssetsDataSource):
            AboutMeDataSource

}