package com.sauletest.testapi.service

import com.sauletest.testapi.controller.UserNotFoundException
import com.sauletest.testapi.model.entity.User
import com.sauletest.testapi.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUsers(): List<User> = userRepository.findAll()

    fun getUser(id: Long): Optional<User> {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException("User with id $id does not exist")
        }

        return userRepository.findById(id)
    }
}