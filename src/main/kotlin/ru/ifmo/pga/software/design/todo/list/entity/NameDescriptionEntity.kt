package ru.ifmo.pga.software.design.todo.list.entity

import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
@MappedSuperclass
abstract class NameDescriptionEntity : AbstractEntity() {
    @get:Column(name = "name")
    var name: String? = null

    @get:Column(name = "description")
    var description: String? = null

    companion object {
        const val NAME = "name"
    }
}
