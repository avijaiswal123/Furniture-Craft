package com.craft.furniture.utils

import android.util.Log.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class VMFactory @Inject constructor(
    private val providers: Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.NewInstanceFactory()  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider = providers[modelClass]
            ?: providers.asIterable()
                .firstOrNull { modelClass.isAssignableFrom(it.key) }
                ?.value
            ?: throw IllegalArgumentException("Unknown model class: $modelClass")

        try {
            @Suppress("UNCHECKED_CAST") val model = provider.get() as T
            d("TAG", "factory: ${this}, key: ${modelClass}, provider: ${provider}, model: $model")
            return model
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}