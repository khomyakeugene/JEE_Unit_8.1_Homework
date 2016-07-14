package com.company.todo.servlets;

import com.company.todo.servlets.proto.ReturnToRefererPageServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yevhen on 14.07.2016.
 */
public class TaskItemServlet extends ReturnToRefererPageServlet {
    @Override
    protected void responseAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
