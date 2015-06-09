package com.jhindin.phonotone.instruments;

/**
 * Created by jhindin on 09/06/15.
 */
public class Instrument {
    public String name;
    public int pc; // program change

    public Instrument(String name, int pc) {
        this.name = name;
        this.pc = pc;
    }
}
