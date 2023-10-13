package com.ProgramAdvisoryTool.ProgramPlanner.Controller;

import com.ProgramAdvisoryTool.ProgramPlanner.Model.ImportDegreeInfo;
import com.ProgramAdvisoryTool.ProgramPlanner.Model.StudentInfoRequest;
import com.ProgramAdvisoryTool.ProgramPlanner.PlanGenerator;
import com.ProgramAdvisoryTool.ProgramPlanner.Service.PLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ProgramPlanner")
@CrossOrigin
public class PLController {
    ImportDegreeInfo importDegreeInfo = new ImportDegreeInfo();
    PlanGenerator PlanGenerator = new PlanGenerator();
    @Autowired
    private PLService PLService;

    //Front end gets the list of degrees from the back end. Back end gets them from database.
    @GetMapping("/getDegrees")
    public String[] getDegrees() {
        importDegreeInfo.GetDegree();
        return importDegreeInfo.DegreeList();
    }
    //Front end posts the chosen degree so that majors can be updated to match the degree.
    @PostMapping("/postDegree")
    public void postDegree(@RequestBody String degree) {
        importDegreeInfo.GetMajor(degree);

    }
    //Front end gets the list of courses from the back end. Back end gets them from database.
    @GetMapping("/getCourses")
    public String[] getCourse() {
        importDegreeInfo.GetCourses();
        return importDegreeInfo.CoursesList();
    }



    //Back end checks the inputed degree and gets the associated majors from database.
    @GetMapping("/getMajors")
    public String[] getMajors() {
        return importDegreeInfo.MajorList();
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

