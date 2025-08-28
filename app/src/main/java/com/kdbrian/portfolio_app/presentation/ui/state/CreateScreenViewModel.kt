package com.kdbrian.portfolio_app.presentation.ui.state

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdbrian.portfolio_app.BuildConfig
import com.kdbrian.portfolio_app.domain.model.Solution
import com.kdbrian.portfolio_app.domain.repo.ImageRepo
import com.kdbrian.portfolio_app.domain.repo.SolutionsRepo
import com.kdbrian.portfolio_app.util.FileInfo
import com.kdbrian.portfolio_app.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber


data class CreateScreenUiState(
    val name: String = "",
    val description: String = "",
    val link: String = "",
    val imageUri: Uri? = Uri.EMPTY,
    val infoGraphic: Uri? = Uri.EMPTY,
    val fileInfo: FileInfo? = null,
)


class CreateScreenViewModel(
    private val solutionsRepo: SolutionsRepo,
    private val imageRepo: ImageRepo
) : ViewModel() {


    private val _solutionName = MutableStateFlow<Resource<List<Solution>>>(Resource.Idle())
    val solutionName = _solutionName.asStateFlow()

    private val _addSolutionState = MutableStateFlow<Resource<Boolean>>(Resource.Idle())
    val addSolutionState = _addSolutionState.asStateFlow()

    private val _createScreenUiState = MutableStateFlow(CreateScreenUiState())
    val createScreenUiState = _createScreenUiState.asStateFlow()

    private val _messages = Channel<Resource<String>> { }
    val messages = _messages.receiveAsFlow()

    fun onNameChange(name: String) {
        _createScreenUiState.value = _createScreenUiState.value.copy(name = name)
    }

    fun onDescriptionChange(description: String) {
        _createScreenUiState.value = _createScreenUiState.value.copy(description = description)
    }

    fun onLinkChange(link: String) {
        _createScreenUiState.value = _createScreenUiState.value.copy(link = link)
    }

    fun onImageUriChange(uri: Uri) {
        _createScreenUiState.value = _createScreenUiState.value.copy(imageUri = uri)
    }

    fun onInfoGraphicUriChange(uri: Uri) {
        _createScreenUiState.value = _createScreenUiState.value.copy(infoGraphic = uri)
    }

    fun onFileInfoChange(fileInfo: FileInfo) {
        _createScreenUiState.value = _createScreenUiState.value.copy(fileInfo = fileInfo)
    }


    fun loadSolutions() {
        _solutionName.value = Resource.Loading()
        viewModelScope.launch {
            solutionsRepo
                .loadSolutions()
                .fold(
                    onSuccess = {
                        _solutionName.value = Resource.Success(it)
                    },
                    onFailure = {
                        _messages.trySend(Resource.Error(it.message.toString()))
                        _solutionName.value = Resource.Error(it.message.toString())
                    }
                )

        }
    }

    fun verify(): Boolean {

        if (_createScreenUiState.value.name.isEmpty()) {
            _messages.trySend(Resource.Error("Name cannot be empty"))
            return false
        }

        if (_createScreenUiState.value.description.isEmpty()) {
            _messages.trySend(Resource.Error("Description cannot be empty"))
            return false
        }


        if (_createScreenUiState.value.link.isEmpty() && !android.util.Patterns.WEB_URL.matcher(
                _createScreenUiState.value.link
            ).matches()
        ) {
            _messages.trySend(Resource.Error("Link cannot be empty"))
            return false
        }

        if (_createScreenUiState.value.imageUri == Uri.EMPTY) {
            _messages.trySend(Resource.Error("Image cannot be empty"))
            return false
        }
        if (_createScreenUiState.value.infoGraphic == Uri.EMPTY) {
            _messages.trySend(Resource.Error("Info Graphic cannot be empty"))
            return false
        }
        return true
    }

    fun createSolution() {
        viewModelScope.launch {
            Timber.d("createSolution")
            _addSolutionState.value = Resource.Loading()

            _messages.trySend(Resource.Success("Verifying"))

            val solution = Solution.Builder()
                .name(_createScreenUiState.value.name)
                .description(_createScreenUiState.value.description)
                .link(_createScreenUiState.value.link)

            //upload logo and info file
            launch {
                if (_createScreenUiState.value.imageUri != null &&
                    _createScreenUiState.value.imageUri != Uri.EMPTY
                ) {
                    imageRepo
                        .uploadImage(
                            imageUri = _createScreenUiState.value.imageUri!!,
                            path = "${BuildConfig.APPLICATION_ID}/images/${_createScreenUiState.value.name}"
                        ).fold(
                            onSuccess = {
                                _messages.trySend(Resource.Success("Logo uploaded"))
                                solution.logo(it)
                            },
                            onFailure = {
                                _addSolutionState.value = Resource.Error(it.message.toString())
                                _messages.trySend(Resource.Error(it.message.toString()))
                                return@launch
                            }
                        )
                }
            }

            launch {
                if (_createScreenUiState.value.infoGraphic != null &&
                    _createScreenUiState.value.infoGraphic != Uri.EMPTY
                ) {
                    imageRepo
                        .uploadImage(
                            imageUri = _createScreenUiState.value.infoGraphic!!,
                            path = "${BuildConfig.APPLICATION_ID}/infoGraphics/${_createScreenUiState.value.name}"
                        ).fold(
                            onSuccess = {
                                _messages.trySend(Resource.Success("Info Graphic uploaded"))
                                solution.infoFile(it)
                            },
                            onFailure = {
                                _addSolutionState.value = Resource.Error(it.message.toString())
                                return@launch
                            }
                        )
                }

            }

            _messages.trySend(Resource.Success("Saving solution"))

            solutionsRepo
                .addSolution(solution = solution.build())
                .fold(
                    onSuccess = {
                        clearForm()
                        _addSolutionState.value = Resource.Success(it)
                    },
                    onFailure = {
                        _addSolutionState.value = Resource.Error(it.message.toString())
                    }
                )

        }
    }

    fun clearForm() {
        _createScreenUiState.value = CreateScreenUiState()
    }


}