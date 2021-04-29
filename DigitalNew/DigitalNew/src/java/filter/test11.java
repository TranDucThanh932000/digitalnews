/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author admin2
 */
public class test11 implements Filter{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse respond= (HttpServletResponse) sr1;
        String url = request.getServletPath();
//        String contextPath = request.getContextPath();
//        int count = contextPath.length();
        if(url.contains(".jsp")){
            respond.sendRedirect("HomePageServlet");
        }
        //tien xu ly
        fc.doFilter(sr, sr1);
        //hau xu ly
    }
    
}
