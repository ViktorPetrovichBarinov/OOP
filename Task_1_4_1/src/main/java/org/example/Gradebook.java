package org.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Some text.
 */
public class Gradebook {
    private final List<Mark> markList = new ArrayList<>();
    private final HashMap<String, Mark> diploma = new HashMap<>();
    private Mark.Semester currentSemester;
    private Integer qualificationWorkGrade = null;

    /**
     * Some text.
     *
     * @param markList          - Some text.
     * @param currentSemester   - Some text.
     */
    public Gradebook(List<Mark> markList, Mark.Semester currentSemester) {
        this.currentSemester = currentSemester;
        for (Mark tmpMark : markList) {
            addMark(tmpMark);
        }
//        markList.forEach(this::addMark);
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
        if (markList.stream() // преобразуем в поток данных
                .anyMatch(tmpMark ->//если есть хоть одно совпадение вернёт true
                        tmpMark.getNameOfDiscipline().equals(mark.getNameOfDiscipline())
                                && tmpMark.getSemester() == mark.getSemester())) {
            throw new IllegalArgumentException();
        }

        //если в дипломе уже содержиться балл для данной дисциплины, то
        if (diploma.containsKey(mark.getNameOfDiscipline())) {
            Mark.Semester lastSemesterForCurrentDiscipline =
                    diploma.get(mark.getNameOfDiscipline()).getSemester();
            //мы проверяем, что семестр записанный в дипломе меньше,
            //чем семестр, который мы хотим записать
            if (lastSemesterForCurrentDiscipline.compareTo(mark.getSemester()) < 0) {
                diploma.put(mark.getNameOfDiscipline(), mark);
            }
        } else {
            diploma.put(mark.getNameOfDiscipline(), mark);
        }
        markList.add(mark);
    }

    /**
     * Some text.
     *
     * @param mark  - Some text.
     */
    public void deleteMark(Mark mark) {
        String disciplineName = mark.getNameOfDiscipline();
        Mark.Semester markSemester = mark.getSemester();

        if (!diploma.containsKey(disciplineName)) {
            throw new IllegalArgumentException("Incorrect delete mark");
        }
        diploma.remove(disciplineName);
        //ищем оценку, которую надо удалить
        Mark deleteMark = markList.stream()
                .filter(tmpMark ->//фильтруем так, чтобы совпадало имя, семестр и оценка
                        tmpMark.getNameOfDiscipline().equals(disciplineName)
                                && tmpMark.getSemester() == markSemester
                                && Objects.equals(tmpMark.getGrade(), mark.getGrade()))
                .findFirst()//находим первое сопадение
                .orElse(null);//если не находи то ноль, но такого не может быть т.к. выше проверка
        //на то что диплом содержит такую оценку

        //удаляем
        markList.remove(deleteMark);

        Mark markForDiploma;

        markForDiploma = markList.stream()
                .filter(tmpMark ->//ищем все оценки с идентичным названием
                        tmpMark.getNameOfDiscipline().equals(disciplineName))
                .max(Comparator.comparing(Mark::getSemester))//берём оценку за последний семестр
                .orElse(null);


        diploma.put(disciplineName, markForDiploma);

    }

    /**
     * Some text.
     *
     * @return  - Some text.
     */
    public double averageGrade() {
        int summaryOfGrades = 0;
        int numberOfGrades = markList.size();

        summaryOfGrades = markList.stream()
                .mapToInt(Mark::getGrade)
                .sum();

        return (double) summaryOfGrades / numberOfGrades;
    }

    /**
     * Some text.
     *
     * @return  - Some text.
     */
    public boolean isIncreasedScholarship() {
        return markList.stream()
                .filter(tmpMark ->
                        tmpMark.getSemester() == currentSemester)
                .allMatch(tmpMark ->
                        tmpMark.getGrade() == 5);
    }

    /**
     * Some text.
     *
     * @return  - Some text.
     */
    public boolean isRedDiploma() {
        int numberOfDiplomaDegree = diploma.size();
        int numberOfDiplomaExcellentDegree = 0;

        numberOfDiplomaExcellentDegree = (int) diploma.values().stream()
                .filter(tmpMark ->
                        tmpMark.getGrade() == 5)
                .count();

        boolean isFalse = (diploma.values().stream()
                .anyMatch(tmpMark ->
                        tmpMark.getGrade() <= 3));
        if (isFalse) {
            return false;
        }

        double relationshipExcellentGradesToAllGrades =
                (double) numberOfDiplomaExcellentDegree / numberOfDiplomaDegree;
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
