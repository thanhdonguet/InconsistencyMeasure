<%--
  Created by IntelliJ IDEA.
  User: Nguyen Thanh Dong
  Date: 4/11/2018
  Time: 11:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Inconsistency Measure</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#kbInput").hide();
            $("#numInput").hide();
            $("#normInput").hide();


            $('#code').change(function() {
                var ss = $(this).val();
                if (["7", "8"].indexOf(ss) != -1) {
                    $("#kbInput").show();
                    $("#normInput").show();
                    $("#numInput").hide();
                }
                else if (["9"].indexOf(ss) != -1){
                    $("#numInput").show();
                    $("#kbInput").hide();
                    $("#normInput").hide();

                }
                else {
                    $("#kbInput").hide();
                    $("#numInput").hide();
                    $("#normInput").hide();
                }
            });
        })
    </script>

  </head>
  <body>
    <h1>Measure Inconsistency of Probabilistic Knowledge Base</h1>
    <form action="/Servlet" method="post" enctype="multipart/form-data">
      Upload Knowledge Base:  <input type="file" name="uploadedFile" />
      <button type="submit">Calculate</button>

    <select id="code" name="code">
      <option value="1">Drastic Inconsistency Measure</option>
      <option value="2">MI Inconsistency Measure</option>
      <option value="3">SMI-c Inconsistency Measure</option>
      <option value="4">l-Inconsistency Measure</option>
      <option value="5">X-Inconsistency Measure</option>
      <option value="6">μ-Inconsistency Measure</option>
      <option value="7">DM Inconsistency Measure</option>
      <option value="8">SUM Inconsistency Measure</option>
      <option value="9">Probabilistic Shapley Inconsisten Value</option>
      <option value="10">SV-Inconsistency Measure</option>
      <option value="11">Unnormalized Inconsistency Measure</option>
    </select>
    <div id="kbInput">
        <label>Input Knowledge Base:</label>
        <input type="text" name="kbInput"/>
    </div>

    <div id="normInput">
        <label>Input norm</label>
        <input type="text" name="normInput"/>
    </div>

    <div id="numInput">
        <label>Input Constraint's order:</label>
        <input type="text" name="numInput"/>
    </div>
    </form>

    <%
      java.util.Date date = new java.util.Date();
    %>

    <h2>
      Now is
      <%=date.toString()%>
  </h2>


  </body>
</html>
