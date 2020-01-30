package dailyreport.report;

import java.util.Date;

public class Report {
    private Date date;
    private String[] tasks;

    public Report() {
        date = new Date();
        tasks = new String[24];
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return date;
    }

    public void setTask(int hour, String task) {
        tasks[hour] = task;
    }

    public String getTask(int hour) {
           String task = tasks[hour];

           if (task == null)
                   return "";
           else
                   return task;
    }
}