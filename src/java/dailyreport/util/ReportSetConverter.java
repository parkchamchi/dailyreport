package dailyreport.util;

import java.util.StringTokenizer;

import dailyreport.business.ReportSet;
import dailyreport.report.Report;
import java.util.HashSet;
import java.util.Set;

public class ReportSetConverter {
    static public Report ReportSetToReport(ReportSet rs) {
        Report r = new Report();
        
        if (rs != null) {
            StringTokenizer st = new StringTokenizer(rs.getTaskString(), "\n");

            for (int hour = 0; hour < 24 && st.hasMoreTokens(); hour++)
                r.setTask(hour, st.nextToken());
        }
        
        return r;
    }
    
    static public ReportSet ReportToReportSet(Report r, String id) {
        ReportSet rs = new ReportSet();
        
        rs.setId(id);
        
        /*Tokenization*/
        String tokenized = "";
        for (int i = 0; i < 24; i++)
            tokenized += r.getTask(i) + "\n";
        rs.setTaskString(tokenized);
        
        return rs;
    }
}
