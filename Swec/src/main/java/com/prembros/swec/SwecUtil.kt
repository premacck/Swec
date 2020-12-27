package com.prembros.swec

import androidx.annotation.IntDef

/**
 * Prem's creation, on 28/12/20
 */

const val STATE_LOADING = 0
const val STATE_SUCCESS = 1
const val STATE_EMPTY = 2
const val STATE_ERROR = 3

@Retention @IntDef(STATE_LOADING, STATE_SUCCESS, STATE_EMPTY, STATE_ERROR) annotation class LoadState
