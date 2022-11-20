package ru.ifmo.pga.software.design.todo.list.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.pga.software.design.todo.list.dao.TaskListDao
import ru.ifmo.pga.software.design.todo.list.entity.TaskList
import ru.ifmo.pga.software.design.todo.list.entity.enums.Status
import ru.ifmo.pga.software.design.todo.list.service.TaskListService
import ru.ifmo.pga.software.design.todo.list.service.TaskService

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
@Service("taskListService")
class TaskListServiceImpl @Autowired constructor(
    private val taskService: TaskService
) : NameDescriptionServiceImpl<TaskList, TaskListDao>(), TaskListService {

    override fun findCountNotDoneTasks(id: Long): Int {
        return taskService.findByTaskListIdAndStatus(id, Status.TO_DO).count()
    }

    override fun remove(id: Long) {
        taskService.findByTaskListId(id)
            .forEach{
                taskService.remove(it)
            }
        super.remove(id)
    }
}
