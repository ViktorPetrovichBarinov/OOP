package scripts

import org.apache.commons.io.FileUtils
import org.apache.ivy.util.FileUtil
import org.jsoup.Jsoup

def shell = new GroovyShell()
def script = shell.parse(new File("buildChecker.groovy"))
def results = script.run()

def htmlTemplate = new File(this.class.getResource("/html/template.html").getFile())
def htmlBody = Jsoup.parse(htmlTemplate).toString()



def margin = 0

for (def lab in results.keySet()) {
    htmlBody += "<h1>\n${lab}</h1>\n"
    htmlBody += "<div id=\"content\">"

    for (student in results[lab].keySet()) {
        htmlBody += "<div id=\"content\">\n" + "h1" + student + ""
    }



    htmlBody += "</div>\n"
}

htmlBody += "</body>\n</html>"

def resultFile = new File("./result.html")
FileUtils.writeStringToFile(resultFile, htmlBody)



println(htmlBody)