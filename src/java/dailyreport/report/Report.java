package dailyreport.report;

public class Report {
    private String[] tasks;
    private int fromHour, toHour;

    public Report() {
        tasks = new String[24];
        fromHour = 0;
        toHour = 23;
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