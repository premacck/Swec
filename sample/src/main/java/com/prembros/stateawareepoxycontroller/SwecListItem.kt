package com.prembros.stateawareepoxycontroller

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.swapi.models.*

/**
 * Prem's creation, on 01/01/21
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class SwecListItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

  private val titleLabel: TextView? by lazy { findViewById(R.id.tv_title_label) }
  private val titleText: TextView? by lazy { findViewById(R.id.tv_title) }
  private val subtitleLabel: TextView? by lazy { findViewById(R.id.tv_subtitle_label) }
  private val subtitleText: TextView? by lazy { findViewById(R.id.tv_subtitle) }

  private fun dip(value: Int): Float = (value * context.resources.displayMetrics.density)

  init {
    radius = dip(8)
    cardElevation = dip(4)
    useCompatPadding = true
    setCardBackgroundColor(ContextCompat.getColor(context, R.color.color_bg_card))
    inflate(context, R.layout.item_swec_list, this)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    setOnClickListener {}
  }

  override fun onDetachedFromWindow() {
    setOnClickListener(null)
    super.onDetachedFromWindow()
  }

  @JvmOverloads @ModelProp fun withFilm(item: Film? = null) {
    item ?: return
    titleLabel?.text = context?.getString(R.string.title)
    titleText?.text = item.title
    subtitleLabel?.text = context?.getString(R.string.director)
    subtitleText?.text = item.director
  }

  @JvmOverloads @ModelProp fun withPeople(item: People? = null) {
    item ?: return
    titleLabel?.text = context?.getString(R.string.name)
    titleText?.text = item.name
    subtitleLabel?.text = context?.getString(R.string.birth_year)
    subtitleText?.text = item.birthYear
  }

  @JvmOverloads @ModelProp fun withPlanet(item: Planet? = null) {
    item ?: return
    titleLabel?.text = context?.getString(R.string.name)
    titleText?.text = item.name
    subtitleLabel?.text = context?.getString(R.string.conditions)
    subtitleText?.text = StringBuilder(item.terrain).append("; ").append(item.climate)
  }

  @JvmOverloads @ModelProp fun withSpecies(item: Species? = null) {
    item ?: return
    titleLabel?.text = context?.getString(R.string.name)
    titleText?.text = item.name
    subtitleLabel?.text = context?.getString(R.string.classification)
    subtitleText?.text = StringBuilder(item.classification).append("\nSpeaks ").append(item.language)
  }

  @JvmOverloads @ModelProp fun withStarship(item: Starship? = null) {
    item ?: return
    titleLabel?.text = context?.getString(R.string.name)
    titleText?.text = item.name
    subtitleLabel?.text = context?.getString(R.string.clazz)
    subtitleText?.text = StringBuilder(item.starshipClass).append("\nBy ").append(item.manufacturer)
  }

  @JvmOverloads @ModelProp fun withVehicle(item: Vehicle? = null) {
    item ?: return
    titleLabel?.text = context?.getString(R.string.name)
    titleText?.text = StringBuilder(item.name).append(" - ").append(item.model)
    subtitleLabel?.text = context?.getString(R.string.manufacturer)
    subtitleText?.text = item.manufacturer
  }
}