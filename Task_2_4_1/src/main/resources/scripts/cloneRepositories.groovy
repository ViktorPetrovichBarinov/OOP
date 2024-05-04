package scripts

import groovy.transform.Field

def config = new GroovyClassLoader().parseClass(
        new File(this.class.getResource("/scripts/students.groovy").getFile())
).newInstance()

def cloneRepos(LinkedHashMap students) {
    println "user list:" + students.keySet()

    for (id in students.keySet()) {
        pathToCloning = "repositories/${id}"

        student = students.get(id)
        repository = student.repository
        username = student.username
        println("Program are cloning repository ${repository} " +
                "to the \"${pathToCloning}\" directory " +
                "of ${username}" +
                "(\"${id}\" - student id).")

        gitCommand = "git clone ${student.repository} ${pathToCloning}"
        //print(gitCommand)
        def cloning = gitCommand.execute()
        def help = new StringBuffer()
        clonning.consumeProcessErrorStream(help)
        println help.toString()

    }

}
cloneRepos(config.students)