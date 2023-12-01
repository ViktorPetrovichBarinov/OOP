package org.example;

import static org.example.JsonReader.readObjectsFromJason;

import static org.example.JsonWriter.addObjectToJson;
import static org.example.JsonWriter.writeObjectsToJson;
import static org.example.ParseFromStringToDate.fromStringToDate;

import java.util.Date;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


/**
 * Some text.
 */
public class CommandParser {
    private final String filePath;

    @Option(name = "-add", usage = "Add note.")
    private boolean add;

    @Option(name = "-rm", usage = "Remove note.")
    private boolean remove;

    @Option(name = "-show", usage = "Show note list.")
    private boolean show;

    @Option(name = "-FAQ", usage = "Show FAQ list.")
    private boolean faq;

    @Argument(metaVar = "arguments", usage = "Arguments fot the command.")
    private String[] arguments;

    public CommandParser(String filePath) {
        this.filePath = filePath;
    }

    public void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        setAllFalse();

        try {
            parser.parseArgument(args);

            if (countActiveCommands() > 1) {
                System.out.println(
                        "Error: The application does not support "
                                + "processing multiple commands at once.Enter only one command");
                //parser.printUsage(System.err);
            }

            if (add) {
                addCommandHandle();
            } else if (remove) {
                rmCommandHandle();
            } else if (show) {
                showCommandHandle();
            } else if (faq) {
                faqCommandHandle();

            } else {
                System.err.println("Enter: notebook -FAQ "
                        + "for information about the application commands;");
                //parser.printUsage(System.err);
            }
        } catch (Exception e) {
            //parser.printUsage(System.err);
        }
    }


    private void addCommandHandle() {
        if (arguments == null || arguments.length > 2 || arguments.length == 0) {
            System.err.println("-add have 1 or 2 arguments");
            System.err.println("1 arg: -add \"Note name\"");
            System.err.println("2 arg: -add \"Note name\" \"Note body\"");
            return;
        }
        String name = arguments[0];
        String body;
        if (arguments.length == 1) {
            body = "";
        } else {
            body = arguments[1];
        }
        Date currentDate = new Date();
        Note note = new Note(name, body, currentDate);
        addObjectToJson(note, filePath);
    }

    private void rmCommandHandle() {
        if (arguments == null || arguments.length != 1) {
            System.err.println("-rn have 1 argument");
            System.err.println("1 arg: -rm \"Note name\"");
            return;
        }
        String name = arguments[0];
        List<Note> notes = readObjectsFromJason(filePath);
        if (notes == null) {
            System.err.println("A note with that name was not found");
            return;
        }
        int startNotesLength = notes.size();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getNoteName().equals(name)) {
                notes.remove(i);
                i--;
            }
        }
        if (notes.size() == startNotesLength) {
            System.err.println("A note with that name was not found");
            return;
        }
        writeObjectsToJson(notes, filePath);
    }

    private void showCommandHandle() {
        List<Note> notes = readObjectsFromJason(filePath);
        if (notes == null) {
            System.out.println("You have no notes");
            return;
        }
        if (arguments == null || arguments.length == 0) {
            for (Note note : notes) {
                System.out.println(note);
            }
            return;
        }
        if (arguments.length == 1) {
            System.err.println("-show have 0 or 2 and more argument");
            System.err.println("2 and more arg: -show \"from time\""
                    + " \"to time\" \"keyword1\" \"keyword2\"...");
            return;
        }


        if (arguments.length >= 2) {
            Date from = fromStringToDate(arguments[0]);
            Date to = fromStringToDate(arguments[1]);
            if (from == null || to == null) {
                System.err.println("Date format: dd.MM.yyyy (H)H:mm");
            }

            for (Note note : notes) {
                if (note.getCreateDate().compareTo(from) > 0
                        && note.getCreateDate().compareTo(to) < 0) {
                    if (arguments.length >= 3) {
                        boolean isGoodWord = true;
                        for (int i = 2; i < arguments.length; i++) {
                            if (note.getNoteName().contains(arguments[i])) {
                                continue;
                            }
                            isGoodWord = false;
                            break;
                        }
                        if (isGoodWord) {
                            System.out.println(note);
                        }
                    } else {
                        System.out.println(note);
                    }

                }
            }
        }

    }

    private void faqCommandHandle() {
        System.out.println("\"-add\" - add note to note list");
        System.out.println("\"-rm\" - remove note from note list");
        System.out.println("\"-show\"- show all notes from note list");
    }


    private int countActiveCommands() {
        int count = 0;
        if (add) {
            count++;
        }
        if (remove) {
            count++;
        }
        if (show) {
            count++;
        }
        if (faq) {
            count++;
        }
        return count;
    }

    private void setAllFalse() {
        this.faq = false;
        this.show = false;
        this.remove = false;
        this.add = false;
    }
}
