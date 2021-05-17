package com.test.marvel.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.marvel.ui.common.HasLoading
import com.test.marvel.ui.common.HasLoadingImpl
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : ViewModel(), HasLoading by HasLoadingImpl() {
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = _model

    // TODO navigate to ...


    sealed class UiModel {
        object NavigateLogin : UiModel()
        object NavigatePinRecover : UiModel()

        object NavigateConnectionTutorialBegin : UiModel()
        object NavigateBluetoothList : UiModel()
        object NavigateDeviceConnected : UiModel()
        object NavigateDashboard : UiModel()
        data class GetMessagesFailure(val message: String) : UiModel()
    }
}