package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Some text.
 */
public class Gradebook {
    private final List<Mark> markList = new ArrayList<>();
    private final HashMap<String, Mark> diploma = new HashMap<>();
    private Mark.Semester currentSemester;
    private int qualificationWorkGrade;
    /**
     * Some text.
     *
     * @param mark  - Some text.
     */
    public void addGrade(Mark mark) {
        //если пытаемся добавить оценку с семестра на котором ещё не учились = ошибка
        if (currentSemester.compareTo(mark.getSemester()) < 0) {
            throw new IllegalArgumentException();
        }
        //если для данной дисциплины и данного значения уже есть оценка = ошибка
        for (Mark tmpMark : markList) {
            if (tmpMark.getSemester() == mark.getSemester()
                && tmpMark.getNameOfDiscipline().equals(mark.getNameOfDiscipline())) {
                throw new IllegalArgumentException();
            }
        }
        //если в дипломе уже содержиться балл для данной дисциплины, то
        if(diploma.containsKey(mark.getNameOfDiscipline())) {
            Mark.Semester lastSemesterForCurrentDiscipline = diploma.get(mark.getNameOfDiscipline()).getSemester();
            //мы проверяем, что семестр записанный в дипломе меньше, чем семестр, который мы хотим записать
            if (lastSemesterForCurrentDiscipline.compareTo(mark.getSemester()) < 0) {
                diploma.put(mark.getNameOfDiscipline(), mark);
            }
        }
        markList.add(mark);
    }

    public double averageGrade() {
        int summaryOfGrades = 0;
        int numberOfGrades = 0;
        for (Mark tmpMark : markList) {
            numberOfGrades++;
            summaryOfGrades += tmpMark.getGrade();
        }

        return  (double)summaryOfGrades / numberOfGrades;
    }

    public boolean isIncreasedScholarship() {
        for (Mark tmpMark : markList) {
            if (tmpMark.getSemester() == currentSemester
                && tmpMark.getGrade() != 5) {
                return false;
            }
        }
        return true;
    }

    public boolean isRedDiploma() {
        int numberOfDiplomaDegree = diploma.size();
        int numberOfDiplomaExcellentDegree = 0;
        for (Mark tmpMark : diploma.values()) {
            if (tmpMark.getGrade() == 5) {
                numberOfDiplomaExcellentDegree++;
            }
            if (tmpMark.getGrade() <= 3) {
                return false;
            }
        }
        double relationshipExcellentGradesToAllGrades = (double) numberOfDiplomaExcellentDegree / numberOfDiplomaDegree;
        if (relationshipExcellentGradesToAllGrades < 0.75) {
            return false;
        }
        if (qualificationWorkGrade != 5) {
            return false;
        }
        return true;
    }

    public void setCurrentSemester(Mark.Semester currentSemester) {
        this.currentSemester = currentSemester;
    }

    public void setQualificationWorkGrade(int qualificationWorkGrade) {
        this.qualificationWorkGrade = qualificationWorkGrade;
    }
}
