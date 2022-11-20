package ru.ifmo.pga.software.design.todo.list.entity.enums

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
interface JpaEnum {
    fun getDbKey(): String
}
