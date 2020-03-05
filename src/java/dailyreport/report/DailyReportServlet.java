package dailyreport.report;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import dailyreport.business.ReportSet;
import dailyreport.data.ReportSetDB;
import dailyreport.util.ReportSetConverter;

public class DailyReportServlet extends HttpServlet {
    //private boolean ifGotId = false;
    private String id;
    private Report report;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {    
        HttpSession session = request.getSession();
        String url = "/dailyreport/index.jsp";
        //request.setCharacterEncoding("euc-kr");

        String action = request.getParameter("action");

        /*Id Insertion*/
        if (action != null && action.equals("insertId")) {
             /*Get ReportSet*/
             id = request.getParameter("id");

             /*Validate id*/
             if (!(4 <= id.length() && id.length() <= 8)) {
                 session.setAttribute("idInsertionMsg", "Invalid id.");
                 report = null;
                 //ifGotId = false;
                 id = null; //set id to null
             }
             else {
                session.setAttribute("idInsertionMsg", "");
                //ifGotId = true;

                ReportSet rs = ReportSetDB.getReportSetById(id);
                
                /*If ReportSet is null, create a Report*/
                if (rs == null)
                    report = new Report();
                /*else, convert ReportSet to Report*/
                else
                    report = ReportSetConverter.ReportSetToReport(rs);
             }
        }
        else if (action != null && action.equals("submit")) {
            for (int hour = 0; hour < 24; hour++) {
                String hourTask = request.getParameter("task" + hour);
                
                report.setTask(hour, hourTask);
            }
            
            ReportSet rs = ReportSetConverter.ReportToReportSet(report, id);
            ReportSetDB.update(rs);
        }
        else if (action != null && action.equals("changeHoursRange")) {
            Integer fromHour = null, toHour = null;
            
            try {
                fromHour = Integer.valueOf(request.getParameter("fromHour"));
                toHour = Integer.valueOf(request.getParameter("toHour"));
            }
            catch (NumberFormatException exc) {
                session.setAttribute("changeHourRangeMsg", "Invalid input");
            }
            
            /*if valid input*/
            if (fromHour != null && toHour != null) {
                if ((0 <= fromHour && fromHour <= 23) && (0 <= toHour && toHour <= 23) &&
                    (fromHour <= toHour)) 
                {
                    report.setFromHour(fromHour);
                    report.setToHour(toHour);
                   
                    session.setAttribute("changeHourRangeMsg", "");
                    
                    ReportSet rs = ReportSetConverter.ReportToReportSet(report, id);
                    ReportSetDB.update(rs); 
                }
                else {
                    session.setAttribute("changeHourRangeMsg", "Invalid input");
                }
            }
            
        }
        
        //session.setAttribute("ifGotId", ifGotId);
        session.setAttribute("id", id);
        session.setAttribute("report", report);
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