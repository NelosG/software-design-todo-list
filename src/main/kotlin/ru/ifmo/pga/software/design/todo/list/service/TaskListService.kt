package ru.ifmo.pga.software.design.todo.list.service

import ru.ifmo.pga.software.design.todo.list.entity.TaskList

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
interface TaskListService : NameDescriptionService<TaskList> {
    fun findCountNotDoneTasks(id: Long): Int
}