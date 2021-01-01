package com.prembros.stateawareepoxycontroller.controller

import com.prembros.stateawareepoxycontroller.loaderItem
import com.prembros.stateawareepoxycontroller.swecListItem
import com.swapi.models.Planet

/**
 * Prem's creation, on 01/01/21
 */
class PlanetController : SwecController<Planet>() {

  override fun onLoadingState() = loaderItem { id("Loader item for $this") }

  override fun loadData(data: List<Planet>?) {
    data?.forEachIndexed { index, planet ->
      swecListItem {
        id("Planet item for ${this@PlanetController} at $index position")
        withPlanet(planet)
      }
    }
  }
}