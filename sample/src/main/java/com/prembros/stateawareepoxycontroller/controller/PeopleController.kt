package com.prembros.stateawareepoxycontroller.controller

import com.prembros.stateawareepoxycontroller.loaderItem
import com.prembros.stateawareepoxycontroller.swecListItem
import com.swapi.models.People

/**
 * Prem's creation, on 01/01/21
 */
class PeopleController : SwecController<People>() {

  override fun onLoadingState() {
    loaderItem { id("Loader item for $this") }
  }

  override fun loadData(data: List<People>?) {
    data?.forEachIndexed { index, people ->
      swecListItem {
        id("People item for ${this@PeopleController} at $index position")
        withPeople(people)
      }
    }
  }
}