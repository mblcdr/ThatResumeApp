package com.samsaz.thatresumeapp.base.ui

sealed class ViewLoadingState {
    object Loading : ViewLoadingState()
    object Success : ViewLoadingState()
    data class Error(val message: String? = null) : ViewLoadingState()
}