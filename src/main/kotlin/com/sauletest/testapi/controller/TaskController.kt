package com.sauletest.testapi.controller

import com.sauletest.testapi.model.entity.Task
import com.sauletest.testapi.repository.TaskRepository
import com.sauletest.testapi.repository.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/tasks")
class TaskController(val taskRepository: TaskRepository, val userRepository: UserRepository) {

    @GetMapping
    fun getTasks() = taskRepository.findAll()

    @RequestMapping(path = [("/{id}")], method = [(RequestMethod.GET)])
    fun getTask(@PathVariable("id") id: Long): Optional<Task>? {
        if (!taskRepository.existsById(id)) {
            throw TaskNotFoundException("Task with id $id does not exist")
        }

        return taskRepository.findById(id)
    }

    @PostMapping
    fun newTask(@RequestBody task: Task): Long? {
        if (task.name.isNullOrBlank()) {
            throw InvalidRequestException("name is missing")
        }

        if (!userRepository.existsById(task.author.id)) {
            throw UserNotFoundException("User with id ${task.author.id} does not exist")
        }

        if (!userRepository.existsById(task.assignee.id)) {
            throw UserNotFoundException("User with id ${task.assignee.id} does not exist")
        }


        taskRepository.save(task)
        return task.id
    }

    @RequestMapping(path = [("/{id}")], method = [(RequestMethod.PUT)])
    fun updateTask(@PathVariable("id") id: Long, @RequestBody task: Task) {
        val currentTask: Task = taskRepository.findById(id).orElseThrow { TaskNotFoundException("Task with id $id does not exist")}

        if (task.name.isNullOrBlank()) {
            throw InvalidRequestException("name is missing")
        }

        if (!userRepository.existsById(task.author.id)) {
            throw UserNotFoundException("User with id ${task.author.id} does not exist")
        }

        if (!userRepository.existsById(task.assignee.id)) {
            throw UserNotFoundException("User with id ${task.assignee.id} does not exist")
        }

        currentTask.name = task.name
        currentTask.description = task.description
        currentTask.author = task.author
        currentTask.assignee = task.assignee

        taskRepository.save(currentTask)
    }

    @RequestMapping(path = [("/{id}")], method = [(RequestMethod.DELETE)])
    fun deleteTask(@PathVariable("id") id: Long) {
        if (!taskRepository.existsById(id)) {
            throw TaskNotFoundException("Task with id $id does not exist")
        }

        taskRepository.deleteById(id)
    }
}