package ru.mitroshkin.servlets;

import ru.mitroshkin.controller.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 17.09.2016.
 */
public class SearchServlet extends HttpServlet {
    private static final String SEARCH = "/view/Search.jsp";

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    private static String name;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        forwardTo(req,resp,SEARCH);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("searchClient")!=null) {
            name = req.getParameter("searchName");
            req.setAttribute("clients", CLIENT_CACHE.findClientByName(name));
        }
        forwardTo(req,resp,SEARCH);
    }

    /**
     * Перенаправление на указанный адрес
     * @param req
     * @param resp
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        CLIENT_CACHE.close();
        super.destroy();
    }
}
