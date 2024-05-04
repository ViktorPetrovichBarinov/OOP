package scripts

import groovy.transform.Field
import org.example.Student

@Field
students = [
        "student1": [
                username: "Ruslan Chudinov",
                repository: "https://github.com/ViktorPetrovichBarinov/OOP"
        ],
        "student2": [
                username: "Rustam Khamidullin",
                repository: "https://github.com/Rustam-Khamidullin/OOP"
        ]
]

println students

