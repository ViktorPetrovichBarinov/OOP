package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kohsuke.args4j.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotebookApp {

    @Option(name = "-add", usage = "Добавить запись")
    private List<String> addArgs;

    @Option(name = "-rm", usage = "Удалить запись")
    private String removeArg;

    @Option(name = "-show", usage = "Показать записи")
    private List<String> showArgs;

    private List<Note> notes;

    public static void main(String[] args) {
        new NotebookApp().run(args);
    }

    public void run(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            loadNotes(); // Загрузить записи из файла JSON

            if (addArgs != null) {
                addNote();
            } else if (removeArg != null) {
                removeNote();
            } else if (showArgs != null) {
                showNotes();
            }

            saveNotes(); // Сохранить записи в файл JSON

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void loadNotes() throws IOException {
        File file = new File("notes.json");
        ObjectMapper objectMapper = new ObjectMapper();

        if (file.exists()) {
            notes = Arrays.asList(objectMapper.readValue(file, Note[].class));
        } else {
            notes = new ArrayList<>();
        }
    }

    private void saveNotes() throws IOException {
        File file = new File("notes.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, notes);
    }

    private void addNote() {
        if (addArgs.size() < 2) {
            System.err.println("Необходимо указать заголовок и текст для добавления записи.");
            return;
        }

        String title = addArgs.get(0);
        String text = addArgs.get(1);
        Date timestamp = new Date();

        Note note = new Note(title, text, timestamp);
        notes.add(note);

        System.out.println("Запись добавлена: " + note);
    }

    private void removeNote() {
        if (removeArg == null) {
            System.err.println("Необходимо указать заголовок записи для удаления.");
            return;
        }

        notes.removeIf(note -> note.getTitle().equals(removeArg));
        System.out.println("Запись удалена: " + removeArg);
    }

    private void showNotes() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        if (showArgs.size() >= 2) {
            Date startDate = dateFormat.parse(showArgs.get(0));
            Date endDate = dateFormat.parse(showArgs.get(1));

            notes.stream()
                    .filter(note -> note.getTimestamp().after(startDate) && note.getTimestamp().before(endDate))
                    .filter(note -> containsKeywords(note, showArgs.subList(2, showArgs.size())))
                    .sorted(Comparator.comparing(Note::getTimestamp))
                    .forEach(System.out::println);
        } else {
            notes.stream()
                    .sorted(Comparator.comparing(Note::getTimestamp))
                    .forEach(System.out::println);
        }
    }

    private boolean containsKeywords(Note note, List<String> keywords) {
        String title = note.getTitle().toLowerCase();
        for (String keyword : keywords) {
            if (!title.contains(keyword.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    private static class Note {
        private String title;
        private String text;
        private Date timestamp;

        public Note() {
            // Default constructor for Jackson
        }

        public Note(String title, String text, Date timestamp) {
            this.title = title;
            this.text = text;
            this.timestamp = timestamp;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "Note{" +
                    "title='" + title + '\'' +
                    ", text='" + text + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}
