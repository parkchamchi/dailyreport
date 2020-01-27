package dailyreport.business;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
public class ReportSet implements Serializable {
    @Id
    private String id;
    @Temporal(TemporalType.DATE)
    private Date date;
    /*taskString consists as this:
    task of 00:00\n
    task of 01:00\n
    ...
    task of 23:00
    */
    private String taskString;

    public ReportSet() {
        
    }
    
    public String getId() {
        return id;
    }    
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getTaskString() {
        return taskString;
    }
    
    public void setTaskString(String taskString) {
        this.taskString = taskString;
    }
}
