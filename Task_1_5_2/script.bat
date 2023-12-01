javac -cp "C:\Users\chydi\.gradle\caches\modules-2\files-2.1\*"       -d .\out .\src\main\java\org\example\*.java
javadoc -d .\javadoc .\src\main\java\org\example\*.java
cd .\out
java -cp "C:\Users\chydi\.gradle\caches\modules-2\files-2.1\*"        -classpath . org.example.Main
pause
::-classpath флаг говорит где искать пользовательские файлы