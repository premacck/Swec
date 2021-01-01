package com.prembros.stateawareepoxycontroller.controller

import android.os.Parcelable
import com.prembros.stateawareepoxycontroller.loadMoreItem
import com.prembros.swec.StateAwareEpoxyControllerList
import com.swapi.models.*

/**
 * Prem's creation, on 31/12/20
 */
open class SwecController<T : Parcelable> : StateAwareEpoxyControllerList<T>() {

  init {
    setLoadingState()
  }

  override fun onLoadingMoreState(data: List<T>) {
    loadData(data)
    loadMoreItem { id("Load More Item for ${this@SwecController}") }
  }

  override fun onSuccessState(data: List<T>?) {
    loadData(data)
  }

  fun addData(data: List<T>?) = currentData.orEmpty().toMutableList().apply { addAll(data.orEmpty()) }.let { newData -> setData(newData) }

  open fun loadData(data: List<T>?) {}
}