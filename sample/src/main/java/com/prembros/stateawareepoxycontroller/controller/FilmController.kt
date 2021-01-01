package com.prembros.stateawareepoxycontroller.controller

import com.prembros.stateawareepoxycontroller.loaderItem
import com.prembros.stateawareepoxycontroller.swecListItem
import com.swapi.models.Film

/**
 * Prem's creation, on 01/01/21
 */
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