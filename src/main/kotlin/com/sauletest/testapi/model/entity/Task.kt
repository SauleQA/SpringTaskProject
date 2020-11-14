package com.sauletest.testapi.model.entity

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tasks")
class Task(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "serial")
        var id: Long? = null,

        @NotEmpty
        @NotNull
        var name: String? = null,

        var description: String? = "Default description of the task",

        @OneToOne()
        @JoinColumn(name = "author", referencedColumnName = "id")
        @NotEmpty
        @NotNull
        var author: User?,


        @OneToOne()
        @JoinColumn(name = "assignee", referencedColumnName = "id")
        @NotEmpty
        @NotNull
        var assignee: User?
)
