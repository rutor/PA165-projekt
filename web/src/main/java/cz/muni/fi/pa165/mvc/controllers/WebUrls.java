package cz.muni.fi.pa165.mvc.controllers;

public class WebUrls {
    public static final String URL_HOME = "/";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String NOT_FOUND = "/404";

    public static final String URL_ROLE = "/role";
    public static final String URL_USER = "/user";

    public static final String Admin = "Admin";
    public static final String User = "User";

    public static final String Authentication = "/auth";

    private WebUrls() {
        throw new AssertionError("This is not the class you are looking for");
    }
}

