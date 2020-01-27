package dailyreport.report;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import dailyreport.business.ReportSet;
import dailyreport.data.ReportSetDB;
import dailyreport.util.ReportSetConverter;

public class DailyReportServlet extends HttpServlet {
    private boolean ifGotId = false;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {    
        HttpSession session = request.getSession();
        String url = "/dailyreport";
        Report report;

        String action = request.getParameter("action");

        /*Id Insertion*/
        if (action.equals("insertId")) {
             /*Get ReportSet*/
             String id = (String) request.getAttribute("id");

             /*Validate id*/
             if (!(4 <= id.length() && id.length() <= 8)) {
                 request.setAttribute("idInsertionMsg", "Invalid id.");
                 report = null;
                 ifGotId = false;
                 session.setAttribute("ifGotId", ifGotId);
             }
             else {
                request.setAttribute("idInsertionMsg", "");
                ifGotId = true;
                session.setAttribute("ifGodId", ifGotId);
                
                ReportSet rs = ReportSetDB.getReportSetById(id);

                /*Convert ReportSet to Report*/
                report = ReportSetConverter.ReportSetToReport(rs);
                session.setAttribute("report", report);
             }
       }
       
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