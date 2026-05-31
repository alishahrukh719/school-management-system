package com.example.studentapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam implements SchoolDataController.Identified {
    @Id
    @Column(length = 36)
    private String id;
    private String title;
    private String subject;
    private String className;
    private String date;
    private Integer maxMarks;
    private Integer passMarks;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public Integer getMaxMarks() { return maxMarks; }
    public void setMaxMarks(Integer maxMarks) { this.maxMarks = maxMarks; }
    public Integer getPassMarks() { return passMarks; }
    public void setPassMarks(Integer passMarks) { this.passMarks = passMarks; }
}
