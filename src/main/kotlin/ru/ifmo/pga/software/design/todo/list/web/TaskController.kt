package ru.ifmo.pga.software.design.todo.list.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttribute
import ru.ifmo.pga.software.design.todo.list.entity.Task
import ru.ifmo.pga.software.design.todo.list.entity.TaskList
import ru.ifmo.pga.software.design.todo.list.entity.enums.Status
import ru.ifmo.pga.software.design.todo.list.service.TaskListService
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
        model.addAttribute("DONE", Status.DONE)
        session.setAttribute("taskListId", id)
        return "tasks"
    }

    @RequestMapping(value = ["/list/add-task"], method = [RequestMethod.POST])
    fun addTask(
        @SessionAttribute(name = "taskListId", required = true) taskListId: Long,
        @ModelAttribute("newTask") newTask: Task
    ): String {
        taskService.save(newTask.apply {  this.taskListId = taskListId})
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

    @RequestMapping(value = ["/list/mark-done"], method = [RequestMethod.GET])
    fun markTaskDone(
        @RequestParam(name = "id", required = true) id: Long,
        model: Model
    ): String {
        val task = taskService.findById(id) ?: error("Task wasn't found")
        taskService.save(task.apply { status = Status.DONE });
        return "redirect:/list?id=${task.taskListId}"
    }

    @RequestMapping(value = ["/list/mark-undone"], method = [RequestMethod.GET])
    fun markTaskUnDone(
        @RequestParam(name = "id", required = true) id: Long,
        model: Model
    ): String {
        val task = taskService.findById(id) ?: error("Task wasn't found")
        taskService.save(task.apply { status = Status.TO_DO });
        return "redirect:/list?id=${task.taskListId}"
    }
}
