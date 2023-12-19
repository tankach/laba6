<%@page import="java.util.List"%>
<%@page import="sumdu.edu.ua.webstudent.Student"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student</title>
    <style>
        body {
            background-color: grey;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        #page {
            width: 800px;
            margin: auto;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        h1 {
            text-align: center;
        }
        form {
            width: 100%;
            max-width: 400px;
            margin: 20px auto;
        }
        table {
            width: 100%;
            margin-top: 10px;
            border-collapse: collapse;
        }
        table,
        th,
        td {
            border: 1px solid black;
        }
        th,
        td {
            padding: 10px;
            text-align: left;
        }
        input[type=text],
        input[type=email] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            margin-top: 5px;
            margin-bottom: 10px;
        }
        input[type=submit] {
            width: 100%;
            background-color: #4caf50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type=submit]:hover {
            background-color: #45a049;
        }
        #content{
                background-color: white;
                width: 400px;
                margin: 20px auto;
            }
       </style>
</head>
<body>
    <div id="page">
        <h1>Student Form</h1>
        <form method="post" action="NewServlet">
            <table>
    <tbody>
        <tr>
            <td><label for="name">Name</td>
            <td><input id="name" type="text" name="name"></td>
        </tr>
        <tr>
            <td><label for="surname">Surname</td>
            <td><input id="surname" type="text" name="surname"></td>
        </tr>
        <tr>
            <td><label for="email">Email</td>
            <td><input id="email" type="email" name="email"></td>
        </tr>
        <tr>
            <td><label for="group">Group</td>
            <td><input id="group" type="text" name="group"></td>
        </tr>
        <tr>
            <td><label for="faculty">Faculty</td>
            <td><input id="faculty" type="text" name="faculty"></td>
        </tr>
    </tbody>
    </table>
            <input type="submit" name="send" value="Send">
        </form>
    <c:if test="${students.size() > 2}">
    <form action="calculateStats">
        <input type="submit" name="send2" value="Statistics">
    </form>
    </c:if>

    <div id="content">
        <c:if test="${not empty students}">
            
                <table class="list">
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Email</th>
                        <th>Group</th>
                        <th>Faculty</th>
                    </tr>
                    <c:forEach items="${students}" var="s">
                        <tr>
                            <td><c:out value="${s.name}" /></td>
                            <td><c:out value="${s.surname}" /></td>
                            <td><c:out value="${s.email}" /></td>
                            <td><c:out value="${s.group}" /></td>
                            <td><c:out value="${s.faculty}" /></td>
                            <td><a href="GradesServlet?id=${s.id}">View Grades</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</body>
</html>
