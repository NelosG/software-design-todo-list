package ru.ifmo.pga.software.design.todo.list.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import ru.ifmo.pga.software.design.todo.list.entity.Task
import ru.ifmo.pga.software.design.todo.list.entity.enums.Status
import ru.ifmo.pga.software.design.todo.list.service.TaskService
import javax.servlet.http.HttpSession

@Controller
class TaskController @Autowired constructor(
    private val taskService: TaskService,
) {
    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun getList(
        @RequestParam(name = "id", required = true) id: Long,
        model: Model,
        session: HttpSession
    ): String {
        model.addAttribute("tasks", taskService.findByTaskListId(id))
        model.addAttribute("newTask", Task())
        model.addAttribute("TO_DO", Status.TO_DO)
        session.setAttribute("taskListId", id)
        return "tasks"
    }

    @RequestMapping(value = ["/list/add-task"], method = [RequestMethod.POST])
    fun addTask(
        @SessionAttribute(name = "taskListId", required = true) taskListId: Long,
        @ModelAttribute("newTask") newTask: Task
    ): String {
        taskService.save(newTask.apply { this.taskListId = taskListId })
        return "redirect:/list?id=${newTask.taskListId}"
    }

    @RequestMapping(value = ["/list/delete"], method = [RequestMethod.GET])
    fun deleteTask(
        @RequestParam(name = "id", required = true) id: Long,
        model: Model
    ): String {
        val task = taskService.findById(id) ?: error("Task wasn't found")
        taskService.remove(id)
        return "redirect:/list?id=${task.taskListId}"
    }

    @RequestMapping(value = ["/list/change-status"], method = [RequestMethod.GET])
    fun changeTaskStatus(
        @RequestParam(name = "id", required = true) id: Long,
        model: Model
    ): String {
        val task = taskService.findById(id) ?: error("Task wasn't found")
        taskService.save(
            task.apply {
                status = when (task.status) {
                    Status.TO_DO -> Status.DONE
                    Status.DONE -> Status.TO_DO
                }
            })
        return "redirect:/list?id=${task.taskListId}"
    }
}
