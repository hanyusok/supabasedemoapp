package com.example.supabasedemo.di

import androidx.compose.ui.res.stringResource
import com.example.supabasedemo.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = stringResource(R.string.SUPABASE_URL),
            supabaseKey = stringResource(R.string.ANON_KEY)

        ) {
            install(Postgrest)
            install(GoTrue) {
                flowType = FlowType.PKCE
                scheme = "app"
                host = "supabase.com"
            }
            install(Storage)
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    @Singleton
    fun provideSupabaseGoTrue(client: SupabaseClient): GoTrue {
        return client.gotrue
    }

    @Provides
    @Singleton
    fun provideSupabaseStorage(client: SupabaseClient): Storage {
        return client.storage
    }
}