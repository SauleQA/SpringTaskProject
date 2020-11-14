package com.sauletest.testapi.service

import com.sauletest.testapi.controller.InvalidRequestException
import com.sauletest.testapi.controller.TaskNotFoundException
import com.sauletest.testapi.controller.UserNotFoundException
import com.sauletest.testapi.model.entity.Task
import com.sauletest.testapi.model.entity.User
import com.sauletest.testapi.repository.TaskRepository
import com.sauletest.testapi.repository.UserRepository
import com.sauletest.testapi.request.SaveTaskRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskService(private val taskRepository: TaskRepository, private val userRepository: UserRepository) {

    fun getTasks() = taskRepository.findAll()

    fun getTask(id: Long): Optional<Task> {
        if (!taskRepository.existsById(id)) {
            throw TaskNotFoundException("Task with id $id does not exist")
        }

        return taskRepository.findById(id)
    }

    fun addTask(request: SaveTaskRequest): Long? {
        when {
            request.name.isNullOrBlank() -> throw InvalidRequestException("name is missing")
            request.authorId == null -> throw InvalidRequestException("author is missing")
            request.assigneeId == null -> throw InvalidRequestException("assignee is missing")
        }

        val author: User = userRepository.findById(request.authorId!!).orElseThrow { UserNotFoundException("User with id ${request.authorId} does not exist")}
        val assignee: User = userRepository.findById(request.assigneeId!!).orElseThrow { UserNotFoundException("User with id ${request.assigneeId} does not exist")}


        val task = taskRepository.save(
                Task(description = request.description ?: "Default description of the task",
                assignee = author,
                author = assignee,
                name = request.name
                )
        )
        return task.id
    }

    fun updateTask(id: Long, request: SaveTaskRequest): Task {
        val currentTask: Task = taskRepository.findById(id).orElseThrow { TaskNotFoundException("Task with id $id does not exist")}

        if (request.authorId != null) {
            val author: User = userRepository.findById(request.authorId).orElseThrow { UserNotFoundException("User with id ${request.authorId} does not exist")}
            currentTask.author = author
        }

        if (request.assigneeId != null) {
            val assignee: User = userRepository.findById(request.assigneeId).orElseThrow { UserNotFoundException("User with id ${request.assigneeId} does not exist")}
            currentTask.assignee = assignee
        }

        if (request.description != null) {
            currentTask.description = request.description
        }

        if (request.name != null) {
            currentTask.name = request.name
        }

        taskRepository.save(currentTask)

        return currentTask
    }

    fun deleteTask(id: Long) {
        if (!taskRepository.existsById(id)) {
            throw TaskNotFoundException("Task with id $id does not exist")
        }

        taskRepository.deleteById(id)
    }

}