package pe.edu.upc.mark03

import android.app.Application
import android.content.Context

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object{
        private var application: MyApplication? = null

        fun getApplication():Application{
            return application as MyApplication
        }

        fun getContext(): Context {
            return getApplication().applicationContext
        }
    }
}