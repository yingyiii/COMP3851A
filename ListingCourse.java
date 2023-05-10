import java.util.Scanner;

public class ListingCourse {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        /*

        System.out.println("Please enter the courses you wish to take");
        String[] enteredCourse = new String[4];
        enteredCourse[0] = scan.next();
        enteredCourse[1] = scan.next();
        enteredCourse[2] = scan.next();
        enteredCourse[3] = scan.next(); 
        
        */

        /*

        System.out.println("Please enter the number of courses you wish to take");
        int n = scan.next();
        System.out.println("Please enter the course code");
        String[] enteredCourse = new String[n];
        System.out.println("Please enter the courses level");
        int[] courseLevel = new Int[n];

         */

        String [][] courses =   {
                            {"COMP1010", "1000", "Semester 1"}, {"COMP1140", "1000", "Semester 2"}, {"MATH1110", "1000", "Semester 1 & 2"}, {"MATH1510", "1000", "Semester 2"}, {"SENG1050", "1000", "Semester 2"}, {"SENG1110", "1000", "Semester 1 & 2"}, {"SENG1120", "1000", "Semester 1 & 2"}, 
                            {"COMP2230", "2000", "Semester 2"}, {"COMP2240", "2000", "Semester 2"}, {"COMP2270", "2000", "Semester 1"}, {"SENG2130", "2000", "Semester 1"}, {"SENG2250", "2000", "Semester 2"}, {"SENG2260", "2000", "Semester 2"}, {"SENG2200", "2000", "Semester 1"}, {"INFT2150", "2000", "Semester 2"}, {"SENG2050", "2000", "Semester 1"},    
                            {"COMP3851A", "3000", "Semester 1 & 2"}, {"COMP3851B", "3000", "Semester 1 & 2"}, {"INFT3800", "3000", "Semester 1"}, {"INFT3100", "3000", "Semester 1"}, {"SENG3320", "3000", "Semester 1"}, {"COMP3260", "3000", "Semester 1"}, {"COMP3320", "3000", "Semester 1"},   
                            {"SENG4500", "4000", "Semester 2"}        
                        };

        for (int i = 0; i < courses.length; i++){
            System.out.println();
            for(int j = 0; j < courses[i].length; j++){
                System.out.print(courses[i][j] + "  ");
            }
        }            

        
       































    }
}
