package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Some text.
 */
public class GradebookTest {
    @Test
    @DisplayName("Constructor test")
    void test1() {
        Mark mark1 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark2 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark3 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark4 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark5 = new Mark("Fundamentals of speech culture",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark6 = new Mark("History",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 4);
        Mark mark7 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 4);
        Mark mark8 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark9 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark10 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark11 = new Mark("Foreign language",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark12 = new Mark("Digital platforms",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark1);
        markList.add(mark2);
        markList.add(mark3);
        markList.add(mark4);
        markList.add(mark5);
        markList.add(mark6);
        markList.add(mark7);
        markList.add(mark8);
        markList.add(mark9);
        markList.add(mark10);
        markList.add(mark11);
        markList.add(mark12);

        HashMap<String, Mark> myDiploma = new HashMap<>();
        myDiploma.put("Digital platforms", mark12);
        myDiploma.put("Foreign language", mark11);
        myDiploma.put("Imperative programming", mark10);
        myDiploma.put("Declarative programming", mark9);
        myDiploma.put("Introduction to Discrete Mathematics and Mathematical Logic", mark8);
        myDiploma.put("Introduction to Algebra and Analysis", mark7);
        myDiploma.put("History", mark6);
        myDiploma.put("Fundamentals of speech culture", mark5);
        Gradebook myGradebook = new Gradebook(markList, Mark.Semester.BACHELOR_COURSE_3_SEMESTER);

        assertEquals(myGradebook.getCurrentSemester(), Mark.Semester.BACHELOR_COURSE_3_SEMESTER);
        assertEquals(myGradebook.getMarkList(), markList);
        assertEquals(myGradebook.getDiploma(), myDiploma);
    }


    @Test
    @DisplayName("Delete method test")
    void test2() {
        Mark mark1 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark2 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark3 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark4 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark5 = new Mark("Fundamentals of speech culture",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark6 = new Mark("History",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 4);
        Mark mark7 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 4);
        Mark mark8 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark9 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark10 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark11 = new Mark("Foreign language",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark12 = new Mark("Digital platforms",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark1);
        markList.add(mark2);
        markList.add(mark3);
        markList.add(mark4);
        markList.add(mark5);
        markList.add(mark6);
        markList.add(mark7);
        markList.add(mark8);
        markList.add(mark9);
        markList.add(mark10);
        markList.add(mark11);
        markList.add(mark12);

        HashMap<String, Mark> myDiploma = new HashMap<>();
        myDiploma.put("Digital platforms", mark12);
        myDiploma.put("Foreign language", mark11);
        myDiploma.put("Imperative programming", mark10);
        myDiploma.put("Declarative programming", mark9);
        myDiploma.put("Introduction to Discrete Mathematics and Mathematical Logic", mark8);
        myDiploma.put("Introduction to Algebra and Analysis", mark7);
        myDiploma.put("History", mark6);
        myDiploma.put("Fundamentals of speech culture", mark5);
        Gradebook myGradebook = new Gradebook(markList, Mark.Semester.BACHELOR_COURSE_3_SEMESTER);

        myGradebook.deleteMark(mark10);

        myDiploma.put("Imperative programming", mark4);
        markList.remove(mark10);

        assertEquals(myGradebook.getMarkList(), markList);
        assertEquals(myGradebook.getDiploma(), myDiploma);
    }

    @Test
    @DisplayName("Average mark")
    void test3() {
        Mark mark1 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark2 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark3 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark4 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark5 = new Mark("Fundamentals of speech culture",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark6 = new Mark("History",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 4);
        Mark mark7 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 4);
        Mark mark8 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark9 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark10 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark11 = new Mark("Foreign language",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark12 = new Mark("Digital platforms",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark1);
        markList.add(mark2);
        markList.add(mark3);
        markList.add(mark4);
        markList.add(mark5);
        markList.add(mark6);
        markList.add(mark7);
        markList.add(mark8);
        markList.add(mark9);
        markList.add(mark10);
        markList.add(mark11);
        markList.add(mark12);

        Gradebook myGradebook = new Gradebook(markList, Mark.Semester.BACHELOR_COURSE_3_SEMESTER);
        assertEquals(Math.round(myGradebook.averageGrade() * 10) / 10.0, 4.8);
    }


    @Test
    @DisplayName("isIncreasedScholarship")
    void test4() {
        Mark mark1 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark2 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark3 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark4 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark5 = new Mark("Fundamentals of speech culture",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark6 = new Mark("History",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 4);
        Mark mark7 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 4);
        Mark mark8 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark9 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark10 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark11 = new Mark("Foreign language",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark12 = new Mark("Digital platforms",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark1);
        markList.add(mark2);
        markList.add(mark3);
        markList.add(mark4);
        markList.add(mark5);
        markList.add(mark6);
        markList.add(mark7);
        markList.add(mark8);
        markList.add(mark9);
        markList.add(mark10);
        markList.add(mark11);
        markList.add(mark12);

        Gradebook myGradebook = new Gradebook(markList, Mark.Semester.BACHELOR_COURSE_3_SEMESTER);

        assertTrue(myGradebook.isIncreasedScholarship());

        myGradebook.addMark(new Mark("test descipline",
                Mark.Semester.BACHELOR_COURSE_3_SEMESTER, 4));
        assertFalse(myGradebook.isIncreasedScholarship());
    }

    @Test
    @DisplayName("isRedDiploma")
    void test5() {
        Mark mark1 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark2 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark3 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark4 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark5 = new Mark("Fundamentals of speech culture",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 5);
        Mark mark6 = new Mark("History",
                Mark.Semester.BACHELOR_COURSE_1_SEMESTER, 4);
        Mark mark7 = new Mark("Introduction to Algebra and Analysis",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 4);
        Mark mark8 = new Mark("Introduction to Discrete Mathematics and Mathematical Logic",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark9 = new Mark("Declarative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark10 = new Mark("Imperative programming",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark11 = new Mark("Foreign language",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        Mark mark12 = new Mark("Digital platforms",
                Mark.Semester.BACHELOR_COURSE_2_SEMESTER, 5);
        ArrayList<Mark> markList = new ArrayList<>();
        markList.add(mark1);
        markList.add(mark2);
        markList.add(mark3);
        markList.add(mark4);
        markList.add(mark5);
        markList.add(mark6);
        markList.add(mark7);
        markList.add(mark8);
        markList.add(mark9);
        markList.add(mark10);
        markList.add(mark11);
        markList.add(mark12);

        Gradebook myGradebook = new Gradebook(markList, Mark.Semester.BACHELOR_COURSE_2_SEMESTER);
        myGradebook.setCurrentSemester(Mark.Semester.BACHELOR_COURSE_3_SEMESTER);
        
        assertEquals(myGradebook.getCurrentSemester(), Mark.Semester.BACHELOR_COURSE_3_SEMESTER);
        assertTrue(myGradebook.isRedDiploma());

        myGradebook.setQualificationWorkGrade(4);
        assertEquals(myGradebook.getQualificationWorkGrade(), 4);
        assertFalse(myGradebook.isRedDiploma());
    }
}
