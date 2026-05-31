package com.example.studentapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "school_subjects")
public class SchoolSubject implements SchoolDataController.Identified {
    @Id
    @Column(length = 36)
    private String id;
    private String name;
    private String code;
    private String teacher;
    private String classes;
    private String type;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }
    public String getClasses() { return classes; }
    public void setClasses(String classes) { this.classes = classes; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
