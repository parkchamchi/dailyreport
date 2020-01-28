<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dr" uri="/WEB-INF/tlds/dailyreport.tld" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daily Report</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/index.css">
    </head>
    <body>
        <h1>Daily Report</h1>
        
        <!-- ID insertion -->
        <div>
            <p>${idInsertionMsg}</p>
            <form action="" method="POST">
                <input name="action" value="insertId" hidden>
                <label>ID (4-8 letters)</label>
                <input name="id" minlength="4" maxlength="8" required>
                <input type="submit" value="Submit">
            </form>
        </div>
        
        <c:if test="${ifGotId}">
            
            <!-- Input tasks -->
            <form action="" method="POST">
                <dr:hourInputs>
                    <div>
                        <input name="action" value="submit" hidden>
                        <label>${hour}</label>
                        <input name="${hourTask}" value="${hourValue}">
                    </div>
                </dr:hourInputs>
                <input type="submit" value="Submit">    
            </form>

            <!-- Hour range control --> 

            <p>You can set the range of hours</p>
            <form action="" method="POST">
                <input name="action" value="changeHoursRange" hidden>

                <label>Starting from...</label>
                <input name="hourFrom" type="number" min="0" max="23">
                <label>to...</label>
                <input name="hourTo" type="number" min="0" max="23">   

                <input type="submit" value="Submit">
            </form> 
            <p>${changeHourRangeMsg}</p>
            
        </c:if>
           
    </body>
</html>
