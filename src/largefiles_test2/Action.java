package largefiles_test2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Action {
    final LocalDateTime time;
    final Program program;

    public Action(LocalDateTime time, Program program) {
        this.time = time;
        this.program = program;
    }

    public int getChanel() {
        return program.getChannel();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Program getProgram() {
        return program;
    }

    @Override
    public String toString() {
        if (program!=null) {
            return String.format("Turn on ch=%d in t=%d to watch pr=%s", 
                program.getChannel(), time.toEpochSecond(ZoneOffset.UTC), program.toString());
        } else {
            return String.format("Turn OFF in time=%d", 
                time.toEpochSecond(ZoneOffset.UTC));
        }
    }
    
    
}
