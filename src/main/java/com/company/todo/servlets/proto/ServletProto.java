package com.company.todo.servlets.proto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yevhen on 14.07.2016.
 */
public abstract class ServletProto extends HttpServlet {
    protected abstract void getResponse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getResponse(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getResponse(request, response);
    }
}
