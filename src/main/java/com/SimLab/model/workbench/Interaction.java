package com.SimLab.model.workbench;

import com.SimLab.model.workbench.MaterialObjects.BkendContainer;
import com.SimLab.model.workbench.MaterialObjects.BkendTool;
import lombok.Data;

@Data
public class Interaction {
    String name;
    BkendContainer container1;
    BkendContainer container2;
    BkendTool tool;
    BkendContainer resultant1;
    BkendContainer resultant2;
    int stepNo;


    public Interaction(String name, BkendContainer cont1, BkendContainer cont2, BkendTool tool){
        this.name = name;
        container1 = null;
        container2 = null;
        this.tool = null;
        if(cont1 != null) {
            container1 = (BkendContainer)cont1.clone();
        }
        if(cont2 != null) {
            container2 = (BkendContainer)cont2.clone();
        }
        if(tool != null) {
            this.tool = (BkendTool)tool.clone();
        }
        resultant1 = null;
        resultant2 = null;
        stepNo = 0;
    }

    public void addResultant1(BkendContainer c){
        if(c!=null) this.resultant1 = (BkendContainer)c.clone();
    }
    public void addResultant2(BkendContainer c){
        if(c!=null)     this.resultant2 = (BkendContainer)c.clone();
    }

}
