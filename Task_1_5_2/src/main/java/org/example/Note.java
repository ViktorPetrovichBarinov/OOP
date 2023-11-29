package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Date;


public class Note {

    @JsonSerialize
    private String note;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public Note(String note, String content, Date createTime) {
        this.note = note;
        this.content = content;
        this.createDate = createTime;
    }

    public Note() {

    }

    public String getNote() {
        return note;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
