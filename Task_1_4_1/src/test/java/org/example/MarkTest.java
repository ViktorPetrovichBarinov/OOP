package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Some text.
 */
public class MarkTest {

    @Test
    @DisplayName("getter test")
    void test1() {
        String nameOfDiscipline = "Discrete mathematics";
        Mark.Semester semester = Mark.Semester.BACHELOR_COURSE_1_SEMESTER;
        Integer grade = 5;
        Mark test = new Mark(nameOfDiscipline, semester, grade);

        assertEquals(nameOfDiscipline, test.getNameOfDiscipline());
        assertEquals(semester, test.getSemester());
        assertEquals(grade, test.getGrade());
    }

    @Test
    @DisplayName("setter test")
    void test2() {
        String nameOfDiscipline = "Discrete mathematics";
        Mark.Semester semester = Mark.Semester.BACHELOR_COURSE_1_SEMESTER;
        Integer grade = 5;
        Mark test = new Mark(nameOfDiscipline, semester, grade);

        String nameOfDisciplineNew = "History";
        Mark.Semester semesterNew = Mark.Semester.BACHELOR_COURSE_1_SEMESTER;
        Integer gradeNew = 4;

        test.setNameOfDiscipline(nameOfDisciplineNew);
        test.setSemester(semesterNew);
        test.setGrade(gradeNew);


        assertEquals(nameOfDisciplineNew, test.getNameOfDiscipline());
        assertEquals(semesterNew, test.getSemester());
        assertEquals(gradeNew, test.getGrade());
    }

    @Test
    @DisplayName("grade exception")
    void test3() {
        String nameOfDiscipline = "Discrete mathematics";
        Mark.Semester semester = Mark.Semester.BACHELOR_COURSE_1_SEMESTER;
        Integer grade = 5;
        Mark test = new Mark(nameOfDiscipline, semester, grade);

        Integer gradeNew = 7;

        try {
            test.setGrade(gradeNew);
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect grade", e.getMessage());
        }


    }
}
