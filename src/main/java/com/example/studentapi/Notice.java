package com.example.studentapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "notices")
public class Notice implements SchoolDataController.Identified {
    @Id
    @Column(length = 36)
    private String id;
    private String title;
    @Lob
    private String body;
    private String category;
    private String date;
    private String target;
    private Boolean urgent;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }
    public Boolean getUrgent() { return urgent; }
    public void setUrgent(Boolean urgent) { this.urgent = urgent; }
}
