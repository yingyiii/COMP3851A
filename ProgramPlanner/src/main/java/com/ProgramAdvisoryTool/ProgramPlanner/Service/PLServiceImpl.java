package com.ProgramAdvisoryTool.ProgramPlanner.Service;
import com.ProgramAdvisoryTool.ProgramPlanner.Model.ProgramPlanner;
import com.ProgramAdvisoryTool.ProgramPlanner.Repository.RepositoryProgramPlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PLServiceImpl implements PLService{
    @Autowired
    private RepositoryProgramPlanner RepositoryProgramPlanner;


    @Override
    public List<ProgramPlanner> getplan() {
        return RepositoryProgramPlanner.findAll();

    }
}
