package com.prembros.swec

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.Typed2EpoxyController

/**
 * Prem's creation, on 28/12/20
 *
 * Common class for all the [EpoxyController]s that have a [List] and want to be state aware,
 * to handle [STATE_LOADING], [STATE_SUCCESS], [STATE_EMPTY], and [STATE_ERROR] states of data
 */
abstract class StateAwareEpoxyController<DATA_TYPE>(private val sameErrorAndEmptyStates: Boolean = true) : Typed2EpoxyController<DATA_TYPE, Int>() {

  var currentData: DATA_TYPE? = null

  /**
   * Optional convenient override for the child controllers to get the data in respective callback functions directly,
   *  without having to implement the when case themselves
   *
   * This is useful in case the child controller doesn't customise the way the states are implemented here.
   *
   * Note: to remove the unnecessary different name warning in the sub-classes, you can:
   * - Go to Preferences -> Editor -> Inspections and type "Parameter name differs from" in the search box
   * - Select the Severity to "No highlighting", only fix in All Scope
   */
  override fun buildModels(data: DATA_TYPE?, @LoadState loadState: Int) {
    onPreLoadState(data, loadState)
    when (loadState) {
      STATE_LOADING -> onLoadingState()
      STATE_LOADING_MORE -> data?.let { onLoadingMoreState(it) }
      STATE_SUCCESS -> onSuccessState(data)
      STATE_EMPTY -> if (sameErrorAndEmptyStates) onErrorOrEmptyState(data) else onEmptyState()
      STATE_ERROR -> if (sameErrorAndEmptyStates) onErrorOrEmptyState(data) else onErrorState()
    }
    onPostLoadState(data, loadState)
  }

  /**
   * Convenient function to add anything to the controller BEFORE the State aware components are added
   *
   * Works only if [buildModels] of this class is called
   */
  protected open fun onPreLoadState(data: DATA_TYPE?, @LoadState loadState: Int) {}

  /**
   * Convenient function to add anything to the controller AFTER the State aware components are added
   *
   * Works only if [buildModels] of this class is called
   */
  protected open fun onPostLoadState(data: DATA_TYPE?, @LoadState loadState: Int) {}

  /** Optional callback for [STATE_LOADING] state */
  protected open fun onLoadingState() {}

  /**
   * Function to be executed on `onLoadMore()` type function invocations,
   * useful for showing progressbar at the bottom of the list in case of pagination
   */
  protected open fun onLoadingMoreState(data: DATA_TYPE) {}

  /** Optional callback for [STATE_SUCCESS] state */
  protected open fun onSuccessState(data: DATA_TYPE?) {}

  /** Optional callback for [STATE_EMPTY] state. Not called when [sameErrorAndEmptyStates] is true */
  protected open fun onEmptyState() {}

  /** Optional callback for [STATE_ERROR] state. Not called when [sameErrorAndEmptyStates] is true */
  protected open fun onErrorState() {}

  /** Optional callback for [STATE_EMPTY] or [STATE_ERROR] state. This is called only when [sameErrorAndEmptyStates] is true */
  protected open fun onErrorOrEmptyState(data: DATA_TYPE?) {}

  /**
   * Open Function to set the data in [StateAwareEpoxyController] without having to pass in the [LoadState]
   * If the object or List is null, [LoadState] will be [STATE_ERROR]
   * If the object is a list and the List is empty, the [LoadState] will be [STATE_EMPTY]
   * If the object or List is not null or empty, [LoadState] will be [STATE_SUCCESS]
   */
  open fun setData(data: DATA_TYPE?) {
    currentData = data
    setData(
      data, when (data) {
        null -> STATE_ERROR
        is Collection<*> -> when {
          data.isEmpty() -> STATE_EMPTY
          else -> STATE_SUCCESS
        }
        else -> STATE_SUCCESS
      }
    )
  }

  /**
   * Refresh current data, it will refresh the current models, depending upon their changes, or their IDs
   */
  open fun setData() = setData(currentData)

  /**
   * Function to set [STATE_LOADING] in a [StateAwareEpoxyController]
   */
  fun setLoadingState() {
    cancelPendingModelBuild()
    setData(null, STATE_LOADING)
  }

  /**
   * Function to set [STATE_LOADING_MORE] in a [StateAwareEpoxyController]
   */
  fun setLoadingMoreState() {
    cancelPendingModelBuild()
    setData(currentData, STATE_LOADING_MORE)
  }
}