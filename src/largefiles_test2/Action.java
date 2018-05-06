package largefiles_test2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Действие - описывает, когда и на какую программу надо переключиться.
 * Если программа ссылается на нулевое значение, то действие подразумевает
 * выключение средства просмотра телепередач
 * @author akropon
 */
public class Action {
    final LocalDateTime time;
    final Program program;

    public Action(LocalDateTime time, Program program) {
        this.time = time;
        this.program = program;
    }

    public int getChanel() {
        return program==null ? -1 : program.getChannel();
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
            return String.format("Turn on ch=%d in t=%d", 
                program.getChannel(), time.toEpochSecond(ZoneOffset.UTC));
        } else {
            return String.format("Turn OFF in time=%d", 
                time.toEpochSecond(ZoneOffset.UTC));
        }
    }
    
    
}
