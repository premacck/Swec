package com.prembros.swec

import androidx.annotation.IntDef

/**
 * Prem's creation, on 28/12/20
 */

const val STATE_LOADING = 0
const val STATE_LOADING_MORE = 1
const val STATE_SUCCESS = 2
const val STATE_EMPTY = 3
const val STATE_ERROR = 4

@Retention @IntDef(STATE_LOADING, STATE_LOADING_MORE, STATE_SUCCESS, STATE_EMPTY, STATE_ERROR) annotation class LoadState
