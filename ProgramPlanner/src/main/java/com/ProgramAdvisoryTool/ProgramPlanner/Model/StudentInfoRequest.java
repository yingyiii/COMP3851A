package com.ProgramAdvisoryTool.ProgramPlanner.Model;
public class StudentInfoRequest {
    private String Degree;
    private String Major;
    private String CompletedCourses;
    private Integer Units;
    private Integer Startingsemester;

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

    public Integer getUnits() {
        return Units;
    }

    public void setUnits(Integer units) {
        Units = units;
    }

    public Integer getStartingsemester() {
        return Startingsemester;
    }

    public void setStartingsemester(Integer startingsemester) {
        Startingsemester = startingsemester;
    }





}
