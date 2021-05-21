package com.test.marvel.ui.dashboard.characters.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.marvel.data.functional.fold
import com.test.marvel.data.repository.CharactersRepository
import com.test.marvel.domain.Character
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharactersViewModel @Inject constructor(private val charactersRepository: CharactersRepository) : ViewModel(){
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = _model

    fun getCharacters(){
        _model.value = UiModel.Loading

        GlobalScope.launch {
            val result = charactersRepository.getCharacters()
            result.fold({
                _model.postValue(UiModel.FailureMoviesList)
            }, {
                _model.postValue(UiModel.SuccessMoviesList(it))
            })
        }

    }

    fun getCharacterDetail(id : Int?){
        _model.value = UiModel.Loading

        GlobalScope.launch {
            val result = charactersRepository.getCharacterDetail(id)
            result.fold({
                _model.postValue(UiModel.FailureCharacterDetail)
            }, {
                _model.postValue(UiModel.SuccessCharacterDetail(it))
            })
        }
    }


    sealed class UiModel {
        object Loading : UiModel()
        object FailureMoviesList : UiModel()
        data class SuccessMoviesList(val characters : List<Character>?) : UiModel()

        object FailureCharacterDetail : UiModel()
        data class SuccessCharacterDetail(val character : Character?) : UiModel()
    }


}