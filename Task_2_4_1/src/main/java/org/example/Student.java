package org.example;

import java.util.Objects;

public class Student {
    private String githubUsername;
    private String fullName;
    private String repositoryUrl;

    public Student(String githubUsername, String fullName, String repositoryUrl) {
        this.githubUsername = githubUsername;
        this.fullName = fullName;
        this.repositoryUrl = repositoryUrl;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(githubUsername, student.githubUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(githubUsername);
    }

    @Override
    public String toString() {
        return "Student{" +
                "githubUsername='" + githubUsername + '\'' +
                ", fullName='" + fullName + '\'' +
                ", repositoryUrl='" + repositoryUrl + '\'' +
                '}';
    }
}