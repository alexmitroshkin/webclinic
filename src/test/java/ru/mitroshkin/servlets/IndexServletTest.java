package ru.mitroshkin.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.mitroshkin.controller.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

public class IndexServletTest extends Mockito{
//    final ClientCache CLIENT_CACHE = ClientCache.getInstance();

    @Test
    public void testIndexPage() throws ServletException, IOException{
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        when(request.getParameter("addClient"));
//        assertTrue(!CLIENT_CACHE.listClients().isEmpty());
    }

}