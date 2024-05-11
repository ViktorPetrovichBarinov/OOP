package scripts

import org.apache.commons.io.FileUtils
import org.apache.ivy.util.FileUtil
import org.jsoup.Jsoup

def shell = new GroovyShell()
def script = shell.parse(new File("buildChecker.groovy"))
def results = script.run()

def htmlTemplate = new File(this.class.getResource("/html/template.html").getFile())
def htmlBody = Jsoup.parse(htmlTemplate).toString()
htmlBody += "\n\n"


def margin = 0

for (def lab in results.keySet()) {
    htmlBody += "<table>\n"
    htmlBody += "    <caption>${lab}</caption>"
    htmlBody += """
    <tr>
        <th>Студент</th>
        <th>Сборка</th>
        <th>Документация</th>
        <th>Style</th>
        <th>баллы</th>
    </tr>\n"""
    for (student in results[lab].keySet()) {
        def build = results[lab][student]["build"]
        def javadoc = results[lab][student]["javadoc"]
        def test = results[lab][student]["test"]
        def result;
        if (build == '-' || javadoc == '-' || test == '-') {
            result = "0"
        } else {
            result = "1"
        }
        htmlBody += """
    <tr>
        <td>${student}</td>
        <td>${build}</td>
        <td>${javadoc}</td>
        <td>${test}</td>
        <td>${result}</td>
    </tr>\n"""
    }
    htmlBody += "</table>\n"
}

htmlBody += "</body>\n</html>"

def resultFile = new File("./result.html")
FileUtils.writeStringToFile(resultFile, htmlBody)



println(htmlBody)