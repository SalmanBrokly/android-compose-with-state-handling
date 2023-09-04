
package com.ebank_indore;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import jdk.internal.net.http.common.Log;


public class EBank_Servlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

          
         Connection con;
        PrintWriter out = response.getWriter(); 
        out.write("hello");
        try { 
           out.write("hello"+request.getParameter("account_number"));
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_database","root","");
           
           PreparedStatement  stm = con.prepareStatement("insert into user values(?,?,?,?)");
          
            
            int randomNumber = new Random().nextInt(9000) + 1000;


           
           stm.setInt(1, randomNumber);
           stm.setString(2, request.getParameter("user"));
           stm.setString(3, request.getParameter("email"));
           stm.setString(4, request.getParameter("add"));
           stm.executeUpdate();
           out.write("Date Saved");
           RequestDispatcher rd = request.getRequestDispatcher("Deposite_Account.jsp");
           rd.forward(request, response);
                stm.close();
                con.close();
        }catch(ClassNotFoundException | SQLException e){
                    Log.logError(e.getCause());
        }

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
