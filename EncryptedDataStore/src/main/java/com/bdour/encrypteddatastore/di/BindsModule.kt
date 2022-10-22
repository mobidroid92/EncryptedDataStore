package com.bdour.encrypteddatastore.di

import com.bdour.encrypteddatastore.encryption.EncryptionHelper
import com.bdour.encrypteddatastore.encryption.EncryptionHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Singleton
    @Binds
    abstract fun bindEncryption(encryptionHelperImpl: EncryptionHelperImpl): EncryptionHelper

}