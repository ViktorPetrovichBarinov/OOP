package scripts

import org.gradle.tooling.GradleConnector
import org.jsoup.Jsoup

//Получаем конфиги для студентов и для тасок
def engine = new GroovyScriptEngine("scripts/")
def scriptClass = engine.loadScriptByName("tasks.groovy")
def taskConfig = scriptClass.getDeclaredConstructor().newInstance()
scriptClass = engine.loadScriptByName("students.groovy")
def studentsConfig = scriptClass.getDeclaredConstructor().newInstance()

def evaluate(Set students, String lab) {
    println "-------------------"
    println lab + ':'

    def results = new LinkedHashMap()
    for (student in students) {
        def connector = GradleConnector.newConnector()

        studentPath = new File("./repositories/" + student);
        //println(studentPath)

        println "----------"
        println "${student}:"
        //println "Testing:"

        def studentResults = [
                build: '-',
                javadoc: '-',
                test: '-'
        ]

        def labPath = "./repositories/" + student + '/' + lab
        connector.forProjectDirectory(new File(labPath))
        def connection = connector.connect()
        def build = connection.newBuild()


        //build
        try {
            build.forTasks('build')
                    .run()
            studentResults['build'] = '+'
            println "Building +"
        } catch (Exception e) {
            println "Building -"
        }

        //coverage
        try {
            build.forTasks('test')
                    .addArguments('-i')
                    .run()
            def link = labPath + '/build/reports/tests/test/index.html'
            def testSummary = new File(link)
            def document = Jsoup.parse(testSummary)

            def value = document.getElementById("successRate").select("div.percent").first().text()
            println 'TESTS COMPLETED + ' + value

            studentResults['test'] = value
            //studentResults['summaryHTML'] = document.getElementById("content").outerHtml()
        } catch (Exception e) {
            println 'TESTS FAILED - '
        }

        //docs
        try {
            build.forTasks('javadoc')
                    .run()
            studentResults['javadoc'] = '+'
            println "JAVADOC +"
        } catch (Exception e) {
            println "Javadoc -"
        }

        results[student] = studentResults
    }
    return results;
}


//Клонируем репы
def shell = new GroovyShell()
def script = shell.parse(new File("cloneRepositories.groovy"))
script.run()

def labResults = new LinkedHashMap();
for (lab in taskConfig.tasks) {
    def labResult = evaluate(studentsConfig.students.keySet(), lab);
    labResults[lab] = labResult
}

println "-------------"

return labResults