package com.prembros.stateawareepoxycontroller.controller

import com.prembros.stateawareepoxycontroller.loaderItem
import com.prembros.stateawareepoxycontroller.swecListItem
import com.swapi.models.Species

/**
 * Prem's creation, on 01/01/21
 */
class SpeciesController : SwecController<Species>() {

  override fun onLoadingState() = loaderItem { id("Loader item for $this") }

  override fun loadData(data: List<Species>?) {
    data?.forEachIndexed { index, species ->
      swecListItem {
        id("Species item for ${this@SpeciesController} at $index position")
        withSpecies(species)
      }
    }
  }
}