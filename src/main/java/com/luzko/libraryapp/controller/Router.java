package com.luzko.libraryapp.controller;

public class Router {
    public enum RouteType {
        FORWARD,
        REDIRECT
    }

    private RouteType routeType;
    private String pagePath;

    public Router() {
        this.routeType = RouteType.FORWARD;
    }

    public Router(String pagePath) {
        this.routeType = RouteType.FORWARD;
        this.pagePath = pagePath;
    }

    public Router(RouteType routeType, String pagePath) {
        this.routeType = routeType;
        this.pagePath = pagePath;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setRedirect() {
        this.routeType = RouteType.REDIRECT;
    }
}
