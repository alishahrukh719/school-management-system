package com.example.studentapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher implements SchoolDataController.Identified {
    @Id
    @Column(length = 36)
    private String id;
    private String name;
    private String eid;
    private String subject;
    private String classes;
    private String exp;
    private String status;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEid() { return eid; }
    public void setEid(String eid) { this.eid = eid; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getClasses() { return classes; }
    public void setClasses(String classes) { this.classes = classes; }
    public String getExp() { return exp; }
    public void setExp(String exp) { this.exp = exp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
