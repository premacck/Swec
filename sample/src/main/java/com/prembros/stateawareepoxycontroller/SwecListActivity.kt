package com.prembros.stateawareepoxycontroller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.airbnb.epoxy.EpoxyRecyclerView
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
  private lateinit var swecController: SwecController<*>

  companion object {
    fun launch(from: Context, @SWListType listType: Int) = from.startActivity(Intent(from, SwecListActivity::class.java).putExtra(LIST_TYPE, listType))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, true)
  }

  override fun initRecyclerView() {
    swecController = when (listType) {
      LIST_FILMS -> FilmController()
      LIST_PEOPLE -> PeopleController()
      LIST_PLANETS -> PlanetController()
      LIST_SPECIES -> SpeciesController()
      LIST_STARSHIPS -> StarshipController()
      LIST_VEHICLES -> VehicleController()
      else -> throw IllegalStateException("Invalid list type specified. You must specify a valid LIST_TYPE from @SWListType when launching SwecListActivity")
    }
    swecList?.setController(swecController)
    endlessScrollListener = swecList?.onEndlessScroll { newPage, _ ->
      this.page = newPage
      swecController.setLoadingMoreState()
      getStarWarsData()
    }
  }

  override fun <DATA> onDataResponse(it: Response<SWList<DATA>>) {
    Timber.i("SWAPI list response: $it")
  }

  override fun onAllFilmsResponse(response: Response<SWList<Film>>) {
    (swecController as FilmController).addData(response.body()?.results)
  }

  override fun onAllPeopleResponse(response: Response<SWList<People>>) {
    (swecController as PeopleController).addData(response.body()?.results)
  }

  override fun onAllPlanetsResponse(response: Response<SWList<Planet>>) {
    (swecController as PlanetController).addData(response.body()?.results)
  }

  override fun onAllSpeciesResponse(response: Response<SWList<Species>>) {
    (swecController as SpeciesController).addData(response.body()?.results)
  }

  override fun onAllStarshipsResponse(response: Response<SWList<Starship>>) {
    (swecController as StarshipController).addData(response.body()?.results)
  }

  override fun onAllVehiclesResponse(response: Response<SWList<Vehicle>>) {
    (swecController as VehicleController).addData(response.body()?.results)
  }
}