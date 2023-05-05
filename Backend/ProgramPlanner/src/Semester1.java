import javax.swing.*;
import java.util.*;
import java.io.*;
public class Semester1
{

    // Define the number of courses available for the semester
    private int courseCount = 4;
    // Declare an array to hold the Course objects
    private Course[] Courses;
    // Initialize the course code
    private String courseCode;


    public Semester1()
    {
        Courses = new Course[courseCount];
    }
    private void run(String Selection)
    {
        Courses[0] = new Course(Selection);
        /*for(int i=0; i<courseCount;i++)
        {

            //setCourseData(Courses[i], " code" + i, " name" + i, i);
        }*/

        //display();
    }
    
    public static void main(String[] args)
    {
        Scanner Read = new Scanner(System.in);
        String Degree = "";
        System.out.println("Select Your Degree:");
        System.out.println(" Computer science - 1 || Information Technology - 2");

        // Get user input for the chosen degree
        // this will be changed as soon as we connect with frontend
        int ChosenDegree = Read.nextInt();
        switch(ChosenDegree) {
            case 1:
                Degree = "CS";
                break;
            case 2:
                Degree = "IT";
                break;
            default:

        }

            Semester1 semester1 = new Semester1();
            semester1.run(Degree);
    }

/*    private void setCourseData(Course c, String code, String name,int year)
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
            //System.out.println(semesterString(d));
        }
    }*/
}
