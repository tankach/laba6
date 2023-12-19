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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{ 
        HttpSession session = request.getSession();
       
        PrintWriter pw=null;
        try{ 
                pw =response.getWriter(); 
                Class.forName("com.mysql.jdbc.Driver"); 
            } 
            catch(ClassNotFoundException ex){ 
                ex.printStackTrace(pw); 
                pw.print(ex.getMessage()); 
         } 
        String sqlQuery = "INSERT INTO `students`(`name`, `surname`, `email`, `group_name`, `faculty`) VALUES (?, ?, ?, ?, ?)";
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        con.setAutoCommit(true);
        
         if (request.getParameter("name") != null && request.getParameter("surname") != null) { 
                PreparedStatement ps = con.prepareStatement(sqlQuery); 
                    ps.setString(1, request.getParameter("name"));
                    ps.setString(2, request.getParameter("surname"));
                    ps.setString(3, request.getParameter("email"));
                    ps.setString(4, request.getParameter("group"));
                    ps.setString(5, request.getParameter("faculty"));
                    ps.executeUpdate();
                 
            } 
            Statement s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM `students`");
            List<Student> students = new LinkedList<Student>(); 
                    while (rs.next()) { 
                        /*System.out.println(": " + rs.getString(4));*/
                        Student student = new Student(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)); 
                        students.add(student);
                        session.setAttribute("students",students); 
                    } 
            response.sendRedirect("/Student.jsp"); 
            }catch(SQLException ex){ 
                ex.printStackTrace();
                Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null,ex);
        }
         
        } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
