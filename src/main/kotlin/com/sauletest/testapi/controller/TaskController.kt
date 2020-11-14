package com.sauletest.testapi.controller

import com.sauletest.testapi.model.entity.Task
import com.sauletest.testapi.request.SaveTaskRequest
import com.sauletest.testapi.service.TaskService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Api(description = "Task controller")
@RequestMapping("/v1/tasks")
class TaskController(val taskService: TaskService) {
    @GetMapping
    @ApiOperation("Get all tasks")
    fun getTasks(): List<Task> = taskService.getTasks()

    @RequestMapping(path = [("/{id}")], method = [(RequestMethod.GET)])
    @ApiOperation("Get task by id")
    fun getTask(@PathVariable("id") id: Long): Optional<Task>? = taskService.getTask(id)

    @PostMapping
    @ApiOperation("Add new task")
    fun addTask(@RequestBody request: SaveTaskRequest): Long? = taskService.addTask(request)

    @RequestMapping(path = [("/{id}")], method = [(RequestMethod.PUT)])
    @ApiOperation("Update existing task")
    fun updateTask(@PathVariable("id") id: Long, @RequestBody request: SaveTaskRequest) = taskService.updateTask(id, request)

    @RequestMapping(path = [("/{id}")], method = [(RequestMethod.DELETE)])
    @ApiOperation("Delete existing task")
    fun deleteTask(@PathVariable("id") id: Long) = taskService.deleteTask(id)
}