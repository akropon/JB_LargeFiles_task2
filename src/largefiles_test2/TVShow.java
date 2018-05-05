package largefiles_test2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

class TVShow implements Program, Comparable<Program>{

    private final int channel;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final int priority;
    private final String name;

    public TVShow(int channel, LocalDateTime startTime, LocalDateTime endTime, int priority) {
        this.channel = channel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        name = "noname";
    }

    public TVShow(int channel, LocalDateTime startTime, LocalDateTime endTime, int priority, String name) {
        this.channel = channel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.name = name;
    }
    
    
    
    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    
    public String toStringFull() {
        return String.format(
                "tvShow=[ name=%s chanel=%d priority=%d startTime=%d finishTime=%d ]", 
                name, channel, priority, startTime.toEpochSecond(ZoneOffset.UTC), endTime.toEpochSecond(ZoneOffset.UTC));
    }
    
    @Override
    public String toString() {
        return String.format(
                "tv=[ n=%s ch=%d p=%d st=%d et=%d ]", 
                name, channel, priority, startTime.toEpochSecond(ZoneOffset.UTC), endTime.toEpochSecond(ZoneOffset.UTC));
    }
    
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Program o) {
        return -Integer.compare(priority, o.getPriority());
    }
    
}
