package com.sauletest.testapi.service

import com.sauletest.testapi.controller.InvalidRequestException
import com.sauletest.testapi.controller.TaskNotFoundException
import com.sauletest.testapi.controller.UserNotFoundException
import com.sauletest.testapi.model.entity.Task
import com.sauletest.testapi.repository.TaskRepository
import com.sauletest.testapi.repository.UserRepository
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

    fun addTask(task: Task): Long? {
        when {
            task.name.isNullOrBlank() -> throw InvalidRequestException("name is missing")
            task.author?.id == null -> throw InvalidRequestException("author is missing")
            task.assignee?.id == null -> throw InvalidRequestException("assignee is missing")
        }

        if (!userRepository.existsById(task.author!!.id)) {
            throw UserNotFoundException("User with id ${task.author?.id} does not exist")
        }

        if (!userRepository.existsById(task.assignee!!.id)) {
            throw UserNotFoundException("User with id ${task.assignee?.id} does not exist")
        }


        taskRepository.save(task)
        return task.id
    }

    fun updateTask(id: Long, task: Task) {
        val currentTask: Task = taskRepository.findById(id).orElseThrow { TaskNotFoundException("Task with id $id does not exist")}

        if (task.name.isNullOrBlank()) {
            throw InvalidRequestException("name is missing")
        }

        if (!userRepository.existsById(task.author!!.id)) {
            throw UserNotFoundException("User with id ${task.author!!.id} does not exist")
        }

        if (!userRepository.existsById(task.assignee!!.id)) {
            throw UserNotFoundException("User with id ${task.assignee!!.id} does not exist")
        }

        currentTask.name = task.name
        currentTask.description = task.description
        currentTask.author = task.author
        currentTask.assignee = task.assignee

        taskRepository.save(currentTask)
    }

    fun deleteTask(id: Long) {
        if (!taskRepository.existsById(id)) {
            throw TaskNotFoundException("Task with id $id does not exist")
        }

        taskRepository.deleteById(id)
    }

}