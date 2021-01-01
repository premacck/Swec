package com.prembros.stateawareepoxycontroller.controller

import com.prembros.stateawareepoxycontroller.loaderItem
import com.prembros.stateawareepoxycontroller.swecListItem
import com.swapi.models.Vehicle

/**
 * Prem's creation, on 01/01/21
 */
class VehicleController : SwecController<Vehicle>() {

  override fun onLoadingState() = loaderItem { id("Loader item for $this") }

  override fun loadData(data: List<Vehicle>?) {
    data?.forEachIndexed { index, vehicle ->
      swecListItem {
        id("Vehicle item for ${this@VehicleController} at $index position")
        withVehicle(vehicle)
      }
    }
  }
}