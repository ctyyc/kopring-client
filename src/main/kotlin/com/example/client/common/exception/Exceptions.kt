package com.example.client.common.exception

class InvalidInputException: RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}

class BizException: RuntimeException {
    constructor(message: String?): super(message)
    constructor(cause: Throwable?): super(cause)
    constructor(message: String?, cause: Throwable?): super(message, cause)
}