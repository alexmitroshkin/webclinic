package ru.mitroshkin.servlets;

import ru.mitroshkin.controller.ClientCache;
import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Pet;
import ru.mitroshkin.model.pet.Type;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 23.08.2016.
 */
public class EditClientServlet extends HttpServlet {
    private static final String EDIT_CLIENT = "/view/EditClient.jsp";

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    private static int id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        if (req.getParameter("id")!=null) {
            id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("client", CLIENT_CACHE.getClientById(id));
        }
        forwardTo(req,resp,EDIT_CLIENT);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        if (req.getParameter("addNewPet")!=null){
            addPet(req,resp, EDIT_CLIENT);
        }else if(req.getParameter("editClient")!=null){
            editClient(req,resp);
        }
    }

    private void editClient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = CLIENT_CACHE.getClientById(id);
        String fullName = req.getParameter("clientName");
        client.setFullName(fullName);
        CLIENT_CACHE.editClient(client);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/edit/?id="+id));
    }

    private void addPet(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {
        int petType = Integer.parseInt(req.getParameter("petType"));
        String petName = req.getParameter("petName");
        int petAge = Integer.parseInt(req.getParameter("petAge"));

        Pet pet;
        pet = new Pet();
        Client client = CLIENT_CACHE.getClientById(id);
        pet.setName(petName);
        pet.setType(Type.values()[petType]);
        pet.setAge(petAge);
        pet.setClient(client);
        client.getPets().add(pet);
        CLIENT_CACHE.addPetToClient(client,pet);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/edit/?id="+id));
    }

    /**
     * Перенаправление на указанный адрес
     * @param req Запрос
     * @param resp Ответ
     * @param path Адрес
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
