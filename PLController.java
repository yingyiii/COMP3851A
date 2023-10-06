package com.ProgramAdvisoryTool.ProgramPlanner.Controller;

import com.ProgramAdvisoryTool.ProgramPlanner.Model.StudentInfoRequest;
import com.ProgramAdvisoryTool.ProgramPlanner.PlanGenerator;
import com.ProgramAdvisoryTool.ProgramPlanner.Service.PLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ProgramPlanner")
@CrossOrigin
public class PLController {

    PlanGenerator PlanGenerator = new PlanGenerator();
@Autowired
    private PLService PLService;

    //Front end gets the list of degrees from the back end. Back end gets them from database.
    @GetMapping("/getDegrees")
    public String[] getDegrees() {
        String[] output = {"degree1", "degree2", "degree3"}; //This will need to be function to get a list of the degrees.
        return output ;
    }

    //Front end gets the list of courses from the back end. Back end gets them from database.
    @GetMapping("/getCourses")
    public String[] getCourses() {
        String[] output = {"CODE1", "CODE2", "CODE3"}; //This will need to be function to get a list of the courses.
        return output ;
    }

    //Front end posts the chosen degree so that majors can be updated to match the degree.
    String degree;
    @PostMapping("/postDegree")
    public void postDegree(@RequestBody String degree) {
        this.degree = degree;
    }

    //Back end checks the inputed degree and gets the associated majors from database.
    @GetMapping("/getMajors")
    public String[] getMajors() {
        if(degree.equals("degree1")) { //This if-else block will need to be a function to get majors based of the degree provided by front end.
            String[] output = {"major11", "major12", "major13"};
            return output;
        }
        else if(degree.equals("degree2")) {
            String[] output = {"major21", "major22", "major23"};
            return output;
        }
        else if(degree.equals("degree3")) {
            String[] output = {"major31", "major32", "major33"};
            return output;
        }
        else {
            String[] output = {"Hello"};
            return output;
        }
    }



    @PostMapping("/sendInfo")
    public String addStudentInfo(@RequestBody StudentInfoRequest studentInfoRequest) {
        String degree = studentInfoRequest.getDegree();
        String major = studentInfoRequest.getMajor();
        String completedCourses = studentInfoRequest.getCompletedCourses();
        Integer units = studentInfoRequest.getUnits();
        Integer startingSemester = studentInfoRequest.getStartingsemester();

        PlanGenerator.CreatePlan(degree, major, completedCourses, units, startingSemester);

        return "New input added";
    }

    @GetMapping("/getInfo")
    public String[][][] getAllstudent() {



        return PlanGenerator.GetThePlan();




    }



}


