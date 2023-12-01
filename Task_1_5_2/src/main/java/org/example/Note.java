package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private String noteName;
    private String noteBody;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date createDate;

    //Этот конструктор для Jackson нужен
    public Note() {

    }

    public Note(String noteName, String noteBody, Date createDate) {
        this.noteName = noteName;
        this.noteBody = noteBody;
        this.createDate = createDate;
    }


    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder outputString = new StringBuilder();
        outputString.append("Note name: ").append(noteName).append("\n");
        outputString.append("Note content: ").append(noteBody).append("\n");
        outputString.append("CreateDate: ").append(dateFormat.format(createDate)).append("\n");
        return outputString.toString();
    }
}
