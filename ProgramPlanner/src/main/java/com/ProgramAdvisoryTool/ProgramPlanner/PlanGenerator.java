package com.ProgramAdvisoryTool.ProgramPlanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanGenerator {
    String[] StudentPlan = new String[24];

    public void CreatePlan(String Degree, String Major, String[] CompletedCourses, String DegreeLength) {


        // variables
        int array1Index = 0;
        int array2Index = 0;


        //Getting the completed courses
        String StudentCourses = "";

        // these arrays represent the courses split into semesters
        List<String> S1 = new ArrayList<>();
        List<String> S2 = new ArrayList<>();




        int index = 0;




        String[] completedCourses = CompletedCourses;

        String[][] courses = {{"COMP1010", "Computing Fundamentals", "1000", "10", "Semester 1","If you have successfully completed INFT1001 or INFT1150, you cannot enrol in this course",null, "Core"},
                {"COMP1140", "Database and Information Management", "1000", "10", "Semester 2","If you have successfully completed INFT2040, you cannot enrol in this course","Knowledge of and experience in programming", "Core"},
                {"INFT1060", "Cybersecurity Fundamentals", "1000", "10", "Semester 1",null, null, "Core"},
                {"SENG1050", "Web Technologies", "1000", "10", "Semester 2",null, null, "Core"},
                {"SENG1110", "Object Oriented Programming", "1000", "10", "Semester 1 & 2",null, null, "Core"},
                {"INFT2031", "Systems and Network Administration", "2000", "10", "Semester 1 & 2",null, "SENG1050 Or INFT1004", "Core"},
                {"INFT2060", "Applied Artificial Intelligence", "2000", "10", "Semester 2",null, "INFT1004 OR SENG1110", "Core"},
                {"INFT2150", "Business Analysis", "2000", "10", "Semester 2","similar to INFT3150", "COMP1010 or COMP1140", "Core"},
                {"SENG2130", "Systems Analysis and Design", "2000", "10", "Semester 1",null, null, "Core"},
                {"SENG2260", "Human-Computer Interaction", "2000", "10", "Semester 2","similar to INFT2009", "INFT1004 OR SENG1110", "Core"},
                {"COMP3851A", "Computing and Information Sciences Work Integrated Learning Part A","3000", "10", "Semester 1 & 2", "similar to COMP3850", "at least 140 units", "Core"},
                {"COMP3851B", "Computing and Information Sciences Work Integrated Learning Part B","3000", "10", "Semester 1 & 2", null, "COMP3851A", "Core"},
                {"INFT3100", "Project Management", "3000", "20", "Semester 1",null, "Basic compentency in MS Office packages including Excel", "Core"},
                {"INFT3800", "Professional Practice in IT", "3000", "10", "Semester 1",null, "Successful completion of at least 100 units of study, SENG2130 or INFT2150", "Core"},
                {"MATH1510", "Discrete Mathematics", "1000", "10", "Semester 2",null, "HSC Advanced Mathematics (Bands 5 or 6) or equivalent", "ICT Developer"},
                {"SENG1120", "Data Structures", "1000", "10", "Semester 1 & 2",null, "SENG1110 or INFT2012", "ICT Developer"},
                {"INFT2051", "Mobile Application Programming", "2000", "10", "Semester 2",null, "INFT2012 OR SENG1110", "ICT Developer"},
                {"EBUS3050", "The Digital Economy", "3000", "10", "Semester 1","similar to EBUS3010", null, "ICT Developer"},
                {"INFT3050", "Web Programming", "3000", "10", "Semester 2","replaces INFT2008", "COMP1140 and INFT2012 or SENG1110", "ICT Developer"},
                {"INFT3950", "Games Design", "3000", "20", "Semester 1",null, null, "ICT Developer"},
                {"INFT1004", "Introduction to Programming", "1000", "10", "Semester 2",null, null, "ICT Professional"},
                {"STAT1060", "Business Decision Making", "1000", "10", "Semester 1 & 2",null, "Basic numeracy and literacy", "ICT Professional"},
                {"SENG2250", "System and Network Security", "2000", "10", "Semester 2",null, null, "ICT Professional"},
                {"COMP3350", "Advanced Database", "3000", "10", "Semester 1","similar to INFT3007", "COMP1140 and INFT1004 or SENG1110", "ICT Professional"}
        };


        for (int A = 0; A < courses.length; A++) {
            System.out.println(courses[A][0] + " " + courses[A][4]);
        }

        for (int i = 0; i < courses.length; i++) {
            boolean isCompleted = false;

            //loop to check the completed courses
            for (int x = 0; x < CompletedCourses.length; x++) {
                if (courses[i][0].equals(CompletedCourses[x])) {
                    ArrayList<String[]> coursesList = new ArrayList<>(Arrays.asList(courses));
                    coursesList.remove(i);
                    courses = coursesList.toArray(new String[coursesList.size()][]);
                    isCompleted = true;
                }
            }
            if (!isCompleted) {

            }


        }




        // Sort courses based on semester availability
        for (int x = 0; x < courses.length; x++) {

            if (courses[x][4].contains("Semester 1") || courses[x][4].contains("Semester 1 & 2")) {
                S1.add(courses[x][0]);
            } else if (courses[x][4].contains("Semester 2") || courses[x][4].contains("Semester 1 & 2")) {
                S2.add(courses[x][0]);
            }
        }
        int totalLength = S1.size() + S2.size();




        // Fill the StudentPlan array with the distributed courses
        while (index < totalLength) {
            for (int i = 0; i < 4 && array1Index < S1.size(); i++) {
                StudentPlan[index++] = S1.get(array1Index++);
            }

            for (int i = 0; i < 4 && array2Index < S2.size(); i++) {
                StudentPlan[index++]= S2.get(array2Index++);
            }
        }

        for(int A = 0 ; A < StudentPlan.length; A++){
            System.out.println((StudentPlan[A]));


        }


    }

    public String[] GetThePlan()
    {


        return StudentPlan;

    }


}

