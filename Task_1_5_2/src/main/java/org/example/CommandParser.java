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
                System.out.print(
                        "Error: The application does not support "
                                + "processing multiple commands at once.Enter only one command\n");
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
                System.err.print("Enter: notebook -FAQ "
                        + "for information about the application commands;\n");
                //parser.printUsage(System.err);
            }
        } catch (Exception e) {
            //parser.printUsage(System.err);
        }
    }


    private void addCommandHandle() {
        if (arguments == null || arguments.length > 2 || arguments.length == 0) {
            System.err.print("-add have 1 or 2 arguments\n");
            System.err.print("1 arg: -add \"Note name\"\n");
            System.err.print("2 arg: -add \"Note name\" \"Note body\"\n");
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
            System.err.print("-rn have 1 argument\n");
            System.err.print("1 arg: -rm \"Note name\"\n");
            return;
        }
        String name = arguments[0];
        List<Note> notes = readObjectsFromJason(filePath);
        if (notes == null) {
            System.err.print("A note with that name was not found\n");
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
            System.err.print("A note with that name was not found\n");
            return;
        }
        writeObjectsToJson(notes, filePath);
    }

    private void showCommandHandle() {
        List<Note> notes = readObjectsFromJason(filePath);
        if (notes == null) {
            System.out.print("You have no notes\n");
            return;
        }
        if (arguments == null || arguments.length == 0) {
            for (Note note : notes) {
                System.out.println(note);
            }
            return;
        }
        if (arguments.length == 1) {
            System.err.print("-show have 0 or 2 and more argument\n");
            System.err.print("2 and more arg: -show \"from time\""
                    + " \"to time\" \"keyword1\" \"keyword2\"...\n");
            return;
        }


        if (arguments.length >= 2) {
            Date from = fromStringToDate(arguments[0]);
            Date to = fromStringToDate(arguments[1]);
            if (from == null || to == null) {
                System.err.print("Date format: dd.MM.yyyy (H)H:mm\n");
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
                            System.out.print(note + "\n");
                        }
                    } else {
                        System.out.print(note + "\n");
                    }

                }
            }
        }

    }

    private void faqCommandHandle() {
        System.out.print("\"-add\" - add note to note list\n");
        System.out.print("\"-rm\" - remove note from note list\n");
        System.out.print("\"-show\"- show all notes from note list\n");
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
