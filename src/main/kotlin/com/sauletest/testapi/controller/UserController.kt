package com.sauletest.testapi.controller

import com.sauletest.testapi.model.entity.User
import com.sauletest.testapi.service.UserService
import org.springframework.web.bind.annotation.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import java.util.*

@RestController
@Api(description = "User controller")
@RequestMapping("/v1/users")
class UserController(val userService: UserService) {
    @GetMapping
    @ApiOperation("Get all users")
    fun getUsers(): List<User> = userService.getUsers()

    @RequestMapping(path = [("/{id}")], method = [(RequestMethod.GET)])
    @ApiOperation("Get user by id")
    fun getUser(@PathVariable("id") id: Long): Optional<User>? = userService.getUser(id)
}
