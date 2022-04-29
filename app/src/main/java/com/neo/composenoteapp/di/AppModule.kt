package com.neo.composenoteapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// module for providing bindings or how hilt should get objects

@InstallIn(SingletonComponent::class)  // singleton(useful for room db instance)
@Module
object AppModule {




}