package dailyreport.tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.util.*;
import java.io.IOException;

import dailyreport.report.Report;

public class HourInputs extends BodyTagSupport {
    private int hour;
    private Report report;
    
    private int fromHour, toHour;
    
    @Override
    public int doStartTag() {
        report = (Report) pageContext.findAttribute("report");
        
        if (report == null)
            return SKIP_BODY;
        else
            return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public void doInitBody() throws JspException {
        fromHour = report.getFromHour();
        toHour = report.getToHour();
        
        hour = fromHour;
        
        setHourAttributes();
    }
    
    @Override
    public int doAfterBody() throws JspException {
        try {
            if (hour <= toHour) {
                setHourAttributes();
                return EVAL_BODY_AGAIN;
            }
            else {
                JspWriter out = bodyContent.getEnclosingWriter();
                bodyContent.writeOut(out);
                return SKIP_BODY;
            }
        }
        catch (IOException ioe) {
            System.err.println("error in doAfterBody " + ioe.getMessage());
            return SKIP_BODY;
        }
    }
    
    private void setHourAttributes() {
        String hourString = String.format("%02d:00", hour);
        
        pageContext.setAttribute("hour", hourString);
        pageContext.setAttribute("hourValue", report.getTask(hour));
        pageContext.setAttribute("hourTask", "task" + hour);
        hour++;
    }
}