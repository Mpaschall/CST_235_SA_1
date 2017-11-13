package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Storage_Bean;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "myServlet", urlPatterns = {"/myServlet"})
public class myServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet myServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet myServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Storage_Bean storageBean = new Storage_Bean();
        
        //gets info from post method in index.html, sets to variables
        int qlty = Integer.parseInt(request.getParameter("quality"));
        int runt = Integer.parseInt(request.getParameter("rt"));
        int rate = 0;
        
        //checks video quality, and sets appropriate transfer rate
        if(qlty == 720){
            storageBean.setTransRate(12);
            rate = 12;
        }
        else if(qlty == 1080){
            storageBean.setTransRate(22);
            rate = 22;
        }
        else{
        }
        
        //sets bean properties
        storageBean.setVideoQuality(qlty);
        storageBean.setRunTime(runt);
        storageBean.setVideoSpace(((runt * rate) / 1024));
        
        //send request for redirect to jsp page
        request.setAttribute("storageBean", storageBean);
        RequestDispatcher r = request.getRequestDispatcher("displayStorage.jsp");
        r.forward(request,response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
