package com.example.collspantoolbarlayoutinmultiview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.collspantoolbarlayoutinmultiview.databinding.ActivityExampleBinding
import com.google.android.material.appbar.AppBarLayout

class ExampleActivity : AppCompatActivity() {


    private val binding: ActivityExampleBinding by lazy {
        DataBindingUtil.setContentView<ActivityExampleBinding>(this, R.layout.activity_example)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainAppBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                val container = binding.collapsingContents
                val toolbar = binding.toolbar
                appBarLayout?.let {
                    if (verticalOffset == 0) {
                        if (state != CollapsingToolbarLayoutState.EXPANDED) {
                            state = CollapsingToolbarLayoutState.EXPANDED
                            container.alpha = 1f
                            toolbar.alpha = 0f
                        }

                    } else if (Math.abs(verticalOffset) >= it.totalScrollRange) {
                        if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                            state = CollapsingToolbarLayoutState.COLLAPSED
                            container.alpha = 0f
                            toolbar.alpha = 1f
                        }
                    } else {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            container.alpha = 0.5f
                            toolbar.alpha = 0f
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE
                    }
                }

            }
        })
    }

    private var state: CollapsingToolbarLayoutState = CollapsingToolbarLayoutState.EXPANDED

    private enum class CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ExampleActivity::class.java)
    }
}
