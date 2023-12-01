package org.example;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.util.Date;
import java.util.List;

import static org.example.JsonReader.readObjectsFromJason;
import static org.example.JsonWriter.*;
import static org.example.parseFromStringToDate.fromStringToDate;


public class CommandParser {
    private final String filePath;

    @Option(name = "-add", usage = "Add note.")
    private boolean add;

    @Option(name = "-rm", usage = "Remove note.")
    private boolean remove;

    @Option(name = "-show", usage = "Show note list.")
    private boolean show;

    @Argument(metaVar = "arguments", usage = "Arguments fot the command.")
    private String[] arguments;

    public CommandParser(String filePath) {
        this.filePath = filePath;
    }

    void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            if (countActiveCommands() > 1) {
                System.out.println(
                        "Error: The application does not support "
                                        + "processing multiple commands at once.Enter only one command");
                parser.printUsage(System.err);
            }

            if (add) {
                addCommandHandle();
            } else if (remove) {
                rmCommandHandle();
            } else if (show) {
                showCommandHandle();
            } else {
                System.err.println("Enter: notebook -FAQ for information about the application commands;");
                parser.printUsage(System.err);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }


    private void addCommandHandle() {
        if (arguments == null || arguments.length > 2 || arguments.length == 0) {
            System.err.println("-add have 1 or 2 arguments"
            + "1 arg: -add \"Note name\""
            + "2 arg: -add \"Note name\" \"Note body\"");
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
            System.err.println("-rn have 1 argument"
                    + "1 arg: -rm \"Note name\"");
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
            System.err.println("-show have 0 or 2 and more argument"
                    + "2 and more arg: -show \"from time\" \"to time\" \"keyword1\" \"keyword2\"...");
            return;
        }


        if (arguments.length >= 2) {
            Date from = fromStringToDate(arguments[0]);
            Date to = fromStringToDate(arguments[1]);
            if (from == null || to == null) {
                System.err.println("Date format: dd.MM.yyyy (H)H:mm");
            }

            for (Note note : notes) {
                if (note.getCreateDate().compareTo(from) > 0 && note.getCreateDate().compareTo(to) < 0) {
                    if (arguments.length >= 3) {
                        for (int i = 2; i < arguments.length - 1; i++) {
                            if (note.getNoteName().contains(arguments[i])) {
                                System.out.println(note);
                                break;
                            }
                        }
                    } else {
                        System.out.println(note);
                    }

                }
            }
        }

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
        return count;
    }
}
