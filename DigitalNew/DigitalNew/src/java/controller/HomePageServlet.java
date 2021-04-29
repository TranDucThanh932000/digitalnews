/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.DBContext;
import dao.DigitalDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import model.Digital;

/**
 *
 * @author admin2
 */
public class HomePageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomePageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomePageServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        try {
            DBContext db = new DBContext();
            DigitalDAO dao = new DigitalDAO();
            String pathImage = "";
            //get path image
            try {
                pathImage = db.getImagePath();
                request.setAttribute("pathImage", pathImage);
            } catch (Exception e) {
                request.setAttribute("error", "No image transmission exists");
            }

            Digital digital = null;
            //begin: get digital by id
            if (request.getParameter("id") == null) {
                digital = dao.getNewDigital();
                if (digital == null) {
                    request.setAttribute("error", "No posts exist");
                }
            } else {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    digital = dao.getNewByID(id);
                    if (digital == null) {
                        request.setAttribute("error", "The post does not exist!");
                    }  
                } catch (Exception e) {
                    request.setAttribute("error", "Invalid index!");
                }
            }
            request.setAttribute("digital", digital);
            //end: get digital by id
            
            //begin: get most recent short description new and get top 5 new , order by time publish desc
            String mostRecentShortNew = dao.getMostRecentNew();

            if (mostRecentShortNew.equals("")) {
                request.setAttribute("error", "No posts exist!");
            } else {
                List<Digital> listTopFive = dao.getTop5();
                request.setAttribute("topfive", listTopFive);
                request.setAttribute("mostRecentShortNew", mostRecentShortNew);
            }
            //end : get most recent short description new and get top 5 new , order by time publish desc

            request.getRequestDispatcher("main.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error occuring!");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
