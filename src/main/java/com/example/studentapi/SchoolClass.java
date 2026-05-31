package com.example.studentapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "school_classes")
public class SchoolClass implements SchoolDataController.Identified {
    @Id
    @Column(length = 36)
    private String id;
    private String name;
    private String teacher;
    private Integer students;
    private String room;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }
    public Integer getStudents() { return students; }
    public void setStudents(Integer students) { this.students = students; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
}
