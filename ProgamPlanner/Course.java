
/**
 * Course is the data relevant to the semester
 *
 * Ben Kamprad
 * @18/04/2023
 */
import java.util.*;
import java.io.*;
public class Course
{
    private String courseCode, courseName;
    private int courseYear;

    
    public Course()
    {
        courseCode = "*";
        courseName = "Blank";
        courseYear = 0;
    }
    public Course(String courseCode, String courseName, int courseYear)
    {
        setcourseCode(courseCode);
        setcourseName(courseName);
        setcourseYear(courseYear);
    }
    public void setcourseCode(String courseCode)
    {
        this.courseCode = courseCode;
    }
    public String getcourseCode()
    {
        return courseCode;
    }
    public void setcourseName(String courseName)
    {
        this.courseName = courseName;
    }
    public String getcourseName()
    {
        return courseName;
    }
    public void setcourseYear(int courseYear)
    {
        this.courseYear = courseYear;
    }
    public int getcourseYear()
    {
        return courseYear;
    }
}
