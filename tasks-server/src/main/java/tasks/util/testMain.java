/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks.util;

import java.util.List;
import tasks.Task;

/**
 *
 * @author user
 */
public class testMain {
    public static void main(String[] args) {
        DBManager manager = new DBManager();
        List<Task> tasks = manager.getAllTasks();
        for(Task task : tasks){            
            System.out.print(task.getTitle());
        }
        
        
        manager.getTaskById(79);
    }
}
