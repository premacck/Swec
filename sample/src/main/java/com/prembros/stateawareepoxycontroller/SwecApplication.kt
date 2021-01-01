package com.prembros.stateawareepoxycontroller

import android.app.Application
import com.swapi.StarWarsSdk

/**
 * Prem's creation, on 29/12/20
 */
class SwecApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    StarWarsSdk.init(applicationContext)
  }
}