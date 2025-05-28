package dev.tomco.a25b_11345b_l08_09

import android.app.Application
import dev.tomco.a25b_11345b_l08_09.utilities.ImageLoader

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        ImageLoader.init(this)
    }
}