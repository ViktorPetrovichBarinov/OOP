package scripts

import groovy.transform.Field

def config = new GroovyClassLoader().parseClass(
        new File(this.class.getResource("/scripts/config.groovy").getFile())
).newInstance()

def cloneRepos(LinkedHashMap students) {
    println 'user list:' + students.keySet()

}
cloneRepos(config.students)