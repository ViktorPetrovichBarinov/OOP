package scripts

class ConfigurationParser {
    static Configuration parse(String configFile) {
        def config = new Configuration()
        return config
    }
}

class Configuration {
    List<Task> tasks = []
    List<Group> groups = []
    List<Checkpoint> checkpoints = []
    // Дополнительные настройки системы

    void addTask(Task task) {
        tasks.add(task)
    }

    void addGroup(Group group) {
        groups.add(group)
    }

    void addCheckpoint(Checkpoint checkpoint) {
        checkpoints.add(checkpoint)
    }
}

class Task {
    String id
    String name
    Date softDeadline
    Date hardDeadline
}

class Group {
    String name
    List<Student> students = []

    void addStudent(Student student) {
        students.add(student)
    }
}

class Student {
    String githubUsername
    String fullName
    String repositoryUrl
}

class Checkpoint {
    String name
    Date date
}