package com.kdbrian.portfolio_app.presentation.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdbrian.portfolio_app.domain.repo.AuthRepo
import com.kdbrian.portfolio_app.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false
)

class SignInViewModel(
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _emailPasswordState = MutableStateFlow<Resource<Boolean>>(Resource.Idle())
    val emailPasswordState = _emailPasswordState.asStateFlow()

    private val _createAccountState = MutableStateFlow<Resource<Boolean>>(Resource.Idle())
    val createAccountState = _createAccountState.asStateFlow()


    private val _signInState = MutableStateFlow(SignInUiState())
    val signInState = _signInState.asStateFlow()


    private val _messages = Channel<Resource<String>> { }
    val messages = _messages.receiveAsFlow()


    fun onEmailChanged(email: String) {
        _signInState.value = _signInState.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _signInState.value = _signInState.value.copy(password = password)
    }

    fun onPasswordVisibilityChanged() {
        _signInState.value =
            _signInState.value.copy(isPasswordVisible = !_signInState.value.isPasswordVisible)
    }


    fun emailPasswordSignIn() {
        if (validate()) {
            viewModelScope.launch {
                _emailPasswordState.value = Resource.Loading()

                val result = authRepo.emailPasswordSignIn(
                    email = _signInState.value.email,
                    password = _signInState.value.password
                )

                result.onSuccess {
                    _signInState.value = SignInUiState()
                    _emailPasswordState.value = Resource.Success(true)
                }

                result.onFailure { error ->
                    val message = error.message ?: "Unknown error"
                    Timber.e(error)

//                    if (message.contains("no user record", ignoreCase = true) ||
                    /*
                                            message.contains("not found", ignoreCase = true)) {
                                            // Try creating account
                                            val signUpResult = authRepo.emailPasswordSignUp(
                                                email = _signInState.value.email,
                                                password = _signInState.value.password
                                            )

                                            signUpResult.onSuccess {
                                                _signInState.value = SignInUiState()
                                                _messages.trySend(Resource.Success("Account created and signed in"))
                                                _emailPasswordState.value = Resource.Success(true)
                                            }

                                            signUpResult.onFailure { signUpError ->
                                                _emailPasswordState.value = Resource.Error(signUpError.message.toString())
                                            }
                                        } else {
                    */
                    _emailPasswordState.value = Resource.Error(message)
                }
            }
        }
    }

    fun emailPasswordSignUp() {
        if (validate()) {
            viewModelScope.launch {
                _createAccountState.value = Resource.Loading()

                val result = authRepo.emailPasswordSignUp(
                    email = _signInState.value.email,
                    password = _signInState.value.password
                )

                result.onSuccess {
                    _messages.trySend(Resource.Success("Account created and signed in"))
                    _signInState.value = SignInUiState()
                    _createAccountState.value = Resource.Success(true)
                }

                result.onFailure { error ->
                    val message = error.message ?: "Unknown error"
                    Timber.e(error)
                    _createAccountState.value = Resource.Error(message)
                }

            }
    }
    }

    private fun validate(): Boolean {
        if (_signInState.value.email.isEmpty()) {
            _messages.trySend(Resource.Error("Email cannot be empty"))
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_signInState.value.email).matches()) {
            _messages.trySend(Resource.Error("Invalid email"))
            return false
        }

        if (_signInState.value.password.isEmpty()) {
            _messages.trySend(Resource.Error("Password cannot be empty"))
            return false
        }

        if (_signInState.value.password.length < 6) {
            _messages.trySend(Resource.Error("Password must be at least 6 characters"))
            return false
        }

        return true
    }


}