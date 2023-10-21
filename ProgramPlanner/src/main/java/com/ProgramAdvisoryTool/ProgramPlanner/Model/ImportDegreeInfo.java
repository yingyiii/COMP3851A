package com.ProgramAdvisoryTool.ProgramPlanner.Model;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportDegreeInfo {

    static String[] output = new String[0]; //This will need to be function to get a list of the degrees.
    static String[] Majoroutput = new String[0];
    static String[]  Coursesoutput = new String[0];
    public void GetDegree(){
        output = new String[0];
        Majoroutput = new String[0];
        Coursesoutput = new String[0];
        Connection connection = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;
        try {

            //create connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uondb", "root", "0000");

            //create statement
            String s1 = "Select DegreeName from degrees";
            PreparedStatement preparedStatement1 = connection.prepareStatement(s1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //preparedStatement1.setString(1, "Core");
            resultSet1 = preparedStatement1.executeQuery();

            List<String> resultList = new ArrayList<>(); // Create a List to store the values

            while (resultSet1.next()) {
                String courseID = resultSet1.getString("DegreeName");
                resultList.add(courseID); // Add each value to the List
            }

            // Convert the List to an array
            output = resultList.toArray(new String[0]);

        }

        catch (
                SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
                if (resultSet2 != null) {
                    resultSet2.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public String[] DegreeList()
    {

        return output;
    }

    public void GetMajor(String Degree){
        output = new String[0];
        Majoroutput = new String[0];
        Coursesoutput = new String[0];
        Connection connection = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;
        try {

            //create connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uondb", "root", "0000");

            //create statement
            String s1 = "Select MajorName from majors where DegreeName = '"+Degree+"'";
            PreparedStatement preparedStatement1 = connection.prepareStatement(s1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //preparedStatement1.setString(1, "Core");
            resultSet1 = preparedStatement1.executeQuery();

            List<String> resultList = new ArrayList<>(); // Create a List to store the values

            while (resultSet1.next()) {
                String courseID = resultSet1.getString("MajorName");
                resultList.add(courseID); // Add each value to the List
            }

            // Convert the List to an array
            Majoroutput = resultList.toArray(new String[0]);

        }

        catch (
                SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
                if (resultSet2 != null) {
                    resultSet2.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public String[] MajorList()
    {

        return Majoroutput;
    }


    public void GetCourses(){
        output = new String[0];
        Majoroutput = new String[0];
        Coursesoutput = new String[0];
        Connection connection = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;

        try {

            //create connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uondb", "root", "0000");

            //create statement
            String s1 = "SELECT Course_ID FROM it_courses UNION SELECT Course_ID FROM cs_courses ORDER BY Course_ID;";
            PreparedStatement preparedStatement1 = connection.prepareStatement(s1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //preparedStatement1.setString(1, "Core");
            resultSet3 = preparedStatement1.executeQuery();

            List<String> resultList = new ArrayList<>();

            while (resultSet3.next()) {
                String courseID = resultSet3.getString("Course_ID");
                resultList.add(courseID); // Add each value to the List
            }

            // Convert the List to an array
            Coursesoutput = resultList.toArray(new String[0]);

        }

        catch (
                SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (resultSet1 != null) {
                    resultSet1.close();
                }
                if (resultSet2 != null) {
                    resultSet2.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public String[] CoursesList()
    {

        return Coursesoutput;
    }

}