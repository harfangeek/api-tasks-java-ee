/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package tasks.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import tasks.Task;
import tasks.util.DBManager;

/**
 *
 * @author user
 */
@Path("service")
public class Service {
    
    private static Map<Integer, Task> tasks = new HashMap<>();
    private static final String SUCCESS_RESULT="<result>success</result>";
    private static final String FAILURE_RESULT="<result>failure</result>";
    
    static{
        for (int i = 0; i < 10; i++) {
            int id = i+1;
            Task task = new Task();
            task.setId(id);
            task.setTitle("Title"+id);
            task.setDescription("Description"+id);
            
            tasks.put(id, task);
        }
    }
    private DBManager manager = new DBManager();
    
    
    @GET
    @Path("/getTaskByIdJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Task getTaskByIdJSON(@PathParam("id")int id){
        return manager.getTaskById(id);
    }
    
    @GET
    @Path("/getAllTasksXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Task> getAllTasksXML(){
        return manager.getAllTasks();
    }
    
    @GET
    @Path("/getAllTasksJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getAllTasksJSON(){
        return manager.getAllTasks();
    }
    
   @DELETE
   @Path("/deleteTask/{taskid}")
   @Produces(MediaType.APPLICATION_JSON)
   public String deleteUser(@PathParam("taskid") int taskid){
      int result = manager.deleteTask(taskid);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
    
   @PUT
   @Path("/createTask")
   @Produces(MediaType.APPLICATION_JSON)
   public String createTask(@FormParam("title") String title,
      @FormParam("description") String description) {
      Task task = new Task(title, description);
      int result = manager.insertTask(task);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }    
   
   @PUT
   @Path("/updateTask")
   @Produces(MediaType.APPLICATION_JSON)
   public String updateTask(@FormParam("id") int id,
      @FormParam("title") String title,
      @FormParam("description") String description) {
      Task task = new Task(id, title, description);
      int result = manager.updateTask(task);
      System.out.print("UPDATE DATA");
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }    
}
