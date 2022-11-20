package ru.ifmo.pga.software.design.todo.list.service

import ru.ifmo.pga.software.design.todo.list.entity.NameDescriptionEntity

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
interface NameDescriptionService<T : NameDescriptionEntity> : Service<T> {
    fun findByName(name: String): List<T>
}
