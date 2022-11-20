package ru.ifmo.pga.software.design.todo.list.service.impl

import org.springframework.stereotype.Service
import ru.ifmo.pga.software.design.todo.list.dao.TaskDao
import ru.ifmo.pga.software.design.todo.list.entity.Task
import ru.ifmo.pga.software.design.todo.list.entity.enums.Status
import ru.ifmo.pga.software.design.todo.list.service.TaskService

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
@Service("taskService")
class TaskServiceImpl : NameDescriptionServiceImpl<Task, TaskDao>(), TaskService {
    override fun findByTaskListId(id: Long): List<Task> {
        return invokeDaoMethod(id, TaskDao::findByTaskListId)
    }

    override fun findByTaskListIdAndStatus(id: Long, status: Status): List<Task> {
        return invokeDaoMethod(id, status, TaskDao::findByTaskListIdAndStatus)
    }
}
