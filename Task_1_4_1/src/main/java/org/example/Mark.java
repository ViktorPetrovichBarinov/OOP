package org.example;

/**
 * Some text.
 */
public class Mark {

    private String nameOfDiscipline;
    private Semester semester;
    private Integer grade;

    Mark(String nameOfDiscipline, Semester semester, Integer grade) {
        this.nameOfDiscipline = nameOfDiscipline;
        this.semester = semester;
        setGrade(grade);
    }

    public String getNameOfDiscipline() {
        return nameOfDiscipline;
    }

    public Semester getSemester() {
        return semester;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setNameOfDiscipline(String nameOfDiscipline) {
        this.nameOfDiscipline = nameOfDiscipline;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    /**
     * Some text.
     *
     * @param grade - Some text.
     */
    public void setGrade(Integer grade) {
        if (grade < 2 || grade > 5) {
            throw new IllegalArgumentException("Incorrect grade");
        }
        this.grade = grade;
    }

    enum Semester {
        BACHELOR_COURSE_1_SEMESTER,
        BACHELOR_COURSE_2_SEMESTER,
        BACHELOR_COURSE_3_SEMESTER,
        BACHELOR_COURSE_4_SEMESTER,
        BACHELOR_COURSE_5_SEMESTER,
        BACHELOR_COURSE_6_SEMESTER,
        BACHELOR_COURSE_7_SEMESTER,
        BACHELOR_COURSE_8_SEMESTER,
        MASTER_COURSE_1_SEMESTER,
        MASTER_COURSE_2_SEMESTER,
        MASTER_COURSE_3_SEMESTER,
        MASTER_COURSE_4_SEMESTER,
        MASTER_COURSE_5_SEMESTER
    }
}