package com.example.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.beanvalidation.MvcBeanValidationFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import javax.ws.rs.ApplicationPath;

/**
 * Created by tada on 2015/12/24.
 */
@ApplicationPath("api")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        // Jersey MVCの登録、ビューとしてJSPを使う
        register(JspMvcFeature.class);
        // Jersey MVCにおけるBean Validationを有効化する
        register(MvcBeanValidationFeature.class);
        // JSPファイルを保存するフォルダを指定する
        property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/views/");
        // com.example以下の全パッケージを登録対象にする
        packages(true, this.getClass().getPackage().getName());
    }
}
