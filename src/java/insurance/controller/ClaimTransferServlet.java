/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.controller;

import insurance.model.Hospital;
import insurance.pojo.ClaimDetailsPojo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClaimTransferServlet", urlPatterns = {"/ClaimTransferServlet"})
public class ClaimTransferServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            String user=request.getParameter("name");
             ArrayList<ClaimDetailsPojo> cdp=Hospital.getClaimDetailsByUser(user);
             ArrayList<ClaimDetailsPojo> faulty=Hospital.getClaimDetailsByUserFault(user);
             if(!cdp.isEmpty())
                 request.setAttribute("claimDetails", cdp);
             else
                 request.setAttribute("claimDetails", null);
                 
             if(!faulty.isEmpty())
             {
                 System.out.println("Backend Cause:"+faulty.get(0).getCause());
                 request.setAttribute("faultyList", faulty);
             }
             else
                 request.setAttribute("faultyList", null);
            
            RequestDispatcher rd=request.getRequestDispatcher("userProfile.jsp");
            rd.include(request, response);
        }
        catch(NumberFormatException nfe)
        {
            System.out.println("Invalid Format of Username");
            nfe.printStackTrace();
        }
        
        catch(SQLException sqle)
        {
            System.out.println("Invalid Format of Username");
            sqle.printStackTrace();
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
