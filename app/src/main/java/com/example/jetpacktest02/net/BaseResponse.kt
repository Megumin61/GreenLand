package com.example.jetpacktest02.net

import java.io.Serializable

class BaseResponse<T>:Serializable {

    private var msg: String? = null
    private var code: String? = null
    private var data: T? = null

    fun getMessage(): String? {
        return msg
    }

    fun setMessage(message: String?) {
        this.msg = message
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T) {
        this.data = data
    }

    fun getErrCode(): String? {
        return code
    }

    fun setErroCode(erroCode: String?) {
        this.code = erroCode
    }

    override fun toString(): String {
        return "BaseResponse{" +
                ", message='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}'
    }
}