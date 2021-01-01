package com.prembros.stateawareepoxycontroller

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView

/**
 * Prem's creation, on 01/01/21
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT) class LoaderItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  init {
    inflate(context, R.layout.item_loader, this)
  }
}