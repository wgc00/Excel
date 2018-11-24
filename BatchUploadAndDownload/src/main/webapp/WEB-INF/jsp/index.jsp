<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="../../js/jquery.js"></script>

</head>
<body>
<div>
    <br>
    <button type="button" class="btn btn-primary|secondary|success|info|warning|danger|link" onclick="importData()">
        导入Excel
    </button>
    <div class="form-group">
        <label for="myFile"></label>
        <a href="/importData">导出数据</a>
        <small id="fileHelpId" class="form-text text-muted"></small>
    </div>
    <div class="alert alert-warning" role="alert">
        <strong>Warning!</strong> Better check yourself, you're <a href="/insert" class="alert-link">导入数据库</a>
    </div>
    >
</div>
<script>
    function importData() {
        <%--//alert();--%>
        location = "/importData";

    }

    function fileData() {
        <%--/*fetch('/insert', function () {--%>
        <%--method: ''--%>
        <%--});*/--%>
        location = "/insert";
    }
</script>
<br>
<br>
<br>
<div style="width: 980px; height: 100%; margin: auto">
    <table class="table">
        <thead>
        <tr>
            <th>员工编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>学历</th>
            <th>月薪</th>
        </tr>
        <c:forEach items="${emp}" var="e">
        <tr>
            <td>${e.number}</td>
            <td>${e.name}</td>
            <td>${e.gender}</td>
            <td>${e.education}</td>
            <td>${e.monthlyPay}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
