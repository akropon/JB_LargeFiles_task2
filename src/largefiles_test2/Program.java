package largefiles_test2;

import java.time.LocalDateTime;

public interface Program {
    int getChannel(); 
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    int getPriority(); 
}
