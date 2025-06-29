<!-- export-excel.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Report Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h3 class="pb-3 pt-3">Report Application</h3>
    
    <!-- Search Form -->
    <form:form action="search" modelAttribute="search" method="post">
        <table>
            <tr>
                <td>Plan Name:</td>
                <td>
                    <form:select path="planName">
                        <form:option value="">-select-</form:option>
                        <form:options items="${names}" />
                    </form:select>
                </td>
                <td>Plan Status:</td>
                <td>
                    <form:select path="planStatus">
                        <form:option value="">-select-</form:option>
                        <form:options items="${status}" />
                    </form:select>
                </td>
                <td>Gender:</td>
                <td>
                    <form:select path="gender">
                        <form:option value="">-select-</form:option>
                        <form:option value="Male">Male</form:option>
                        <form:option value="Fe-Male">Fe-Male</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>Start Date:</td>
                <td><form:input type="date" path="startDate" /></td>
                <td>End Date:</td>
                <td><form:input type="date" path="endDate" /></td>
            </tr>
            <tr>
                <td><a href="/" class="btn btn-secondary">Reset</a></td>
                <td><input type="submit" value="Search" class="btn btn-primary" /></td>
            </tr>
        </table>
    </form:form>
    
    <hr/>
    
    <!-- Display Results Table -->
    <table class="table table-striped table-hover"> 
        <thead>
            <tr>
                <th>Id</th>
                <th>Holder Name</th>
                <th>Gender</th>
                <th>Plan Name</th>
                <th>Plan Status</th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${plans}" var="plan" varStatus="index">
                <tr>
                    <td>${index.count}</td>
                    <td>${plan.citizename}</td>
                    <td>${plan.gender}</td>
                    <td>${plan.planName}</td>
                    <td>${plan.planStatus}</td>
                    <td>${plan.planStartDate}</td>
                    <td>${plan.planEndDate}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty plans}">
                <tr><td colspan="8">No records found</td></tr>
            </c:if>
        </tbody>
    </table>

    <p class="text-success">${msg}</p>

    <!-- 🔁 Export Buttons Section -->
    <!-- ✅ Updated Excel export to use POST method -->
    <!-- ✅ Correct Export Excel form with hidden fields -->
<form:form action="excel" modelAttribute="search" method="post">
    <form:hidden path="planName"/>
    <form:hidden path="planStatus"/>
    <form:hidden path="gender"/>
    <form:hidden path="startDate"/>
    <form:hidden path="endDate"/>

    <input type="submit" value="Export Excel" class="btn btn-success" />
</form:form>

    <!-- ✅ PDF can stay as GET, if your controller supports it -->
    <a href="pdf" class="btn btn-danger mt-2">Export PDF</a>

    <!-- ❌ Removed the old 'Excel' GET link that caused error -->
    <!-- <a href="excel"> Excel</a> -->

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
