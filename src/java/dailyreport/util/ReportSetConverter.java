package dailyreport.util;

import java.util.StringTokenizer;

import dailyreport.business.ReportSet;
import dailyreport.report.Report;

public class ReportSetConverter {
    static public Report ReportSetToReport(ReportSet rs) {
        Report r = new Report();
        
        if (rs != null) {
            /*Set tasks*/
            String[] tasks = rs.getTaskString().split("\n", 24);
            for (int hour = 0; hour < 24; hour++)
                r.setTask(hour, tasks[hour]);
            
            /*Set hour range*/
            r.setFromHour(rs.getFromHour());
            r.setToHour(rs.getToHour());
        }
        
        return r;
    }
    
    static public ReportSet ReportToReportSet(Report r, String id) {
        ReportSet rs = new ReportSet();
        
        rs.setId(id); //set id
        
        /*Set taskString*/
        String tokenized = "";
        for (int i = 0; i < 23; i++)
            tokenized += r.getTask(i) + "\n";
        tokenized += r.getTask(23);
        rs.setTaskString(tokenized);
        
        /*Set hour range*/
        rs.setFromHour(r.getFromHour());
        rs.setToHour(r.getToHour());
        
        return rs;
    }
}
