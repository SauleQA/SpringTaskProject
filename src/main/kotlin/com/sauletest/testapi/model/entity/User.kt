package com.sauletest.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "serial")
        var id: Long,

        var name: String?
)
