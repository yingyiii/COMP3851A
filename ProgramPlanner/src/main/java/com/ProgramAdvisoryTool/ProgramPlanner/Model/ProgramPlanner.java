package com.ProgramAdvisoryTool.ProgramPlanner.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgramPlanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Degree;
    private String Major;
    private String CompletedCourses;
    private String Semester;
    private Integer Years;
    private String Campus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getCompletedCourses() {
        return CompletedCourses;
    }

    public void setCompletedCourses(String completedCourses) {
        CompletedCourses = completedCourses;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public Integer getYears() {
        return Years;
    }

    public void setYears(Integer years) {
        Years = years;
    }

    public String getCampus() {
        return Campus;
    }

    public void setCampus(String campus) {
        Campus = campus;
    }





}
