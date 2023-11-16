package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Some text.
 */
public class Gradebook {
    private final List<Mark> markList = new ArrayList<>();
    private Mark.Semester currentSemester;

    /**
     * Some text.
     *
     * @param mark  - Some text.
     */
    public void addGrade(Mark mark) {
        if (currentSemester.compareTo(mark.getSemester()) < 0) {
            throw new IllegalArgumentException();
        }
        for (Mark tmpMark : markList) {
            if (tmpMark.getSemester() == mark.getSemester()
                && tmpMark.getNameOfDiscipline().equals(mark.getNameOfDiscipline())) {
                throw new IllegalArgumentException();
            }
        }
        markList.add(mark);
    }




}
