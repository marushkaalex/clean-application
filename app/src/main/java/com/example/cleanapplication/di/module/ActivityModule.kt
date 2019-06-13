package com.example.cleanapplication.di.module

import android.content.Context
import android.widget.Toast
import com.example.cleanapplication.MainActivity
import com.example.cleanapplication.di.interaction.IToaster
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class ActivityModule {

    @Module
    companion object {
        const val ACTIVITY_CONTEXT = "activity_context"

        @Provides
        @JvmStatic
        fun provideToaster(@Named(ACTIVITY_CONTEXT) activityContext: Context): IToaster = object : IToaster {
            override fun showShortToast(message: String) {
                Toast.makeText(activityContext, message, Toast.LENGTH_SHORT).show()
            }

            override fun showLongToast(message: String) {
                Toast.makeText(activityContext, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    @Binds
    @Named(ACTIVITY_CONTEXT)
    abstract fun provideActivityContext(activity: MainActivity): Context
}