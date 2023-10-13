package com.ProgramAdvisoryTool.ProgramPlanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlanGenerator {
    private static int semester1Count = 0; //Semester 1 Counter
    private static int semester2Count = 0; //Semester 2 Counter
    private static int semester1_2Count = 0; //Semester 1 and 2 Counter
    private static int WIl_Counter = 0; //Work integrated Learning Courses Counter
    private static final int Degree_Units = 240; // IT And CS
    /////////////////////////////////////////////////////////////////////////////////////////
    private static int Units_Needed; // calculate the Units needed each semester
    /////////////////////////////////////////////////////////////////////////////////////////
    private static String[][][] FinalPlan;
    private static int index = 0;
    private static int Completion_Years;
    private static int NotNullCounter = 0;
    private static String[] CompletedCourses;
    //private static final String[][][] FinalPlan = new String[Completion_Years * 2][Units_Needed][8]; // the final array will be the array that will have the plan (Completion_Years * 2 = semesters, Units_Needed = courses, 8 = the course information)


    public void CreatePlan(String Degree, String Major, String completedCourses, int UnitLoad,int startingSemester) {

        System.out.println("Selected Degree : "+Degree);
        System.out.println("Selected Major : "+Major);
        System.out.println("The Completed Courses : "+completedCourses);
        System.out.println("Selected Unit load Per Semester : "+UnitLoad);
        System.out.println("Started Semester : Semester "+startingSemester);


        int starting_Semester = startingSemester;
        Units_Needed = UnitLoad;
        Completion_Years = (Degree_Units / Units_Needed) / 20;  // the length that the student wants to finish their Degree

        FinalPlan = new String[Completion_Years * 2][4][8]; // the final array will be the array that will have the plan (Completion_Years * 2 = semesters, 4 COURSES PER SEMESTER (FIXED), 8 = the course information)


        if (Degree.contains("Computer Science"))
        {
            Degree = "cs_courses";
        } else if (Degree.contains("Information Technology")) {
            Degree = "it_courses";
        }


        String[][] Semester1 = new String[Degree_Units / 20][8]; //an array to store all the courses from Semester 1
        String[][] Semester2 = new String[Degree_Units / 20][8]; //an array to store all the courses from Semester 2
        String[][] Semester1_2 = new String[Degree_Units / 10][8]; //an array to store the courses from Semester 2
        String[][] WIL_Courses = new String[5][8]; //Extracting the Project Courses







        //String StudentCourses = completedCourses;
        CompletedCourses = completedCourses.split("\\s+");


        String[][] courses = new String[0][];
        String[][] directed_course = new String[0][];
        Connection connection = null;
        ResultSet resultSet12 = null;
        ResultSet resultSet22 = null;

        try {

            //create connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uondb", "root", "0000");

            //create statement
            String s1 = "SELECT * FROM "+Degree+" WHERE Course_Major = 'core' OR Course_Major = 'WIL' OR Course_Major LIKE '%"+Major+"%' AND Course_Major NOT LIKE '%Directed "+Major+"%';";
            PreparedStatement preparedStatement1 = connection.prepareStatement(s1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //preparedStatement1.setString(1, "Core");
            resultSet12 = preparedStatement1.executeQuery();

            String s2 = "SELECT * FROM "+Degree+" WHERE Course_Major like '%Directed "+Major+"%';";
            PreparedStatement preparedStatement2 = connection.prepareStatement(s2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //preparedStatement2.setString(1, "Directed Software Development");
            resultSet22 = preparedStatement2.executeQuery();




            //COPY FROM HERE
            int CoreCoursesUnits = 0;
            int CompulsoryCoursesUnits = 0;
            int DirectedCoursesUnits = 0;
            int ElectivesUnits = 0;

            String s3 = "SELECT CoreCoursesUnits, CompulsoryCoursesUnits, DirectedCoursesUnits, ElectivesUnits FROM majors WHERE MajorName = ?";
            PreparedStatement preparedStatement3 = connection.prepareStatement(s3);
            preparedStatement3.setString(1, Major); // Set the Major parameter
            ResultSet resultSet3 = preparedStatement3.executeQuery();


            while(resultSet3.next()){

                CoreCoursesUnits = resultSet3.getInt("CoreCoursesUnits");
                CompulsoryCoursesUnits = resultSet3.getInt("CompulsoryCoursesUnits");
                DirectedCoursesUnits = resultSet3.getInt("DirectedCoursesUnits");
                ElectivesUnits = resultSet3.getInt("ElectivesUnits");

                System.out.println("The divided units for the selected major are: " + "Course Courses = "+ CoreCoursesUnits + ", Compulsory Courses = " + CompulsoryCoursesUnits + ", Directed Courses = " + DirectedCoursesUnits + ", Elective Courses = " + ElectivesUnits);

            }
            //TO HERE








            resultSet12.last();
            //int rowCount1 = resultSet1.getRow();
            //int columnCount1 = resultSet1.getMetaData().getColumnCount();
            int rowCount1 = 24;
            int columnCount1 = 8;
            resultSet12.beforeFirst();
            courses = new String[rowCount1][columnCount1];

            resultSet22.last();
            int rowCount2 = resultSet22.getRow();
            int columnCount2 = resultSet22.getMetaData().getColumnCount();
            resultSet22.beforeFirst();
            directed_course = new String[rowCount2][columnCount2];

            int row = 0;

            while (resultSet12.next()) {

                for (int i = 0; i < columnCount1; i++) {
                    courses[row][i] = resultSet12.getString(i + 1);
                    if(courses[row][0] == null)
                    {break;}
                }
                row++;
            }
            int row2 = 0;

            while (resultSet22.next()) {
                for (int a = 0; a < columnCount2; a++) {
                    directed_course[row2][a] = resultSet22.getString(a + 1);
                }
                row2++;
            }
            int Elective = 1;
            ///////////////////////////////////////////////
            for (int i = row; i < row + (ElectivesUnits/10); i++) { // the 3 will be changes with about of Electives
                courses[i] = new String[]{"ELECTIVE "+ Elective, "ELECTIVE", "1000", "10", "Semester 1 & 2", null, null, "ELECTIVE"};
                Elective++;
            }
            int Directed = 1;
            for (int i = row + (ElectivesUnits/10); i < (row + (ElectivesUnits/10)) + (DirectedCoursesUnits/10); i++) { // the 3 will be changes with about of Electives
                courses[i] = new String[]{"Directed Major " +Directed, "Directed Major", "2000", "10", "Semester 1 & 2", null, null, "Directed Major"};
                Directed++;
            }
            ////////////////////////////////////////////////

            /*for (int j = 0; j < rowCount1; j++) {
                for (int k = 0; k < columnCount1; k++) {
                    //System.out.println(course.length);

                    //System.out.print(courses[j][k] + "\t");
                }
                System.out.println();
            }

            for (int b = 0; b < rowCount2; b++) {
                for (int c = 0; c < columnCount2; c++) {
                }

            }*/

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (resultSet12 != null) {
                    resultSet12.close();
                }
                if (resultSet22 != null) {
                    resultSet22.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }














        // Split the courses based on the Semester (will be merged later)
        for (String[] cours : courses) {
            String availability = cours[4];
            if (cours[7].contains("WIL")) {
                System.arraycopy(cours, 0, WIL_Courses[WIl_Counter], 0, 8);
                WIl_Counter++;
            } else if (availability.equals("Semester 1")) {
                System.arraycopy(cours, 0, Semester1[semester1Count], 0, 8);
                semester1Count++;
            } else if (availability.equals("Semester 2")) {
                System.arraycopy(cours, 0, Semester2[semester2Count], 0, 8);
                semester2Count++;
            } else {
                System.arraycopy(cours, 0, Semester1_2[semester1_2Count], 0, 8);
                semester1_2Count++;
            }
        }




        // Calculate the WIL Courses
        for (String[] wilCours : WIL_Courses) {
            if (wilCours[0] != null) {
                NotNullCounter++;
            }
        }





        WIl_Counter = 0; // reset the WIL Courses To 0
        semester1_2Count = 0;// reset the Semester 1 and 2 Courses To 0
        ////////////////////////////////////////////////////////////////////////////////////
        // Add the Semester 1 and 2 Courses to Semester 1 Array.
/*        while (semester1Count < Semester1.length -(NotNullCounter/2) && Semester1[semester1Count] != null) {
            System.arraycopy(Semester1_2[semester1_2Count], 0, Semester1[semester1Count], 0, 8);
            semester1Count++;
            semester1_2Count++;
        }


        // Add the Semester 1 and 2 Courses to Semester 2 Array.
        while (semester2Count < Semester2.length - (NotNullCounter/2) && Semester2[semester2Count] != null) {
            System.arraycopy(Semester1_2[semester1_2Count], 0, Semester2[semester2Count], 0, 8);
            semester2Count++;
            semester1_2Count++;
        }*/

        String courseCode; // Get the course code from Semester1_2

        // Check which array has fewer courses at the same level
        int levelCount1;
        int levelCount2;


        while (semester1Count < Semester1.length - (NotNullCounter / 2) && Semester1[semester1Count] != null &&
                semester2Count < Semester2.length - (NotNullCounter / 2) && Semester2[semester2Count] != null) {
            courseCode = Semester1_2[semester1_2Count][2];
            levelCount1 = countCoursesWithLevel(Semester1, courseCode);
            levelCount2 = countCoursesWithLevel(Semester2, courseCode);
            if (levelCount1 >= levelCount2) {
                System.arraycopy(Semester1_2[semester1_2Count], 0, Semester2[semester2Count], 0, 8);
                semester2Count++;
            } else {
                // Odd count, add to Semester 1
                System.arraycopy(Semester1_2[semester1_2Count], 0, Semester1[semester1Count], 0, 8);
                semester1Count++;
            }
            semester1_2Count++;
        }

// If Semester 1 is full, add remaining elements to Semester 2
        while (semester2Count < Semester2.length - (NotNullCounter / 2) && semester1_2Count < Semester1_2.length) {
            System.arraycopy(Semester1_2[semester1_2Count], 0, Semester2[semester2Count], 0, 8);
            semester2Count++;
            semester1_2Count++;
        }

// If Semester 2 is full, add remaining elements to Semester 1
        while (semester1Count < Semester1.length - (NotNullCounter / 2) && semester1_2Count < Semester1_2.length) {
            System.arraycopy(Semester1_2[semester1_2Count], 0, Semester1[semester1Count], 0, 8);
            semester1Count++;
            semester1_2Count++;
        }



        ////////////////////////////////////////////////////////////////////////////////////



        if (starting_Semester == 1){StartingSemester1(Semester1,Semester2,WIL_Courses);}
        else if (starting_Semester == 2) {StartingSemester2(Semester1,Semester2,WIL_Courses);

        }




        System.out.println("\n===========================================================");
        int gx = 0;
        for (String[][] strings : FinalPlan) {
            for (String[] string : strings) {
                gx++;
                System.out.println(gx +" :" + string[0] + ": " + string[1] + ": " + string[2] + ": " + string[3] + ": " + string[4] + ": " + string[5] + ": " + string[6] + ": " + string[7] + "\n");
            }
            System.out.println("===========================================================");
        }

        /*System.out.print("/////////////////////////////////////////////////////////////////\n");
        System.out.print("/////////////////////////////////////////////////////////////////\n");
        System.out.print("/////////////////////////////////////////////////////////////////\n");
        System.out.print("/////////////////////////////////////////////////////////////////\n");

        for (int DC = 0; DC < directed_course.length; DC++) {
            for (int j = 0; j < directed_course[DC].length; j++) {
                System.out.print(directed_course[DC][j] + " ,");
            }
            System.out.println(); // Move to the next line after each row
        }



        System.out.println("Semester 1 :\n");
        for (int x = 0; x < Semester1.length; x++) {
            System.out.println(Semester1[x][0] + ", " + Semester1[x][1] + ", " + Semester1[x][1] + ", " + Semester1[x][2] + ", " + Semester1[x][3] + ", " + Semester1[x][4] + ", " + Semester1[x][5] + ", " + Semester1[x][6] + ", " + Semester1[x][7]);

            System.out.println("Next Course :\n");

        }
        System.out.println("Semester 2 :\n");
        for (int x = 0; x < Semester1.length; x++) {
            System.out.println(Semester2[x][0] + ", " + Semester2[x][1] + ", " + Semester2[x][1] + ", " + Semester2[x][2] + ", " + Semester2[x][3] + ", " + Semester2[x][4] + ", " + Semester2[x][5] + ", " + Semester2[x][6] + ", " + Semester2[x][7]);

            System.out.println("Next Course :\n");

        }*/






    }



    private static void StartingSemester1(String[][] SS1, String[][] SS2, String[][] WIL)
    {
        WIl_Counter = 0; // reset the WIL Courses To 0


        // Add the Work integrated Learning to Semester 1 Courses
        while (semester1Count < SS1.length && SS1[semester1Count] != null) {
            System.arraycopy(WIL[WIl_Counter], 0, SS1[semester1Count], 0, 8);
            semester1Count++;
            WIl_Counter++;
        }

        // Add the Work integrated Learning to Semester 2 Courses
        while (semester2Count < SS2.length && SS2[semester2Count] != null) {
            System.arraycopy(WIL[WIl_Counter], 0, SS2[semester2Count], 0, 8);
            semester2Count++;
            WIl_Counter++;
        }
        ////////////////////////////////////////////////////////////////////////////////////
        //Call the Sorting Method
        SortCourses(SS1,SS2);
        ////////////////////////////////////////////////////////////////////////////////////
        semester1Count = 0; //Semester 1 Counter
        semester2Count = 0; //Semester 2 Counter

        //adding the courses to the final plan//
        while (index < Completion_Years * 2) {
            if (index % 2 == 0) {
                for (int i = 0; i < Units_Needed; i++) {
                    System.arraycopy(SS1[semester1Count], 0, FinalPlan[index][i], 0, 8);
                    semester1Count++;
                }
            } else {
                for (int Y = 0; Y < Units_Needed; Y++) {
                    System.arraycopy(SS2[semester2Count], 0, FinalPlan[index][Y], 0, 8);
                    semester2Count++;
                }
            }
            index++;
        }
        CheckCompletedCourses();
        FillTheNulls();
        CheckforNullSemesters();
        //swapValue("SENG2130", "INFT3800");
        System.out.println("Plan Has Been Generated Successfully");
        //prerequisite("COMP3851A");



    }
    private static void StartingSemester2(String[][] SS1, String[][] SS2, String[][] WIL)
    {
        WIl_Counter = 0; // reset the WIL Courses To 0


        // Add the Work integrated Learning to Semester 2 Courses
        while (semester2Count < SS2.length && SS2[semester2Count] != null) {
            System.arraycopy(WIL[WIl_Counter], 0, SS2[semester2Count], 0, 8);
            semester2Count++;
            WIl_Counter++;
        }
        // Add the Work integrated Learning to Semester 1 Courses
        while (semester1Count < SS1.length && SS1[semester1Count] != null) {
            System.arraycopy(WIL[WIl_Counter], 0, SS1[semester1Count], 0, 8);
            semester1Count++;
            WIl_Counter++;
        }

        ////////////////////////////////////////////////////////////////////////////////////
        //Call the Sorting Method
        SortCourses(SS1,SS2);
        ////////////////////////////////////////////////////////////////////////////////////
        semester1Count = 0; //Semester 1 Counter
        semester2Count = 0; //Semester 2 Counter

        //adding the courses to the final plan//
        while (index < Completion_Years * 2) {
            if (index % 2 != 0) {
                for (int i = 0; i < Units_Needed; i++) {
                    System.arraycopy(SS1[semester1Count], 0, FinalPlan[index][i], 0, 8);
                    semester1Count++;
                }
            } else {
                for (int Y = 0; Y < Units_Needed; Y++) {
                    System.arraycopy(SS2[semester2Count], 0, FinalPlan[index][Y], 0, 8);
                    semester2Count++;
                }
            }
            index++;
        }
        CheckCompletedCourses();
        FillTheNulls();
        CheckforNullSemesters();
        //swapValue("SENG2130", "INFT3800");
        System.out.println("Plan Has Been Generated Successfully");
        //prerequisite("COMP3851A");

    }
    private static void SortCourses(String[][] SS1, String[][] SS2)
    {
        //sorting the arrays and making the nulls in the end//
        Comparator<String[]> customComparator = (array1, array2) -> {
            String value1 = array1[2];
            String value2 = array2[2];

            if (value1 == null && value2 == null) {
                return 0; // Both have null [2], they are equal
            } else if (value1 == null) {
                return 1; // Null [0] should be placed at the end
            } else if (value2 == null) {
                return -1; // Null [0] should be placed at the end
            } else {
                return value1.compareTo(value2); // Compare [2] values
            }
        };

        // Sorting Semester1 based on [2] with null [0] values at the end
        Arrays.sort(SS1, customComparator);

        // Sorting Semester2 based on [2] with null [0] values at the end
        Arrays.sort(SS2, customComparator);

    }
    public String[][][] GetThePlan()
    {

        System.out.println("Plan Has Been ");

        return FinalPlan;

    }

    public static void FillTheNulls()
    {
        int empityCount = 1;
        for (int i = 0; i < FinalPlan.length; i++) {
            for (int j = 0; j < FinalPlan[i].length; j++) {
                if (FinalPlan[i][j][0] == null) {
                    FinalPlan[i][j][0] = "EMPITY " + empityCount;
                    FinalPlan[i][j][3] = "0";
                    empityCount++;
                }
            }
        }
        System.out.println("Nulls Filled Successfully");



    }

    private static int countCoursesWithLevel(String[][] array, String courseCode) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][2] != null && array[i][2].equals(courseCode)) {
                count++;
            }
        }
        return count;
    }


    private static void CheckCompletedCourses() {
        for (int i = 0; i < FinalPlan.length; i++) {
            for (int j = 0; j < FinalPlan[i].length; j++) {
                if (FinalPlan[i][j][0] != null) {
                    for (String completedCourse : CompletedCourses) {
                        if (FinalPlan[i][j][0] != null && FinalPlan[i][j][0].equals(completedCourse)) {
                            // If the course is completed, replace the course information with nulls
                            for (int k = 0; k < FinalPlan[i][j].length; k++) {
                                FinalPlan[i][j][k] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void CheckforNullSemesters()
    {
        for (int i = 0; i < FinalPlan.length; i++) {
            boolean allNull = true;

            for (int j = 0; j < FinalPlan[i].length; j++) {
                if (FinalPlan[i][j][1] != null) {
                    allNull = false;
                    break; // If any element in [][this][1] is not null, no need to check further.
                }
            }

            if (allNull) {
                // Remove the index by shifting elements
                for (int k = i; k < FinalPlan.length - 1; k++) {
                    FinalPlan[k] = FinalPlan[k + 1];
                }
                // Decrement the length of the array
                FinalPlan = Arrays.copyOf(FinalPlan, FinalPlan.length - 1);
                System.out.println("semester "+ i +" Has Been Removed From The Plan");
                i--; // Recheck the current index since we shifted elements
            }
        }



    }


























    public static boolean swapValue (String courseCode1, String courseCode2){

        for (int i = 0; i < FinalPlan.length; i++) {

            for (int j = 0; j < FinalPlan[i].length; j++){

                if (FinalPlan[i][j][0].equals(courseCode1)){

                    for (int k = 0; k < FinalPlan.length; k++){

                        for (int x = 0; x < FinalPlan[k].length; x++){

                            if (FinalPlan[k][x][0].equals(courseCode2)){

                                for (int row = 0; row < FinalPlan[k][x].length; row++){
                                String temp = FinalPlan[i][j][row];
                                FinalPlan[i][j][row] = FinalPlan[k][x][row];
                                FinalPlan[k][x][row] = temp;
                                }
                                assumedKnowledge(courseCode1, courseCode2);

                                return true;
                            }

                        }

                    }

                }

            }
        }

        return false;
    }

















































































































    public static void assumedKnowledgeWithCompletedCourses(String course2, String[] course3)
    {
        int Checker = 0, Checker2 =0, courseCount = 0 , aLength = 0, bLength = 0;
        String Error = "Nothing";

        for(int a=0;a < course3.length; a++ ) {

            for (int x = 0; x < FinalPlan[a].length; x++){
            courseCount += Integer.parseInt(FinalPlan[a][x][3]);}
            if (course3[a] == null)
            {
                continue;
            }
            else if (course3[a].contains(course2))
            {
                for (int d = 0; d <= a; d++) {
                    for (int e = 0; e < 4; e++) {
                        if ((FinalPlan[d][e][6]).contains(course3[a]))
                        {
                            Checker = 1;
                            //System.out.println("Error! Need to complete " + FinalPlan[a][b][0]);
                        }
                        else
                        {
                            Error = FinalPlan[d][e][6];
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
                        if (FinalPlan[g][f][0].contains("COMP3851A")) {
                            Checker2 = 1;
                        }
                    }
                }

            }
        }
        if(Checker == 0 )
        {
            System.out.println("Error! Need to complete " + Error + "before Enrolling in");
        }
        if(Checker2 == 0)
        {
            System.out.println("Error! Need to complete COMP3851A before COMP3851B");
        }
    }



    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////




    private static void assumedKnowledge(String course2, String course3 )
    {
        int Checker = 0, Checker2 =0, courseCount = 0 , aLength = 0, bLength = 0;
        String Error = "Nothing";

        for(int a=0;a < FinalPlan.length; a++ )
        {
            for(int b = 0; b < FinalPlan[a].length; b++)
            {
                courseCount++;
                if (FinalPlan[a][b][6] == null)
                {
                    continue;
                }
                else if(FinalPlan[a][b][0].contains(course2))
                {
                    for(int d = 0; d <= a ; d++ )
                    {
                        for(int e = 0; e < 4 ; e++ )
                        {
                            if((FinalPlan[a][b][6]).contains(FinalPlan[d][e][0]))
                            {
                                Checker = 1;
                                System.out.println("Error! Need to complete " + FinalPlan[a][b][0]);
                            }
                            else
                            {
                                Error = FinalPlan[a][b][6];
                            }
                        }
                    }
                }
                else if(FinalPlan[a][b][0].contains("COMP3851A"))
                {
                    if(courseCount < 14)
                    {
                        System.out.println("Error! Need to complete " + (14 - courseCount) + " more courses to do this course" );
                    }
                }
                else if(FinalPlan[a][b][0].contains("COMP3851B"))
                {
                    for(int g=0; g< a; g++)
                    {
                        for(int f=0; f < 4; f++)
                        {
                            if(FinalPlan[g][f][0].contains("COMP3851A"))
                            {
                                Checker2 = 1;
                            }
                        }
                    }

                }
            }
            if(FinalPlan[a] != null)
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







    private static void prerequisite(String course2)
    {
        for(int a=0; a<FinalPlan.length; a++)
        {
            for(int b=0; b<FinalPlan[a].length; b++)
            {
                if (FinalPlan[a][b][5] == null)
                {
                    continue;
                }
                else if(FinalPlan[a][b][0].contains(course2))
                {
                    for(int d=0; d<=a; d++)
                    {
                        for(int e=0; e<4; e ++)
                        {
                            if(FinalPlan[a][b][5].contains(FinalPlan[d][e][0]))
                            {
                                System.out.print("Cannot enrol in " + course2 + " because " + FinalPlan[d][e][0] + " has been completed");
                            }
                        }
                    }
                }
            }
        }
    }



}

