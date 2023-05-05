import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Course
{
    private String courseCode, courseName;
    private int courseYear;

    
    public Course(String Degree)
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uondb", "root", "0000");

            // create a statement object to execute queries
            Statement statement = connection.createStatement();

            // execute the query to get all courses for the specified degree from the database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+ Degree+"_courses");

            // loop through the result set and create a Course object for each course retrieved from the database
            while (resultSet.next()){
                // get the course code, name and year from the result set
                courseCode = resultSet.getString("Course_Id");
                courseName = resultSet.getString("Course_Name");
                courseYear = resultSet.getInt("Course_Level");

                // create a new Course object with the retrieved data
                Course course = new Course(courseCode, courseName, courseYear);

                // print the retrieved course details
                System.out.println(resultSet.getString("Course_Id")+" || "+resultSet.getString("Course_Name")+" || "+resultSet.getString("Course_Level"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        //courseCode = "Course_Id";
        //courseName = "";
        //courseYear = 0;


    }
    public Course(String courseCode, String courseName, int courseYear)
    {
        this.setcourseCode(courseCode);
        this.setcourseName(courseName);
        this.setcourseYear(courseYear);
    }
    public void setcourseCode(String courseCode)
    {
        this.courseCode = courseCode;

    }
    public void setcourseName(String courseName)
    {
        this.courseName = courseName;

    }
    public void setcourseYear(int courseYear)
    {
        this.courseYear = courseYear;

    }

/*    public String getcourseCode()
    {
        return courseCode;
    }

    public String getcourseName()
    {
        return courseName;
    }

    public int getcourseYear()
    {
        return courseYear;
    }*/
}
