package scripts

import groovy.transform.Field

def config = new GroovyClassLoader().parseClass(
        new File(this.class.getResource("/scripts/config.groovy").getFile())
).newInstance()

def cloneRepos(LinkedHashMap students) {
    println 'user list:' + students.keySet()

    for (id in students.keySet()) {
        print id
        pathToCloning = "repositories/${id}"
        println (" " + pathToCloning)

        student = students.get(id)
        println(student.repository)

        def clonning = ('git clone ' + student.repository + ' ' + pathToCloning).execute()
        def help = new StringBuffer()
        clonning.consumeProcessErrorStream(help)
        println help.toString()

    }

}
cloneRepos(config.students)