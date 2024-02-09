package org.example.util

object Validator {
    const val PHONE_NUMBER_ERROR = "Phone number error"
    const val EMAIL_ERROR = "Email error"

    @JvmStatic
    fun isPhoneNumberValid(phoneNumber: String) = phoneNumber.matches(Regex("""^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}${'$'}"""))

    @JvmStatic
    fun isEmailValid(email: String) = email.matches(Regex("""(?!(^[.-].*|[^@]*\.@|.*\.{2,}.*)|^.{254}.)([a-zA-Z0-9!#${'$'}%&'*+\/=?^_`{|}~.-]+@)(?!-.*|.*-\.)([a-zA-Z0-9-]{1,63}\.)+[a-zA-Z]{2,15}"""))
}