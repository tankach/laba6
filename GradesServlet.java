/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sumdu.edu.ua.webstudent;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tanka
 */
@WebServlet(name = "GradesServlet", urlPatterns = {"/GradesServlet"})
public class GradesServlet extends HttpServlet {

  private static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter pw=null;
            try{ 
                    pw =response.getWriter(); 
                    Class.forName("com.mysql.jdbc.Driver"); 
                } 
                catch(ClassNotFoundException ex){ 
                    ex.printStackTrace(pw); 
                    pw.print(ex.getMessage()); 
             } 
            int studentId = Integer.parseInt(request.getParameter("id"));
            List<Student> students = getStudentFromDatabase(studentId);
            List<studentMark> marks = getstudentMarkFromDatabase(studentId);
            request.setAttribute("students", students);
            request.setAttribute("marks", marks);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Mark.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(GradesServlet.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    private List<studentMark> getstudentMarkFromDatabase(int studentId) throws SQLException, IOException {
        List<studentMark> marks = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            
            String query = "SELECT * FROM `dis` WHERE stud_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) { 

                        studentMark mark = new studentMark(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)); 
                        marks.add(mark);

                    } 
        }
        return marks;
    }
    
    private List<Student> getStudentFromDatabase(int studentId) throws SQLException, IOException {
        List<Student> students = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            
            String query = "SELECT * FROM `students` WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) { 

                        Student student = new Student(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)); 
                        students.add(student);

                    } 
        }
        return students;
    }
}
