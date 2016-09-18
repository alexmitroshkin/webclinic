package ru.mitroshkin.servlets;

import ru.mitroshkin.controller.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    /*Константы пути к jsp Страницам*/
    private static String ADD_CLIENT = "/view/AddClient.jsp";
    private static String EDIT_CLIENT = "/view/EditClient.jsp";
    private static String INDEX = "/view/index.jsp";

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        if (req.getParameter("addClient")!=null) {
            addClient(req,resp);
        }else {
            defaultAction(req, resp);
        }
        //req.setAttribute("clients", CLIENT_CACHE.listClients());
    }

    /**
     * Обработка кнопки добавления клиента
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardTo(req, resp, ADD_CLIENT);
    }
    /**
     * Стандартное действие при неполучении параметра выводит index.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void defaultAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", CLIENT_CACHE.listClients());
        forwardTo(req,resp,INDEX);
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
