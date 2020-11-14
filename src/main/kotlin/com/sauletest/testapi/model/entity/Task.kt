package com.sauletest.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "tasks")
class Task(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "serial")
        var id: Long? = null,

        var name: String? = null,

        var description: String = "Default description of the task",

        @OneToOne()
        @JoinColumn(name = "author", referencedColumnName = "id")
        var author: User?,


        @OneToOne()
        @JoinColumn(name = "assignee", referencedColumnName = "id")
        var assignee: User?
)
