

package com.ebank_indore;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Withdrwal_Servlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
            /* TODO output your page here. You may use following sample code. */
             int acc_number=0;

        Connection con;
        PrintWriter out = response.getWriter();
         out.write("hello");
           int amout = Integer.parseInt(request.getParameter("amount"));

        try { 
           out.write("hello"+request.getParameter("account_number"));
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_database","root","");
        
         PreparedStatement  stm = con.prepareStatement("select * from user where account_no = ?");
                    stm.setInt(1, Integer.parseInt(request.getParameter("account_number")));
                    ResultSet rs =stm.executeQuery();
                    
                    if(rs.isBeforeFirst()){

                        
                        while(rs.next()){
                        //out.write(""+rs.getInt("account_no"));
                        acc_number = rs.getInt("account_no");
                         
                        if(acc_number==Integer.parseInt(request.getParameter("account_number"))){
                        
                        PreparedStatement  stm2 = con.prepareStatement("select * from deposite_account where account_no = ?");
                    stm2.setInt(1, Integer.parseInt(request.getParameter("account_number")));
                    ResultSet rs2 =stm2.executeQuery();
               
                
                    if(rs2.isBeforeFirst()){
                         while(rs2.next()){
                      amout= rs2.getInt("amount")-amout;
                        }
//                            out.write("Hello "+rs.getString(rs.findColumn("email")));
                        PreparedStatement  stm3 = con.prepareStatement("UPDATE deposite_account SET amount=? where account_no=?");
                         
                        stm3.setInt(1, amout);
                        stm3.setInt(2, acc_number);
                         stm3.executeUpdate();
                         out.write("Date Saved");
      
//           RequestDispatcher rd = request.getRequestDispatcher("Deposite_Account.jsp");
//           rd.forward(request, response);
                    }else{
                        PreparedStatement  stm4 = con.prepareStatement("insert into deposite_account values(?,?)");
                         stm4.setInt(1, acc_number);
                         stm4.setInt(2, amout);
                         stm4.executeUpdate();
                         out.write("Date Saved");
                    }
                        
                           
                         
                    }else {
                         out.write("Record not found");
      
                    }
                    }
                    }
              
                    
                     con.close();
        }catch(Exception e){
                    out.write(e.getMessage());
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
