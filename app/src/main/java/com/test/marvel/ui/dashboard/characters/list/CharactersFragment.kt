package com.test.marvel.ui.dashboard.characters.list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.test.marvel.R
import com.test.marvel.databinding.FragmentCharactersBinding
import com.test.marvel.domain.Character
import com.test.marvel.ui.dashboard.DashboardActivity
import com.test.marvel.ui.dashboard.DashboardViewModel
import javax.inject.Inject


class CharactersFragment : Fragment(){
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory
    lateinit var binding : FragmentCharactersBinding
    private val dashboardViewModel : DashboardViewModel by activityViewModels{ viewModelFactory }
    private val viewModel : CharactersViewModel by viewModels { viewModelFactory }
    private var adapter : CharactersAdapter? = null


    companion object {
        fun newInstance()  = CharactersFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as DashboardActivity).dashboardComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characters, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        setOnQuerySearchListener()

        viewModel.model.observe(this, Observer(::updateUi) )
        viewModel.getCharacters()
    }



    private fun updateUi(uiModel : CharactersViewModel.UiModel){
        when (uiModel){
            is CharactersViewModel.UiModel.Loading -> {
                dashboardViewModel.showLoading()
            }

            is CharactersViewModel.UiModel.FailureMoviesList -> {
                dashboardViewModel.hideLoading()
                Toast.makeText(context, getString(R.string.err_obtain_characters), Toast.LENGTH_SHORT).show()
                binding.tvNoMovies.visibility = View.VISIBLE
            }

            // Movie list obtained
            is CharactersViewModel.UiModel.SuccessMoviesList -> {
                dashboardViewModel.hideLoading()
                binding.tvNoMovies.visibility = View.INVISIBLE

                setupRecyclerView(uiModel.movies)
            }

        }
    }

    private fun setupRecyclerView(movies : List<Character>){
        adapter = CharactersAdapter(movies) {
            // item click listener
            // TODO:
            // dashboardViewModel.navigateToCharacterDetail(it)
        }
        binding.rvMovies.layoutManager = GridLayoutManager(activity, 2) // 2 rows
        binding.rvMovies.adapter  = adapter
    }


    private fun setOnClickListener(){

    }

    private fun setOnQuerySearchListener(){
        binding.searchview.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }
        })

    }

    override fun onDestroy() {
        dashboardViewModel.hideLoading()
        super.onDestroy()
    }


}