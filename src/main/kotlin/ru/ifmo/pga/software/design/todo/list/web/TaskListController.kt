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
import ru.ifmo.pga.software.design.todo.list.service.TaskListService
import ru.ifmo.pga.software.design.todo.list.service.TaskService
import javax.servlet.http.HttpSession

@Controller
class TaskListController @Autowired constructor(
    private val taskListService: TaskListService,
) {

    @RequestMapping(value = ["/lists"], method = [RequestMethod.GET])
    fun getLists(model: Model): String {
        model.addAttribute("taskLists", taskListService.findAll())
        model.addAttribute("newTaskList", TaskList())
        return "task_lists"
    }

    @RequestMapping(value = ["/lists/add-task-list"], method = [RequestMethod.POST])
    fun addList(@ModelAttribute("newTaskList") newTaskList: TaskList): String {
        taskListService.save(newTaskList)
        return "redirect:/lists"
    }

    @RequestMapping(value = ["/lists/delete"], method = [RequestMethod.GET])
    fun deleteList(
        @RequestParam(name = "id", required = true) id: Long,
        model: Model
    ): String {
        taskListService.remove(id)
        return "redirect:/lists"
    }
}
