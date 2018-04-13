<%@ page import="java.util.Map" %>
<%@ page import="measures.KnowledgeBase" %>
<%@ page import="measures.Result" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Inconsistency Measure Result</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
  </head>
  <body>
    <h1>Measure Result</h1>
    <a href="/">Go Back</a>
    <%
      Map<KnowledgeBase, Result> resultMap = (Map<KnowledgeBase, Result>) request.getAttribute("resultMap");
      for(Map.Entry<KnowledgeBase, Result> result : resultMap.entrySet()) {
    %>
    <h3>Knowledge Base: <%=result.getKey()%></h3>
    <p>Measure Name: <%=result.getValue().getMeasurename()%></p>
    <p>Measure Value: <%=result.getValue().getMeasurevalue()%></p>

    <%
      }
    %>


  </body>
</html>
