package cz.muni.fi.pa165.mvc.controllers;

public class WebUrls {
    public static final String URL_HOME = "/";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_AUTH = "/auth";
    public static final String NOT_FOUND = "/404";

    public static final String URL_ROLE = "/role";
    public static final String URL_USER = "/user";

    public static final String URL_GENRE = "/genre";
    public static final String URL_SHOW = "/show";
    public static final String URL_HALL = "/hall";
    public static final String URL_PERFORMANCE = "/performance";
    public static final String URL_BOOKING = "/booking";
    public static final String URL_TICKET = "/ticket";
    public static final String URL_SHOW_DETAIL = "/show_detail";
    public static final String URL_BOOKINGS_TICKETS = "/bookings_tickets";

    private WebUrls() {
        throw new AssertionError("This is not the class you are looking for");
    }
}

