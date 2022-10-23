# EncryptedDataStore

[![](https://jitpack.io/v/mobidroid92/EncryptedDataStore.svg)](https://jitpack.io/#mobidroid92/EncryptedDataStore)

It is a library that let you use an encrypted DataStore securily and easily.
Also you can customize encryption as your needs by implementing **EncryptionHelper** interface and pass it to **EncryptedDataStore**.

# Compatibility
Minimum Android SDK: 26

# Setup
Add to your root build.gradle:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Add the dependency:

	dependencies {
	    implementation 'com.github.mobidroid92:EncryptedDataStore:1.0.1'
	}

# Usage

	val encryptedDataStore = EncryptedDataStore(BaseApplication.context!!)
	val key = stringPreferencesKey("key_name")
	viewModelScope.launch {
	    encryptedDataStore.putString(key, "Hello World")
	    val storedValue = encryptedDataStore.getString(key).first()
	}

- The instance of **EncryptedDataStore** has to be maintained as Singleton in your code.
