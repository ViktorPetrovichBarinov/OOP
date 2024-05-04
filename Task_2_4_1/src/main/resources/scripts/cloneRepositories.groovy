package scripts

import groovy.transform.Field

def config = new GroovyClassLoader().parseClass(
        new File(this.class.getResource("/scripts/students.groovy").getFile())
).newInstance()

def cloneRepos(LinkedHashMap students) {
    println "Student's ids list:" + students.keySet()

    for (id in students.keySet()) {
        pathToCloning = "repositories/${id}"

        student = students.get(id)
        repository = student.repository
        username = student.username
        println("Program are trying to clone repository ${repository} " +
                "to the \"${pathToCloning}\" directory " +
                "of ${username}" +
                "(\"${id}\" - student id).")

        gitCommand = "git clone ${student.repository} ${pathToCloning}"
        println("Git command: ${gitCommand}")
        def cloning = gitCommand.execute()
        def exitCode = cloning.waitFor()
        switch (exitCode) {
            case 0:
                println "Git command was successfully completed.\n"
                break
            case 128:
                println "Git command wasn't completed."
                def errorStream = cloning.errorStream
                def errorMessage = errorStream?.text
                println "Error message ${errorMessage}"
                break
            case 129:
                println "Git command wasn't completed."
                println "Too many arguments in git command.\n" +
                        "Check students configuration file, " +
                        "it don't need to consist gaps in ID " +
                        "and repository have to consist link to working repository."
                break
            default:
                println "Git command wasn't completed."
                def errorStream = cloning.errorStream
                def errorMessage = errorStream?.text
                println "Unexpected error. Check error message:\n ${errorMessage}"
                break

        }

    }

}
cloneRepos(config.students)