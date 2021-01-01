package com.prembros.stateawareepoxycontroller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.airbnb.epoxy.EpoxyRecyclerView
import com.prembros.stateawareepoxycontroller.controller.*
import com.prembros.swapi.sample_components.*
import com.prembros.swapi.sample_components.data.onEndlessScroll
import com.prembros.swapi.sample_components.ui.SwapiListActivity
import com.swapi.models.*
import retrofit2.Response
import timber.log.Timber

/**
 * Prem's creation, on 29/12/20
 */
class SwecListActivity : SwapiListActivity(R.layout.activity_swec_list) {

  private val swecList: EpoxyRecyclerView? by lazy { findViewById(R.id.erv_swec_list) }
  private lateinit var controller: SwecController<*>

  companion object {
    fun launch(from: Context, @SWListType listType: Int) = from.startActivity(Intent(from, SwecListActivity::class.java).putExtra(LIST_TYPE, listType))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, true)
  }

  override fun initRecyclerView() {
    controller = when (listType) {
      LIST_FILMS -> FilmController()
      LIST_PEOPLE -> PeopleController()
      LIST_PLANETS -> PlanetController()
      LIST_SPECIES -> SpeciesController()
      LIST_STARSHIPS -> StarshipController()
      LIST_VEHICLES -> VehicleController()
      else -> throw IllegalStateException("Invalid list type specified. You must specify a valid LIST_TYPE from @SWListType when launching SwecListActivity")
    }
    swecList?.setController(controller)
    endlessScrollListener = swecList?.onEndlessScroll { newPage, _ ->
      this.page = newPage
      controller.setLoadingMoreState()
      getStarWarsData()
    }
  }

  override fun <DATA> onDataResponse(it: Response<SWList<DATA>>) {
    Timber.i("SWAPI list response: $it")
  }

  override fun onAllFilmsResponse(response: Response<SWList<Film>>) {
    (controller as FilmController).addData(response.body()?.results)
  }

  override fun onAllPeopleResponse(response: Response<SWList<People>>) {
    (controller as PeopleController).addData(response.body()?.results)
  }

  override fun onAllPlanetsResponse(response: Response<SWList<Planet>>) {
    (controller as PlanetController).addData(response.body()?.results)
  }

  override fun onAllSpeciesResponse(response: Response<SWList<Species>>) {
    (controller as SpeciesController).addData(response.body()?.results)
  }

  override fun onAllStarshipsResponse(response: Response<SWList<Starship>>) {
    (controller as StarshipController).addData(response.body()?.results)
  }

  override fun onAllVehiclesResponse(response: Response<SWList<Vehicle>>) {
    (controller as VehicleController).addData(response.body()?.results)
  }
}