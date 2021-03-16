package com.sauletest.testapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MyExceptionController {
    @ExceptionHandler(value = [(TaskNotFoundException::class)])
    fun handleTaskNotFound(ex: TaskNotFoundException): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(
                "Task is not found",
                ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(UserNotFoundException::class)])
    fun handleUserNotFound(ex: UserNotFoundException): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(
                "User is not found",
                ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(UserIsBlockedException::class)])
    fun handleUserIsBlocked(ex: UserIsBlockedException): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(
                "User is not blocked",
                ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(InvalidRequestException::class)])
    fun handleInvalidRequest(ex: InvalidRequestException): ResponseEntity<ErrorsDetails?>? {
        val errorDetails = ErrorsDetails(
                "Incorrect body request",
                ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}

class TaskNotFoundException(override val message: String?) : Exception(message)
class InvalidRequestException(override val message: String?) : Exception(message)
class UserNotFoundException(override val message: String?) : Exception(message)
class UserIsBlockedException(override val message: String?) : Exception(message)


data class ErrorsDetails(val message: String, val details: String)
