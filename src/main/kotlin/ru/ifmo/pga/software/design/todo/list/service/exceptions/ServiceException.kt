package ru.ifmo.pga.software.design.todo.list.service.exceptions

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
class ServiceException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}
