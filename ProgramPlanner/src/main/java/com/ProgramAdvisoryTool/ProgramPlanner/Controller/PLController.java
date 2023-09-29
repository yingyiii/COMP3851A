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
    @PostMapping("/SendInfo")
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


