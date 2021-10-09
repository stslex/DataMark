package stslex.datamark.di.module

import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.data.core.DataResponse
import stslex.datamark.ui.core.UIResponse

@ExperimentalCoroutinesApi
@Module
interface ResponseModule {

    @Binds
    fun bindsDataResponse(response: DataResponse.Base): DataResponse

    @Binds
    fun bindsUIResponse(response: UIResponse.Base): UIResponse
}