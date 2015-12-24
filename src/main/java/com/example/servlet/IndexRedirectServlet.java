package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * このサーブレットは、web.xmlでwelcome-fileに設定されています。
 * リクエストを受け取ると、すぐにHelloResourceのindex()へリダイレクトします。
 * @see com.example.rest.resource.HelloResource
 * Created by tada on 2015/12/24.
 */
@WebServlet("/index")
public class IndexRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("./api/hello/index");
    }
}
