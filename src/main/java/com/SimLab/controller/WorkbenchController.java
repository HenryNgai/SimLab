package com.SimLab.controller;


import com.SimLab.model.dao.Instruction;
import com.SimLab.model.dao.Lab;
import com.SimLab.model.workbench.MaterialObjects.BkendContainer;
import com.SimLab.model.workbench.MaterialObjects.BkendTool;
import com.SimLab.model.workbench.WorkbenchBkend;
import com.SimLab.service.LabService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
public class WorkbenchController {

    @Autowired
    private LabService labService;


    private WorkbenchBkend workbenchBkend;

    @GetMapping(value="/workbench")
    public ModelAndView workbench(@RequestParam String labId){
        ModelAndView modelAndView = new ModelAndView();
        Lab lab = labService.findByLabId(Integer.parseInt(labId));
        List<String> instruct = new ArrayList<>();
        List<Instruction> instructions = new ArrayList(lab.getInstructions());
        instructions.sort(Comparator.comparing(e -> e.getStepNumber()));
        for(Instruction inst : instructions){
            instruct.add(inst.toString());
        }
        modelAndView.setViewName("workbench");
        modelAndView.addObject("lab", lab);
        modelAndView.addObject("solutions", lab.getSolutions());
        modelAndView.addObject("containers", lab.getContainers());
        modelAndView.addObject("tools", lab.getTools());
        modelAndView.addObject("instructions", instruct);
        workbenchBkend = new WorkbenchBkend(lab);

        workbenchBkend.addMaterial("HCl");
        workbenchBkend.addMaterial("NaCl");
        workbenchBkend.addMaterial("NaCl");
        workbenchBkend.interact("Mix", "Beaker1", "Beaker2", null, 4, 0);
        workbenchBkend.interact("Mix", "Beaker2", "Beaker3", null, 11, 0);


        for(BkendContainer c: workbenchBkend.getContainers()){
            c.update();
        }
        List<Boolean> test = workbenchBkend.verifyLab();


        return modelAndView;
    }


    @RequestMapping(value = "/interact", method = RequestMethod.POST)
    public String interact(){
        return "";
    }

    //Routing for moveToWorkBench ajax call.
    @ResponseBody
    @RequestMapping(value = "/moveToWorkBench", method = RequestMethod.POST)
    public BkendContainer moveToWorkBench(@RequestParam String materialName){
        BkendContainer container = workbenchBkend.addMaterial(materialName);
        var c = container;
        return c;
    }

    @ResponseBody
    @RequestMapping(value = "/moveToolToWorkBench", method = RequestMethod.POST)
    public BkendTool moveToolToWorkBench(@RequestParam String materialName){
        BkendTool tool = workbenchBkend.addTool(materialName);
        var c = tool;
        return c;
    }

    //Routing for moveToInventory ajax call.
    @ResponseBody
    @RequestMapping(value = "/moveToInventory", method = RequestMethod.POST)
    public String moveToInventory(@RequestParam String materialName){
        workbenchBkend.removeMaterial(materialName);
        return "";
    }

    //Routing for pour ajax call.
    @ResponseBody
    @RequestMapping(value = "/pour", method = RequestMethod.POST)
    public List<BkendContainer> pour(@RequestParam String beaker1, @RequestParam String beaker2, @RequestParam String amount){
        workbenchBkend.interact("Mix",beaker1,beaker2,null,Integer.parseInt(amount),0);
        List<BkendContainer> containers = new ArrayList<BkendContainer>();
        containers.add(workbenchBkend.getContainer(beaker1));
        containers.add(workbenchBkend.getContainer(beaker2));
        return containers;
    }

    //Routing for mix ajax call.
    @ResponseBody
    @RequestMapping(value = "/mix", method = RequestMethod.POST)
    public String mix(@RequestParam String beaker1, @RequestParam String time){

        return "";
    }

    //Routing for heat ajax call.
    @ResponseBody
    @RequestMapping(value = "/heat", method = RequestMethod.POST)
    public String heat(@RequestParam String beaker1, @RequestParam String temp){

        return "";
    }



}
