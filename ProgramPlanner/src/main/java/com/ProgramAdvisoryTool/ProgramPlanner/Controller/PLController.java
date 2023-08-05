package com.ProgramAdvisoryTool.ProgramPlanner.Controller;


import com.ProgramAdvisoryTool.ProgramPlanner.Service.PLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ProgramPlanner")
@CrossOrigin
public class PLController {
String name, degree;
@Autowired
    private PLService PLService;
    @PostMapping("/SendInfo")
    public String addStudentInfo(@RequestBody String Name, String Degree) {

        name = Name;
        degree = Degree;
        return "New input";
    }
    @GetMapping("/getInfo")
    public String getAllstudent() {
        return name + " " + degree;



    }
}


