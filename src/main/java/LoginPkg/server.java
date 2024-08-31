
package LoginPkg;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/server")
public class server extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database Credentials
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/mydb1";
    private static final String username = "user02";
    private static final String Password = System.getenv("MYSQL_PASSWORD");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	System.out.println(Password);
        String action = request.getParameter("action");
        if ("signup".equals(action)) {
            handleSignUp(request, response);
        } else {
            handleLogin(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        if (Password == null || Password.isEmpty()) {
            System.err.println("Environment variable MYSQL_PASSWORD is not set or empty.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, username, Password);

            System.out.println("Connection successful!");

            String model = "SELECT * FROM user1 WHERE userid = ? AND password = ?";
            ps = conn.prepareStatement(model);
            ps.setString(1, userId);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                response.sendRedirect("Welcome.html");
            } else {
                out.print("<h1>Please enter correct userid and password</h1>");
                out.print("<h1>Login Failed!</h1>");
            }

            conn.close();
            ps.close();
            rs.close();
        } catch (ClassNotFoundException e) {
            out.print("Login failed because of server exception!" + "\n" + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            out.print("Login failed because of server exception!" + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            out.print("Passwords do not match!");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, username, Password);

            String sql = "INSERT INTO user1 (userid, password) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, password);

            int result = ps.executeUpdate();

            if (result > 0) {
                out.print("<h1>Registration Successful!</h1>");
                response.sendRedirect("login.html");
            } else {
                out.print("<h1>Registration Failed!</h1>");
            }

            conn.close();
            ps.close();
        } catch (ClassNotFoundException e) {
            out.print("Registration failed because of server exception!" + "\n" + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            out.print("Registration failed because of server exception!" + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


