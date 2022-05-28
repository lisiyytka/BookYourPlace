package ru.lisiyytka.bookyourplace

import android.app.Application
import com.google.android.gms.common.util.VisibleForTesting
import de.hdodenhof.circleimageview.BuildConfig
import ru.lisiyytka.bookyourplace.di.Scopes
import ru.lisiyytka.bookyourplace.di.appModule
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initToothpick()
        initAppScope(Toothpick.openScope(Scopes.APP_SCOPE))
    }

    @VisibleForTesting
    fun initAppScope(appScope: Scope) {
        appScope.installModules(
            appModule(this)
        )
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().preventMultipleRootScopes())
        }
    }
}