package largefiles_test2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Test2 {
    
    public static void main(String[] args) {
        
        doIt();
    }
    
    static void doIt() {
        TVShow[] tvShows = generateTVShows();
        
        LinkedList<ProgramEvent> programEvents = new LinkedList();
        for (TVShow tvShow : tvShows) {
            programEvents.add(new ProgramEvent(tvShow, true));
            programEvents.add(new ProgramEvent(tvShow, false));
        }
        
        System.out.println("Not sorted events:");
        for (ProgramEvent pe : programEvents)
            System.out.println("  "+pe.toString());
        
        Collections.sort(programEvents);
        
        System.out.println("Sorted events:");
        for (ProgramEvent pe : programEvents)
            System.out.println("  "+pe.toString());
        
        PriorityQueue<Program> programsStack = new PriorityQueue<>();
        ArrayList<Action> actionsList = new ArrayList();
        Program currentProgram = null; // выключен
        for (ProgramEvent programEvent : programEvents) {
            if (programEvent.turnOn)
                programsStack.add(programEvent.getProgram());
            else
                programsStack.remove(programEvent.getProgram());
            
            
            
            if (programsStack.peek()!=currentProgram) {
                currentProgram = programsStack.peek();
                actionsList.add(new Action(
                        programEvent.getEventTime(), currentProgram));
            }
            
            System.out.println("STACK: "+programsStack.toString());
            System.out.println("HEAD: "+programsStack.peek());
        }
        
        System.out.println(actionsList);
    }

    
    
    
    
    
    
    
    
    private static TVShow[] generateTVShows() {
        /*int[][] arr = { {1,3,7,10},
                        {1,9,13,10},
                        {3,6,11,20},
                        {4,10,12,30}
        };
        
        String[] names = {  "A",
                            "B",
                            "C",
                            "D"
        };*/
        
        
        int[][] arr = { {100,60,65,20},
                        {200,40,50,15},
                        {300,5,25,10},
                        {400,10,20,20},
                        {500,35,45,25},
        };
        
        String[] names = {  "E",
                            "D",
                            "A",
                            "B",
                            "C"
        };
        
        
        TVShow[] shows = new TVShow[arr.length];
        for (int i=0; i<arr.length; i++)
            shows[i] = new TVShow(arr[i][0], gt(arr[i][1]), gt(arr[i][2]), arr[i][3], names[i]);
        
        return shows;
    }
    
    private static LocalDateTime gt(int seconds) {
        return LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC);
    }
}

