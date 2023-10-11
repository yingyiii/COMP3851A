public class Main
{
    public static String[][][] courses = {
            {{"INFT1001", "Computing Fundamentals", "1000", "10", "Semester 1",
                    "If you have successfully completed INFT1001 or INFT1150, you cannot enrol in this course",
                    null, "Core"},
                    {"COMP1010", "Computing Fundamentals", "1000", "10", "Semester 1",
                            "If you have successfully completed INFT1001 or INFT1150, you cannot enrol in this course",
                            null, "Core"},
                    {"COMP1140", "Database and Information Management", "1000", "10", "Semester 2",
                            "If you have successfully completed INFT2040, you cannot enrol in this course",
                            "Knowledge of and experience in programming", "Core"},
                    {"INFT1060", "Cybersecurity Fundamentals", "1000", "10", "Semester 1",
                            null, null, "Core"}},
            {{"SENG2260", "Web Technologies", "1000", "10", "Semester 2",
                    null, null, "Core"},
                    {"SENG1110", "Object Oriented Programming", "1000", "10", "Semester 1 & 2",
                            null, null, "Core"},
                    {"INFT2031", "Systems and Network Administration", "2000", "10", "Semester 1 & 2",
                            null, "SENG1050 Or INFT1004", "Core"},
                    {"INFT2060", "Applied Artificial Intelligence", "2000", "10", "Semester 2",
                            null, "INFT1004 OR SENG1110", "Core"}},
            {{"INFT2150", "Business Analysis", "2000", "10", "Semester 2",
                    "similar to INFT3150", "COMP1010 or COMP1140", "Core"},
                    {"SENG2130", "Systems Analysis and Design", "2000", "10", "Semester 1",
                            null, null, "Core"},
                    {"SENG2260", "Human-Computer Interaction", "2000", "10", "Semester 2",
                            "similar to INFT2009", "INFT1004 OR SENG1110", "Core"},
                    {"COMP3851A", "Computing and Information Sciences Work Integrated Learning Part A",
                            "3000", "10", "Semester 1 & 2", "similar to COMP3850", "at least 140 units", "Core"}},
            {{"COMP3851B", "Computing and Information Sciences Work Integrated Learning Part B",
                    "3000", "10", "Semester 1 & 2", null, "COMP3851A", "Core"},
                    {"INFT3100", "Project Management", "3000", "20", "Semester 1",
                            null, "Basic compentency in MS Office packages including Excel", "Core"},
                    {"INFT3800", "Professional Practice in IT", "3000", "10", "Semester 1",
                            null, "Successful completion of at least 100 units of study, SENG2130 or INFT2150", "Core"},
                    {"MATH1510", "Discrete Mathematics", "1000", "10", "Semester 2",
                            null, "HSC Advanced Mathematics (Bands 5 or 6) or equivalent", "ICT Developer"}},
            {{"SENG1120", "Data Structures", "1000", "10", "Semester 1 & 2",
                    null, "SENG1110 or INFT2012", "ICT Developer"},
                    {"INFT2051", "Mobile Application Programming", "2000", "10", "Semester 2",
                            null, "INFT2012 OR SENG1110", "ICT Developer"},
                    {"EBUS3050", "The Digital Economy", "3000", "10", "Semester 1",
                            "similar to EBUS3010", null, "ICT Developer"},
                    {"INFT3050", "Web Programming", "3000", "10", "Semester 2",
                            "replaces INFT2008", "COMP1140 and INFT2012 or SENG1110", "ICT Developer"}},
            {{"INFT3950", "Games Design", "3000", "20", "Semester 1",
                    null, null, "ICT Developer"},
                    {"INFT1004", "Introduction to Programming", "1000", "10", "Semester 2",
                            null, null, "ICT Professional"},
                    {"STAT1060", "Business Decision Making", "1000", "10", "Semester 1 & 2",
                            null, "Basic numeracy and literacy", "ICT Professional"},
                    {"SENG2250", "System and Network Security", "2000", "10", "Semester 2",
                            null, null, "ICT Professional"},
                    {"COMP3350", "Advanced Database", "3000", "10", "Semester 1",
                            "similar to INFT3007",/*COMP1140 -->*/ "cOMP1140 and iNFT1004 or sENG1110", "ICT Professional"}}

    };
    public static String[] completedCourses = {"COMP1010","SENG1110"};
    public static void main(String[] args)
    {
        prerequisite(courses,"COMP1140");
        //assumedKnowledge(courses,"COMP3350","COMP1140");
    }
    public static void assumedKnowledge(String[][][] course, String course2, String[] course3)
    {
        int Checker = 0, Checker2 =0, courseCount = 0 , aLength = 0, bLength = 0;
        String Error = "Nothing";

        for(int a=0;a < course3.length; a++ ) {
            courseCount++;
            if (course3[a] == null)
            {
                continue;
            }
            else if (course3[a].contains(course2))
            {
                for (int d = 0; d <= a; d++) {
                    for (int e = 0; e < 4; e++) {
                        if ((course[d][e][6]).contains(course3[a]))
                        {
                            Checker = 1;
                            //System.out.println("Error! Need to complete " + course[a][b][0]);
                        }
                        else
                        {
                            Error = course[d][e][6];
                        }
                    }
                }
            } else if (course3[a].contains("COMP3851A")) {
                if (courseCount < 14) {
                    System.out.println("Error! Need to complete " + (14 - courseCount) + " more courses to do this course");
                }
            } else if (course3[a].contains("COMP3851B")) {
                for (int g = 0; g < a; g++) {
                    for (int f = 0; f < 4; f++) {
                        if (course[g][f][0].contains("COMP3851A")) {
                            Checker2 = 1;
                        }
                    }
                }

            }
        }
        if(Checker == 0 )
        {
            System.out.println("Error! Need to complete " + Error);
        }
        if(Checker2 == 0)
        {
            System.out.println("Error! Need to complete COMP3851A before COMP3851B");
        }
    }
    private static void assumedKnowledge(String[][][] course, String course2, String course3 )
    {
        int Checker = 0, Checker2 =0, courseCount = 0 , aLength = 0, bLength = 0;
        String Error = "Nothing";

        for(int a=0;a < course.length; a++ )
        {
            for(int b = 0; b < course[a].length; b++)
            {
                courseCount++;
                if (course[a][b][6] == null)
                {
                    continue;
                }
                else if(course[a][b][0].contains(course2))
                {
                    for(int d = 0; d <= a ; d++ )
                    {
                        for(int e = 0; e < 4 ; e++ )
                        {
                            if((course[a][b][6]).contains(course[d][e][0]))
                            {
                                Checker = 1;
                                //System.out.println("Error! Need to complete " + course[a][b][0]);
                            }
                            else
                            {
                                Error = course[a][b][6];
                            }
                        }
                    }
                }
                else if(course[a][b][0].contains("COMP3851A"))
                {
                    if(courseCount < 14)
                    {
                        System.out.println("Error! Need to complete " + (14 - courseCount) + " more courses to do this course" );
                    }
                }
                else if(course[a][b][0].contains("COMP3851B"))
                {
                    for(int g=0; g< a; g++)
                    {
                        for(int f=0; f < 4; f++)
                        {
                            if(course[g][f][0].contains("COMP3851A"))
                            {
                                Checker2 = 1;
                            }
                        }
                    }
                    
                }
            }
            if(course[a] != null)
            {
                aLength++;
            }
        }
        if(Checker == 0 )
        {
            System.out.println("Error! Need to complete " + Error);
        }
        if(Checker2 == 0)
        {
            System.out.println("Error! Need to complete COMP3851A before COMP3851B");
        }
    }
    private static void prerequisite(String[][][] course, String course2 )
    {
        for(int a=0; a<course.length; a++)
        {
            for(int b=0; b<course[a].length; b++)
            {
                if (course[a][b][5] == null)
                {
                    continue;
                }
                else if(course[a][b][0].contains(course2))
                {
                    for(int d=0; d<=a; d++)
                    {
                        for(int e=0; e<4; e ++)
                        {
                            if(course[a][b][5].contains(course[d][e][0]))
                            {
                                System.out.print("Cannot enrol in " + course2 + " because " + course[d][e][0] + " has been completed");
                            }
                        }
                    }
                }
            }
        }
    }
}