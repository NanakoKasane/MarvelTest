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
import com.test.marvel.ui.dashboard.characters.detail.CharacterDetailFragment
import com.test.marvel.ui.dashboard.characters.list.CharactersFragment
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

        loadCharactersView()
    }

    private fun updateUi(uiModel : DashboardViewModel.UiModel) {
        viewModel.hideLoading()
        when (uiModel) {
            is DashboardViewModel.UiModel.NavigateToCharacterDetail -> loadCharacterDetailView(uiModel.id)
        }
    }

    private fun loadCharactersView(){
        supportFragmentManager.beginTransaction()
                .replace(
                        R.id.dashboard_content,
                        CharactersFragment.newInstance(),
                        CharactersFragment::javaClass.name
                )
                .commit()
    }

    private fun loadCharacterDetailView(characterId : Int?){
        supportFragmentManager.beginTransaction()
                .replace(
                        R.id.dashboard_content,
                        CharacterDetailFragment.newInstance(characterId),
                        CharacterDetailFragment::javaClass.name
                )
                .addToBackStack(null)
                .commit()
    }



}