package ru.mitroshkin.servlets;

import ru.mitroshkin.controller.ClientCache;
import ru.mitroshkin.model.Client;
import ru.mitroshkin.model.pet.Cat;
import ru.mitroshkin.model.pet.Dog;
import ru.mitroshkin.model.pet.Pet;
import ru.mitroshkin.model.pet.Type;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alex on 23.08.2016.
 */
public class AddClientServlet extends HttpServlet {
    /* Константы пути к файлам jsp */
    private static String ADD_CLIENT = "/view/AddClient.jsp";
    private static String INDEX = "/view/index.jsp";

    private final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        forwardTo(req,resp,ADD_CLIENT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); //Для корректной работы с кирилицей
        if (req.getParameter("addNewClient")!=null) {
            addClient(req, resp);
        }
    }

    /**
     * Добавление клиента в клинику и перенаправление на страницу редактирования для возможности добавить животных
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void addClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        int petType = Integer.parseInt(req.getParameter("petType"));
        String petName = req.getParameter("petName");
        int petAge = Integer.parseInt(req.getParameter("petAge"));

        Client client;
        Pet pet;

//        switch (petType){
//            case 0 : pet = new Dog(); break;
//            case 1 : pet = new Cat(); break;
//            default: pet = new Dog();
//        }
        pet = new Pet();
        client = new Client();
        client.setFullName(clientName);
        pet.setName(petName);
        pet.setAge(petAge);
        pet.setType(Type.values()[petType]);
        pet.setClient(client);

        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets.add(pet);

        client.setPets(pets);

        CLIENT_CACHE.add(client);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/clinic/"));
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
        super.destroy();
        this.CLIENT_CACHE.close();
    }
}
