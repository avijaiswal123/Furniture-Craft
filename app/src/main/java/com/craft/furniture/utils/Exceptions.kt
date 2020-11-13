package com.craft.furniture.utils

import java.io.IOException

/**
 * Throw exception due to network issue while api calling.
 * Implemented in [com.craft.furniture.data.remote.NetworkInterceptor] class
 */
class NoInternetException(message: String): IOException(message)
