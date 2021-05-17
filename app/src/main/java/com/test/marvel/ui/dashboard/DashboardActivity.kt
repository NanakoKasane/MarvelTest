package com.test.marvel.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.marvel.App
import com.test.marvel.R
import com.test.marvel.databinding.ActivityDashboardBinding
import com.test.marvel.ui.dashboard.di.DashboardComponent
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardComponent: DashboardComponent
    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory
    private val viewModel: DashboardViewModel by viewModels { viewmodelFactory }
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        dashboardComponent = (application as App).appComponent.dashboardComponent().create()
        dashboardComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.viewmodel = viewModel
        viewModel.model.observe(this,  Observer(::updateUi) )

        loadLoginView()
    }

    private fun loadLoginView(){
//        supportFragmentManager.beginTransaction()
//                .replace(
//                        R.id.dashboard_content,
//                )
//                .commit()
    }

    private fun updateUi(uiModel : DashboardViewModel.UiModel) {
        viewModel.hideLoading()
        when (uiModel) {
//            is OnboardingViewModel.UiModel.NavigateLogin -> loadLoginView()
//            is OnboardingViewModel.UiModel.NavigatePinRecover -> loadPinRecoverView()
//            is OnboardingViewModel.UiModel.NavigateConnectionTutorialBegin -> loadConnectionTutorialBeginView()
//            is OnboardingViewModel.UiModel.NavigateBluetoothList -> loadBluetoothListView()
//            is OnboardingViewModel.UiModel.NavigateDeviceConnected -> loadDeviceConnectedView()
//            is OnboardingViewModel.UiModel.NavigateDashboard -> loadDashboard()
        }
    }


}