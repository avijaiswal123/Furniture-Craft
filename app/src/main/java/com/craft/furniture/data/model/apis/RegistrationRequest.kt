package com.craft.furniture.data.model.apis

data class RegistrationRequest(
    var first_name: String?,
    var last_name: String = "",
    var email: String?,
    var phone: String?,
    var password: String?
)
