package com.company.todo.servlets.proto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yevhen on 14.07.2016.
 */
public abstract class CallerPageReturnServlet extends ServletProto {
    private static final String REFERER_HEADER_NAME = "referer";

    protected abstract void responseAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    private String getRequestReferer(HttpServletRequest request) {
        return request.getHeader(REFERER_HEADER_NAME);
    }

    private void returnToRefererPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(getRequestReferer(request));
    }

    @Override
    protected void getResponse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        responseAction(request, response);

        returnToRefererPage(request, response);
    }
}
