
/**
 * Semester1 will provide all the relevant classes avaliable for students
 *
 * Ben Kamprad
 * @18/04/2023
 */
import java.util.*;
import java.io.*;
public class Semester1
{
    static Scanner console = new Scanner(System.in);
    private String courseCode;
    private int courseCount = 4;
    private Course[] Courses;
    /**
     * Constructor for objects of class Semester1
     */
    public Semester1()
    {
        Courses = new Course[courseCount];
    }
    private void run() 
    {
        for(int i=0; i<courseCount;i++)
        {
            Courses[i] = new Course();
            setCourseData(Courses[i], " code" + i, " name" + i, i);
        }
        display();
    }
    
    public static void main(String[] args) // Main creates new clinc and intialize's run in clinic class
    {
            Semester1 semester1 = new Semester1();
            semester1.run();
    }
    
    private void setCourseData(Course c, String code, String name, int year)
    {
        c.setcourseCode(code);
        c.setcourseName(name);
        c.setcourseYear(year);
    }
    public String semesterString(int i)
    
    {
        return("Course " + i + " : " + Courses[i].getcourseName() + " : " + Courses[i].getcourseCode() + " : " + Courses[i].getcourseYear() );
    }
    private void display()
    {
        for(int d=0; d<courseCount; d++)
        {
            System.out.println(semesterString(d));
        }
    }
}
