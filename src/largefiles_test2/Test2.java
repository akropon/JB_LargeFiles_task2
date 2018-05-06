package largefiles_test2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Test2 {
    
    /**
     * Пример работы программы
     */
    public static void main(String[] args) {
        ArrayList<Action> actions = getPlan(generateTVShows());
        for (Action action : actions)
            System.out.println(action);
    }
    
    /**
     * Составляет план просмотра каналов на основе входного списка телепередач
     * 
     * @param tvShows - список телепередач
     * @return последовательность действий по переключению каналов
     */
    public static ArrayList<Action> getPlan(TVShow[] tvShows) {
        
        // Создаем и заполняем список событий начал и концов твПрограмм
        LinkedList<ProgramEvent> programEvents = new LinkedList();
        for (TVShow tvShow : tvShows) {
            // добавляем начало твПередачи как событие
            programEvents.add(new ProgramEvent(tvShow, true));
            // добавляем конец твПередачи как событие
            programEvents.add(new ProgramEvent(tvShow, false));
        }
        
        // дебаг
        /*System.out.println("Not sorted events:");
        for (ProgramEvent pe : programEvents)
            System.out.println("  "+pe.toString()); */
        
        // Упорядочиваем события по времени
        Collections.sort(programEvents);
        
        // дебаг
        /*System.out.println("Sorted events:");
        for (ProgramEvent pe : programEvents)
            System.out.println("  "+pe.toString());*/
        
        // Создаем стэк с приоритетом для твПрограмм
        PriorityQueue<Program> programsStack = new PriorityQueue<>();
        // Создаем список действий (по переключению каналов)
        ArrayList<Action> actionsList = new ArrayList();
        // Изначально не смотрим ничего
        Program currentProgram = null; // выключен
        // Идем по событиям в хронологическом порядке
        for (ProgramEvent programEvent : programEvents) {
            // если собитие означает начало программы, то добавляем программу в стек
            // иначе забираем программу из стека
            if (programEvent.turnOn)
                programsStack.add(programEvent.getProgram());
            else
                programsStack.remove(programEvent.getProgram());
            
            
            // если верхушка стека изменилась (т.е. либо появилась другая более приоритетная программа,
            // либо закончилась самая приоритетная, то надо переключать канал
            if (programsStack.peek()!=currentProgram) {
                currentProgram = programsStack.peek();        
                actionsList.add(new Action(
                        programEvent.getEventTime(), currentProgram));
            }
            
            // дебаг
            /*System.out.println("STACK: "+programsStack.toString());
            System.out.println("HEAD: "+programsStack.peek());*/
        }
        
        // фильтр (устраняем одновременные действия и включения одного и того канала)
        int i=actionsList.size()-1;
        while (i>0) {
            if (actionsList.get(i-1).getTime().equals( actionsList.get(i).getTime() )) {
                // если предыдущее действие совпало по времени с текущим, то предыдущее бессмысленно и его надо удалить
                actionsList.remove(i-1);
            } else if (actionsList.get(i-1).getChanel()==actionsList.get(i).getChanel()) {
                // если в предыдущем действии включили канал, 
                // который включили и в текущем, то текущее действие бессмысленно и его надо удалить
                actionsList.remove(i);
            }
            i--;
        }
        
        return actionsList;
    }
    
    /**
     * Генерация набора программ для примера
     * @return набор программ
     */
    private static TVShow[] generateTVShows() {
        
        // { канал, начало, конец, преоритет }
        int[][] arr = { {200,60,65,20},
                        {200,40,50,15},
                        {300,5,25,10},
                        {400,10,20,20},
                        {500,35,45,25},
                        {200,65,70,20},
        };
        
        String[] names = {  "E",
                            "D",
                            "A",
                            "B",
                            "C",
                            "E2"
        };
        
        TVShow[] shows = new TVShow[arr.length];
        for (int i=0; i<arr.length; i++)
            shows[i] = new TVShow(arr[i][0], gt(arr[i][1]), gt(arr[i][2]), arr[i][3], names[i]);
        
        return shows;
    }
    
    /**
     * Переводит секунды в объект времени
     * @param seconds - секунды
     * @return объект времени
     */
    private static LocalDateTime gt(int seconds) {
        return LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC);
    }
}

