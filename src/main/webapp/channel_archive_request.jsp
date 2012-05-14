<%@ page import="dk.statsbiblioteket.mediaplatform.ingest.model.persistence.HibernateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="dk.statsbiblioteket.mediaplatform.ingest.model.ChannelArchiveRequest" %>
<%@ page import="dk.statsbiblioteket.mediaplatform.ingest.model.persistence.ChannelArchiveRequestDAO" %>
<%@ page import="dk.statsbiblioteket.mediaplatform.ingest.model.WeekdayCoverage" %>
<%@ page import="java.util.Date" %>
<%@ page import="dk.statsbiblioteket.mediaplatform.ingest.model.service.YouSeeChannelMappingServiceIF" %>
<%@ page import="dk.statsbiblioteket.mediaplatform.ingest.model.service.YouSeeChannelMappingService" %>
<%@ page import="dk.statsbiblioteket.mediaplatform.ingest.model.YouSeeChannelMapping" %>
<%@ page import="dk.statsbiblioteket.mediaplatform.ingest.model.WeekdayCoverageTime" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="static dk.statsbiblioteket.mediaplatform.ingest.channelarchivingrequester.web.ChannelArchiveRequestCRUDServlet.*" %>
<%
    List<ChannelArchiveRequest> requests = (new ChannelArchiveRequestDAO()).getAllRequests();
    YouSeeChannelMappingServiceIF ucService = new YouSeeChannelMappingService();
%>
<table>
    <tr>
        <th>Channel</th><th>Coverage</th><th>Start Time</th><th>End Time</th><th>From</th><th>To</th><th><!--Submit Buttons in this column--></th>
    </tr>
<%
    for (ChannelArchiveRequest caRequest: requests) {
        Long id = caRequest.getId();
        String sbChannel = caRequest.getsBChannelId();
        WeekdayCoverage coverage = caRequest.getWeekdayCoverage();
        Date fromDate = caRequest.getFromDate();
        Date toDate = caRequest.getToDate();
        Date fromTime = caRequest.getFromTime();
        Date toTime = caRequest.getToTime();
        WeekdayCoverageTime toTimeWct = new WeekdayCoverageTime(toTime);
        WeekdayCoverageTime fromTimeWct = new WeekdayCoverageTime(fromTime);

%>  <!--Each row is an html form -->
    <form action="./ChannelArchiveRequestCRUDServlet" method="post" >
    <tr>
        <td><input value="<%=caRequest.getsBChannelId()%>"/></td>
        <td><%=WeekdayCoverage.getHtmlSelect(COVERAGE, null, null, coverage)%></td>
        <td><input value="<%=fromTimeWct.getHours()%>" size="2" maxlength="2"/>:<input value="<%=fromTimeWct.getMinutes()%>" size="2" maxlength="2"/></td>
        <td><input value="<%=toTimeWct.getHours()%>" size="2" maxlength="2"/>:<input value="<%=toTimeWct.getMinutes()%>" size="2" maxlength="2"/></td>
        <td><input value="<%=JAVA_DATE_FORMAT.format(fromDate)%>" size="10" maxlength="10" /></td>
        <td><input value="<%=JAVA_DATE_FORMAT.format(toDate)%>"  size="10" maxlength="10" /></td>
        <td><button type="submit" name="<%=SUBMIT_ACTION%>" value="<%=UPDATE%>">Update</button>
            <button type="submit" name="<%=SUBMIT_ACTION%>" value="<%=DELETE%>">Delete</button></td>
    </tr>
    </form>
<%
    }
%>
    <form action="./ChannelArchiveRequestCRUDServlet" method="post" >
        <td><input name="<%=CHANNEL%>" value=""/></td>
        <td> <%=WeekdayCoverage.getHtmlSelect(COVERAGE, null, null, null)%></td>
        <td><input name="<%=FROM_TIME_HOURS%>" value="00" size="2" maxlength="2"/>:<input name="<%=FROM_TIME_MINUTES%>" value="00" size="2" maxlength="2"/></td>
        <td><input name="<%=TO_TIME_HOURS%>" value="00" size="2" maxlength="2"/>:<input name="<%=TO_TIME_MINUTES%>" value="00" size="2" maxlength="2"/></td>
        <td><input id="start_create" name="<%=FROM_DATE%>" value=""  size="10" maxlength="10" /></td>
        <td><input id="end_create" name="<%=TO_DATE%>" value=""  size="10" maxlength="10" /></td>
        <td><button type="submit" name="<%=SUBMIT_ACTION%>" value="<%=CREATE%>">Create</button></td>
    </form>
</table>

