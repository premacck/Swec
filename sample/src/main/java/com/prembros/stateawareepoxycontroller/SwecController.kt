package com.prembros.stateawareepoxycontroller

import android.os.Parcelable
import com.prembros.swec.SwecAdapterList
import com.swapi.models.*

/**
 * Prem's creation, on 31/12/20
 */
open class SwecController<T : Parcelable> : SwecAdapterList<T>() {

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

class FilmController : SwecController<Film>() {

  override fun onLoadingState() = loaderItem { id("Loader item for $this") }

  override fun onSuccessState(data: List<Film>?) {
    loadData(data)
  }

  override fun loadData(data: List<Film>?) {
    data?.forEachIndexed { index, film ->
      swecListItem {
        id("Film item for ${this@FilmController} at $index position")
        withFilm(film)
      }
    }
  }
}

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