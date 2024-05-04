package scripts

def engine = new GroovyScriptEngine("scripts/")
def scriptClass = engine.loadScriptByName("students.groovy")
def studentsConfig = scriptClass.getDeclaredConstructor().newInstance()

def cloneRepos(LinkedHashMap students) {
    println "Student's ids list:" + students.keySet()

    for (id in students.keySet()) {
        pathToCloning = "repositories/${id}"

        def student = students.get(id)
        def repository = student.repository
        def username = student.username
        println("Program are trying to clone repository ${repository} " +
                "to the \"${pathToCloning}\" directory " +
                "of ${username}" +
                "(\"${id}\" - student id).")

        def gitCommand = "git clone ${student.repository} ${pathToCloning}"
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

cloneRepos(studentsConfig.students)
