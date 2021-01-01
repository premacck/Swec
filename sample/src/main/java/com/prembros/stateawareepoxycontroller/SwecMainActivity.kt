package com.prembros.stateawareepoxycontroller

import com.prembros.swapi.sample_components.*
import com.prembros.swapi.sample_components.ui.SwapiMainActivity

/**
 * Prem's creation, on 31/12/20
 */
class SwecMainActivity : SwapiMainActivity() {

  override fun onFilmsClick() {
    SwecListActivity.launch(this, LIST_FILMS)
  }

  override fun onPeopleClick() {
    SwecListActivity.launch(this, LIST_PEOPLE)
  }

  override fun onPlanetsClick() {
    SwecListActivity.launch(this, LIST_PLANETS)
  }

  override fun onSpeciesClick() {
    SwecListActivity.launch(this, LIST_SPECIES)
  }

  override fun onStarshipsClick() {
    SwecListActivity.launch(this, LIST_STARSHIPS)
  }

  override fun onVehiclesClick() {
    SwecListActivity.launch(this, LIST_VEHICLES)
  }
}