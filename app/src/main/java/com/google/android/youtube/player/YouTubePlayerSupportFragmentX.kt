package com.google.android.youtube.player

import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.internal.ab
import com.google.android.youtube.player.internal.e

open class YouTubePlayerSupportFragmentX: Fragment(), YouTubePlayer.Provider {
    private val aField: ViewBundle = ViewBundle()
    private var b: Bundle? = null
    private var c: YouTubePlayerView? = null
    private var d: String? = null
    private var e: YouTubePlayer.OnInitializedListener? = null
    private val f = false

    companion object {
        fun newInstance(): YouTubePlayerSupportFragmentX {
            return YouTubePlayerSupportFragmentX()
        }
    }

    override fun initialize(var1: String?, var2: YouTubePlayer.OnInitializedListener?) {
        d = ab.a(var1, "Developer key cannot be null or empty")
        e = var2
        aFunc()
    }

    private fun aFunc() {
        if (c != null && e != null) {
            c?.a(f)
            c?.a(this.activity, this, d, e, b)
            b = null
            e = null
        }
    }

    override fun onCreate(var1: Bundle?) {
        super.onCreate(var1)
        b = var1?.getBundle("YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        c = YouTubePlayerView(activity, null, 0, aField)
        aFunc()

        return c
    }

    override fun onStart() {
        super.onStart()
        c?.a()
    }

    override fun onResume() {
        super.onResume()
        c?.b()
    }

    override fun onPause() {
        c?.c()
        super.onPause()
    }

    override fun onSaveInstanceState(var1: Bundle) {
        super.onSaveInstanceState(var1)
        (if (c != null) c?.e() else b)?.let { var2 ->
            var1.putBundle("YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE", var2)
        }
    }

    override fun onStop() {
        c?.d()
        super.onStop()
    }

    override fun onDestroyView() {
        activity?.let { c?.c(it.isFinishing) }
        c = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        if (c != null) {
            val var1 = this.activity
            c?.b(var1 == null || var1.isFinishing)
        }
        super.onDestroy()
    }

    private inner class ViewBundle : YouTubePlayerView.b {
        override fun a(
            p0: YouTubePlayerView?,
            p1: String?,
            p2: YouTubePlayer.OnInitializedListener?
        ) {
            e?.let { initialize(p1, it) }
        }

        override fun a(p0: YouTubePlayerView?) {

        }
    }
}