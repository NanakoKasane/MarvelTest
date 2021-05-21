package com.test.marvel.ui.dashboard.characters.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.marvel.R
import com.test.marvel.databinding.FragmentCharacterDetailBinding
import com.test.marvel.domain.Character
import com.test.marvel.ui.common.setLink
import com.test.marvel.ui.dashboard.DashboardActivity
import com.test.marvel.ui.dashboard.DashboardViewModel
import com.test.marvel.ui.dashboard.characters.list.CharactersViewModel
import javax.inject.Inject


class CharacterDetailFragment : Fragment(){
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    lateinit var binding : FragmentCharacterDetailBinding
    private val dashboardViewModel : DashboardViewModel by activityViewModels{ viewModelFactory }
    private val viewModel : CharactersViewModel by viewModels { viewModelFactory }
    private var characterId : Int? = null
    private var character : Character? = null

    companion object {
        private const val ARG_CHARACTER_ID = "CHARACTER"
        fun newInstance(characterId : Int?) =
                CharacterDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_CHARACTER_ID, characterId ?:0)
                    }
                }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as DashboardActivity).dashboardComponent.inject(this)

        arguments?.let {
            characterId = it.getInt(ARG_CHARACTER_ID)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(this, Observer(::updateUi) )

        viewModel.getCharacterDetail(characterId)
    }


    private fun updateUi(uiModel : CharactersViewModel.UiModel){
        when (uiModel){
            is CharactersViewModel.UiModel.Loading -> {
                dashboardViewModel.showLoading()
            }

            is CharactersViewModel.UiModel.FailureCharacterDetail ->{
                dashboardViewModel.hideLoading()
                Toast.makeText(context, getString(R.string.err_character_detail), Toast.LENGTH_SHORT).show()
            }

            is CharactersViewModel.UiModel.SuccessCharacterDetail -> {
                dashboardViewModel.hideLoading()

                character = uiModel.character
                binding.character = uiModel.character
                binding.tvWebDetail.setLink(character?.detail, getString(R.string.tv_web_detail))
                binding.tvWiki.setLink(character?.wiki, getString(R.string.tv_wiki))
                binding.tvComic.setLink(character?.comiclink, getString(R.string.tv_comic))
            }
        }
    }


    override fun onDestroy() {
        dashboardViewModel.hideLoading()
        super.onDestroy()
    }


}