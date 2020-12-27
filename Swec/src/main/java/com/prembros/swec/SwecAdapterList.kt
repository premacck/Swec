package com.prembros.swec

import com.airbnb.epoxy.EpoxyController

/**
 * Prem's creation, on 28/12/20
 *
 * Easy parent class for [EpoxyController]s that have List of data,
 * and when you are feeling too lazy to write `SwecAdapter<List<DataModel>` again & again
 * and instead just want to Simplify it to `SwecAdapterList<DataModel>`.
 */
abstract class SwecAdapterList<CLASS_OF_LIST>(sameErrorAndEmptyStates: Boolean = true) :
    SwecAdapter<List<CLASS_OF_LIST>>(sameErrorAndEmptyStates)