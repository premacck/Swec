package com.prembros.stateawareepoxycontroller.controller

import com.prembros.stateawareepoxycontroller.loaderItem
import com.prembros.stateawareepoxycontroller.swecListItem
import com.swapi.models.Starship

/**
 * Prem's creation, on 01/01/21
 */
class StarshipController : SwecController<Starship>() {

  override fun onLoadingState() = loaderItem { id("Loader item for $this") }

  override fun loadData(data: List<Starship>?) {
    data?.forEachIndexed { index, starship ->
      swecListItem {
        id("Starship item for ${this@StarshipController} at $index position")
        withStarship(starship)
      }
    }
  }
}