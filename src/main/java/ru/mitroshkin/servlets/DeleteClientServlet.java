package ru.mitroshkin.servlets;

import ru.mitroshkin.controller.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 23.08.2016.
 */
public class DeleteClientServlet extends HttpServlet {
    private static final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        CLIENT_CACHE.delete(id);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/"));
    }

    @Override
    public void destroy() {
        super.destroy();
        CLIENT_CACHE.close();
    }
}
