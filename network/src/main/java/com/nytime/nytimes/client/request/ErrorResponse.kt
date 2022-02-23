package com.nytime.nytimes.client.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ErrorResponse : Serializable {
    @SerializedName(value = "returnCode", alternate = ["code"])
    private var errorCode: String? = null

    @SerializedName(value = "returnCodeDesc", alternate = ["desc"])
    private var errorMessage: String? = null

    @SerializedName(value = "returnCodeTitle", alternate = ["title"])
    private var errorTitle: String? = null

    constructor() {
        // Empty constructor
    }

    constructor(errorCode: String?) {
        this.errorCode = errorCode
    }

    fun setErrorMessage(errorMessage: String?) {
        this.errorMessage = errorMessage
    }

    fun setErrorTitle(errorTitle: String?) {
        this.errorTitle = errorTitle
    }

    override fun toString(): String {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}'
    }
}
