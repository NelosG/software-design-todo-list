package ru.ifmo.pga.software.design.todo.list.dao

import ru.ifmo.pga.software.design.todo.list.entity.NameDescriptionEntity

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
interface NameDescriptionDao<T : NameDescriptionEntity> : Dao<T> {
    fun findByName(name: String): List<T>
}
