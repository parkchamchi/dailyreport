package dailyreport.report;

import java.util.Date;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import dailyreport.business.ReportSet;
import dailyreport.data.ReportSetDB;
import dailyreport.util.ReportSetConverter;

public class DailyReportServlet extends HttpServlet {
    private boolean ifGotId = false;
    private String id;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {    
        HttpSession session = request.getSession();
        String url = "/dailyreport/index.jsp";
        Report report;
        Date date;

        String action = request.getParameter("action");

        /*Id Insertion*/
        if (action != null && action.equals("insertId")) {
             /*Get ReportSet*/
             id = request.getParameter("id");

             /*Validate id*/
             if (!(4 <= id.length() && id.length() <= 8)) {
                 request.setAttribute("idInsertionMsg", "Invalid id.");
                 report = null;
                 ifGotId = false;
                 
             }
             else {
                request.setAttribute("idInsertionMsg", "");
                ifGotId = true;

                ReportSet rs = ReportSetDB.getReportSetById(id);

                /*Convert ReportSet to Report*/
                date = rs.getDate();
                report = ReportSetConverter.ReportSetToReport(rs);
                session.setAttribute("report", report);
             }
        }
        else if (action != null && action.equals("submit")) {
            for (int hour = 0; hour < 24; hour++) {
                String hourTask = request.getParameter("task" + hour);
                
                if (hourTask != null && !hourTask.equals(""))
                    report.setTask(hour, hourTask); //report should be class var.
            }
            
            ReportSet rs = ReportSetConverter.ReportToReportSet(report, id);
            ReportSet.update(rs);
        }
        
        session.setAttribute("ifGotId", ifGotId);
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }    
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }    
}