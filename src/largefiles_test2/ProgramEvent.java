package largefiles_test2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ProgramEvent implements Comparable<ProgramEvent>{
    Program program;
    boolean turnOn;

    public ProgramEvent(Program program, boolean turnOn) {
        this.program = program;
        this.turnOn = turnOn;
    }
    
    public Program getProgram() {
        return program;
    }

    public boolean isTurnOn() {
        return turnOn;
    }
    
    public LocalDateTime getEventTime() {
        return turnOn ? program.getStartTime() : program.getEndTime();
    }
    
    public boolean isMorePriorityThan(ProgramEvent programEvent) {
        return program.getPriority() > programEvent.getProgram().getPriority();
    }
    
    public boolean isLaterThan(ProgramEvent programEvent) {
        return getEventTime().isAfter(programEvent.getEventTime());
    }

    @Override
    public String toString() {
        return String.format(
                "programEvent=[ turnOn:%b time=%d Program=%s ]", 
                turnOn, getEventTime().toEpochSecond(ZoneOffset.UTC), program.toString());
    }

    @Override
    public int compareTo(ProgramEvent o) {
        return getEventTime().compareTo(o.getEventTime());
    }
    
    
    
    
}

