javac -d .\out .\main\java\ru\nsu\chudinov\*.java
javadoc -d .\javadoc .\main\java\ru\nsu\chudinov\*.java
cd .\out
java -classpath . ru.nsu.chudinov.Main
pause
::-classpath флаг говорит где искать пользовательские файлы
