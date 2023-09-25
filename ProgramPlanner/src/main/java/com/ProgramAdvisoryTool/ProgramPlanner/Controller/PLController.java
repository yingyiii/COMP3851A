package com.ProgramAdvisoryTool.ProgramPlanner.Controller;

import com.ProgramAdvisoryTool.ProgramPlanner.PlanGenerator;
import com.ProgramAdvisoryTool.ProgramPlanner.Service.PLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ProgramPlanner")
@CrossOrigin
public class PLController {
String name, degree;
    PlanGenerator PlanGenerator = new PlanGenerator();
@Autowired
    private PLService PLService;
    @PostMapping("/SendInfo")
    public String addStudentInfo(@RequestBody String Degree, String Major, String CompletedCourses, String DegreeLength) {
        PlanGenerator.CreatePlan("","", "COMP1010",4,1);


        return "New input added";
    }
    @GetMapping("/getInfo")
    public String[][][] getAllstudent() {



        return PlanGenerator.GetThePlan();




    }



}


