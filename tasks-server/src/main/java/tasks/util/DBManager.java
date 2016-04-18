/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package tasks.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tasks.Task;

/**
 *
 * @author user
 */
public class DBManager {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/tasks";
    
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    
    Connection conn = null;
    Statement stmt = null;
    
    public List<Task> getAllTasks(){
        
        List<Task> listTasks = new ArrayList<>();
        try{
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, title, description FROM tasks";
            ResultSet rs = stmt.executeQuery(sql);
            
            //STEP 5: Extract data from result set
            Task currentTask = null;
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                
                //Display values
                currentTask = new Task();
                currentTask.setId(id);
                currentTask.setTitle(title);
                currentTask.setDescription(description);
                
                listTasks.add(currentTask);
                
            }
            //STEP 6: Clean-up environment
        }catch(Exception e){
            //Handle errors for JDBC
            e.printStackTrace();
        }
        
        return listTasks;
    }
    
    public Task getTaskById(int idtask){
        
        List<Task> listTasks = new ArrayList<>();
        try{
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, title, description FROM tasks WHERE id = "+idtask+";";
            ResultSet rs = stmt.executeQuery(sql);
            
            //STEP 5: Extract data from result set
            Task currentTask = null;
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                
                //Display values
                currentTask = new Task();
                currentTask.setId(id);
                currentTask.setTitle(title);
                currentTask.setDescription(description);
                
                listTasks.add(currentTask);
                
            }
            //STEP 6: Clean-up environment
        }catch(Exception e){
            //Handle errors for JDBC
            e.printStackTrace();
        }
        
        return listTasks.get(0);
    }
    
    public int insertTask(Task task){
        
        try{
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO tasks (title, description)" +
                    " VALUES ('"+ task.getTitle() +"', '"+ task.getDescription() +"');";
            
            System.out.println(sql);
            
            int rs = stmt.executeUpdate(sql);
            
            //STEP 6: Clean-up environment
            
            return rs;
        }catch(Exception e){
            //Handle errors for JDBC
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public int deleteTask(int idTask){
        
        try{
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM tasks WHERE id =" + idTask + ";";
            
            System.out.println(sql);
            
            int rs = stmt.executeUpdate(sql);            
            
            return rs;
        }catch(Exception e){
            //Handle errors for JDBC
            e.printStackTrace();
        }
        
        return 0;
    }
    
     public int updateTask(Task task){
        
        try{
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE tasks SET title = '"+ task.getTitle() +"', description = '"+ task.getDescription()+"' WHERE id = " + task.getId() +";";
            
            System.out.println(sql);
            
            int rs = stmt.executeUpdate(sql);            
            
            return rs;
        }catch(Exception e){
            //Handle errors for JDBC
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public DBManager(){
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    
}
