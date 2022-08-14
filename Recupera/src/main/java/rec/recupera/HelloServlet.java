package rec.recupera;

import java.io.*;
import rec.recupera.Dao;
import rec.recupera.Bean;
//import rec.recupera.Service;
//import rec.recupera.ResultAction;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "helloServlet",
        urlPatterns = {
                "/gets",
                "/add",
                "/create",
                "/save",
                "/get",
                "/delete"
        })

public class HelloServlet extends HttpServlet {
    String action;
    String urlRedirect = "/gets";
    //Service serv = new Service();
    Dao ok = new Dao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        action = request.getServletPath();
        switch (action) {
            case "/gets":
                List<Bean> personas = ok.findAll();
                System.out.println(personas.size());
                request.setAttribute("personas", personas);
                urlRedirect = "/consulta.jsp";
                break;
            case "/create":
                urlRedirect = "/create.jsp";
                break;
            case "/get":
                String id = request.getParameter("id");
                id = (id == null) ? "0" : id;
                try {
                    Bean persona = ok.findOne(Long.parseLong(id));
                    request.setAttribute("persona", persona);
                    urlRedirect = "/update.jsp";
                } catch (Exception e) {
                    urlRedirect = "/gets";
                }
                break;
            default:
                request.setAttribute("personas", ok.findAll());
                urlRedirect = "/gets";
                break;
        }
        request.getRequestDispatcher(urlRedirect).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        action = request.getServletPath();
        switch (action) {
            case "/add":
                String nombre = request.getParameter("nombre");
                String subname = request.getParameter("subname");
                String curp = request.getParameter("curp");
                String cumple = request.getParameter("cumple");
                Bean persona = new Bean();
                persona.setNombre(nombre);
                persona.setSubname(subname);
                persona.setCurp(curp);
                persona.setCumple(cumple);
                ok.save(persona);
                urlRedirect = "/gets";
                break;
            case "/save":
                String nombreS = request.getParameter("nombre");
                String subnameS = request.getParameter("subname");
                String curpS = request.getParameter("curp");
                String cumpleS = request.getParameter("cumple");
                String id = request.getParameter("id");
                Bean save = new Bean();
                save.setId(Integer.parseInt((id)));
                save.setNombre(nombreS);
                save.setSubname(subnameS);
                save.setCurp(curpS);
                save.setCumple(cumpleS);
                ok.update(save);
                urlRedirect = "/gets";
                break;
            case "/delete":
                String idP = request.getParameter("id");
                ok.delete(Long.valueOf(idP));
                urlRedirect = "/gets";
                break;
            default:
                urlRedirect = "/gets";
                break;
        }
        response.sendRedirect(request.getContextPath() + urlRedirect);
    }
}