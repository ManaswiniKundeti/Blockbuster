package com.bb.blockbuster.viewstate

import java.lang.StringBuilder

sealed class ViewState<out T>

class Success<out T>(val data: T) : ViewState<T>()

class Error(val  errMsg : String) : ViewState<Nothing>()

object Loading : ViewState<Nothing>()