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
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import model.Digital;

/**
 *
 * @author admin2
 */
public class SearchServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
            DigitalDAO dao = new DigitalDAO();
            String txtSearch = request.getParameter("txtSearch");
            request.setAttribute("txtSearch", txtSearch);
            //begin: get most recent short description
            String mostRecentShortNew = dao.getMostRecentNew();
            if (!mostRecentShortNew.equals("")) {
                request.setAttribute("mostRecentShortNew", mostRecentShortNew);
                //begin: get number new by txtSearch
                int countNewBySearch = dao.countNewBySearching(txtSearch);
                if (countNewBySearch == 0) {
                    request.setAttribute("error", "No posts exist!");
                }
                //end: get number new by txtSearch
                //begin paging
                int index=0;
                if (request.getParameter("index") != null) {
                    try {
                        index = Integer.parseInt(request.getParameter("index"));
                    } catch (Exception e) {
                        request.setAttribute("error", "Invalid index!");
                    }
                }else{
                    index=1;
                }
                request.setAttribute("index", index);
                

                //set maxSize  = 2 
                int size = 2;
                //count number of page
                int numOfPage = countNewBySearch / size;
                if (countNewBySearch % size != 0) {
                    numOfPage++;
                }
                request.setAttribute("numOfPage", numOfPage);
                //get list news by index
                List<Digital> listDigitalBySearch = dao.getByPaging(txtSearch, index, size);
                if (listDigitalBySearch.isEmpty()) {
                    request.setAttribute("error", "No posts exist!");
                } else {
                    request.setAttribute("listDigitalBySearch", listDigitalBySearch);
                }
                //end paging
                //begin: get pathImage 
                DBContext db = new DBContext(); 
                String pathImage = db.getImagePath();
                if (pathImage != null) {
                    request.setAttribute("pathImage", pathImage);
                } else {
                    request.setAttribute("error", "No image transmission exists");
                }
                //end: get pathImage

                //begin: get top 5 
                List<Digital> listTopFive = dao.getTop5();
                if (listTopFive != null) {
                    request.setAttribute("topfive", listTopFive);
                }
                //end: get top 5
            }else{
                request.setAttribute("error", "No posts yet");
            }
            //end: get most recent short description

            request.getRequestDispatcher("search.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error is occurring...");
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
