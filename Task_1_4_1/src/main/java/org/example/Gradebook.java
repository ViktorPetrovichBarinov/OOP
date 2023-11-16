package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Some text.
 */
public class Gradebook {
    private final List<Mark> markList = new ArrayList<>();
    private final HashMap<String, Mark> diploma = new HashMap<>();
    private Mark.Semester currentSemester;
    private Integer qualificationWorkGrade = null;

    public Gradebook(List<Mark> markList, Mark.Semester currentSemester) {
        this.currentSemester = currentSemester;
        for (Mark tmpMark : markList) {
            addMark(tmpMark);
        }
    }
    /**
     * Some text.
     *
     * @param mark  - Some text.
     */
    public void addMark(Mark mark) {
        //если пытаемся добавить оценку с семестра на котором ещё не учились = ошибка
        if (currentSemester.compareTo(mark.getSemester()) < 0) {
            throw new IllegalArgumentException("You are not studying yet this semester");
        }
        //если для данной дисциплины и данного значения уже есть оценка = ошибка
        for (Mark tmpMark : markList) {
            if (tmpMark.getNameOfDiscipline().equals(mark.getNameOfDiscipline())
                && tmpMark.getSemester() == mark.getSemester()) {
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
        } else {
            diploma.put(mark.getNameOfDiscipline(), mark);
        }
        markList.add(mark);
    }

    public void deleteMark(Mark mark) {
        String disciplineName = mark.getNameOfDiscipline();
        Mark.Semester markSemester = mark.getSemester();

        if (!diploma.containsKey(disciplineName)) {
            throw new IllegalArgumentException("Incorrect delete mark");
        }
        diploma.remove(disciplineName);
        Mark deleteMark = null;
        for (Mark tmpMark : markList) {
            if (tmpMark.getNameOfDiscipline().equals(disciplineName)
                && tmpMark.getSemester() == markSemester
                && Objects.equals(tmpMark.getGrade(), mark.getGrade())) {
                deleteMark = tmpMark;
                break;
            }
        }
        markList.remove(deleteMark);
        Mark markForDiploma = null;
        for (Mark tmpMark : markList) {
            if(tmpMark.getNameOfDiscipline().equals(disciplineName)) {
                if (markForDiploma == null) {
                    markForDiploma = tmpMark;
                } else if(markForDiploma.getSemester().compareTo(tmpMark.getSemester()) < 0){
                    markForDiploma = tmpMark;
                }
            }
        }
        diploma.put(disciplineName, markForDiploma);

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

        if (qualificationWorkGrade == null) {
            return true;
        } else {
            return qualificationWorkGrade == 5;
        }
    }

    public void setCurrentSemester(Mark.Semester currentSemester) {
        this.currentSemester = currentSemester;
    }

    public void setQualificationWorkGrade(int qualificationWorkGrade) {
        this.qualificationWorkGrade = qualificationWorkGrade;
    }

    public HashMap<String, Mark> getDiploma() {
        return diploma;
    }

    public List<Mark> getMarkList() {
        return markList;
    }

    public Mark.Semester getCurrentSemester() {
        return currentSemester;
    }

    public Integer getQualificationWorkGrade() {
        return qualificationWorkGrade;
    }
}
