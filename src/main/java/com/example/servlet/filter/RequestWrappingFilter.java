package com.example.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

public class RequestWrappingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public String getParameter(String name) {
                String[] param = parameterMap.get(name);
                return param == null ? null : param[0];
            }
            @Override
            public String[] getParameterValues(String name) {
                return parameterMap.get(name);
            }
            @Override
            public Map<String, String[]> getParameterMap() {
                return parameterMap;
            }
        };
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
