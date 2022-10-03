package com.rentguruz.app.b2b.galadariauto.backgroundtasks

interface AcuantTokenServiceListener {
    fun onSuccess(token: String)
    fun onFail(responseCode: Int)
}