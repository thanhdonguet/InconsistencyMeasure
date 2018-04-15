<%@ page import="java.util.Map" %>
<%@ page import="measures.KnowledgeBase" %>
<%@ page import="measures.Result" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <link rel="stylesheet" href=" styles.css">
    <title>Inconsistency Measure Result</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
    <script>
        function goBack() {
            parent.history.back()
        }
    </script>
  </head>
  <body>
    <h1>Measure Result</h1>
    <button onclick="goBack()">Go Back</button>
    <%
      Map<KnowledgeBase, Result> resultMap = (Map<KnowledgeBase, Result>) request.getAttribute("resultMap");
      for(Map.Entry<KnowledgeBase, Result> result : resultMap.entrySet()) {
    %>
    <h2>Knowledge Base: <%=result.getKey()%></h2>
    <p>Measure Name: <%=result.getValue().getMeasurename()%></p>
    <p>Measure Value: <%=result.getValue().getMeasurevalue()%></p>

    <%
      }
    %>


  </body>
</html>
