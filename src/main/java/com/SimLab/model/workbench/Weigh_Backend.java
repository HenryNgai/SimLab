package com.SimLab.model.workbench;

import com.SimLab.model.dao.Instruction;

public class Weigh_Backend implements InstructionInterf{
    public String material;


    @Override
    public boolean verify() {
        return false;
    }
}
