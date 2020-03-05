package dailyreport.business;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class ReportSet implements Serializable {
    @Id
    private String id;
    
    private String taskString;
    private int fromHour, toHour;

    public ReportSet() {
        
    }
    
    public String getId() {
        return id;
    }    
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTaskString() {
        return taskString;
    }
    
    public void setTaskString(String taskString) {
        this.taskString = taskString;
    }
    
    public int getFromHour() {
        return this.fromHour;
    }
    
    public void setFromHour(int fromHour) {
        this.fromHour = fromHour;
    }
    
    public int getToHour() {
        return this.toHour;
    }
    
    public void setToHour(int toHour) {
        this.toHour = toHour;
    }
}
