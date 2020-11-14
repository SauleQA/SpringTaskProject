package com.sauletest.testapi.request

data class SaveTaskRequest (
    val name: String?,

    val description: String?,

    val authorId: Long?,

    val assigneeId: Long?
)