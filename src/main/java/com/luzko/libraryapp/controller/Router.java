package com.luzko.libraryapp.controller;

/**
 * The type Router.
 */
public class Router {
    /**
     * The enum Route type.
     */
    public enum RouteType {
        FORWARD,
        REDIRECT
    }

    private RouteType routeType;
    private String pagePath;

    /**
     * Instantiates a new Router.
     */
    public Router() {
        this.routeType = RouteType.FORWARD;
    }

    /**
     * Instantiates a new Router.
     *
     * @param pagePath the page path
     */
    public Router(String pagePath) {
        this.routeType = RouteType.FORWARD;
        this.pagePath = pagePath;
    }

    /**
     * Instantiates a new Router.
     *
     * @param routeType the route type
     * @param pagePath  the page path
     */
    public Router(RouteType routeType, String pagePath) {
        this.routeType = routeType;
        this.pagePath = pagePath;
    }

    /**
     * Gets route type.
     *
     * @return the route type
     */
    public RouteType getRouteType() {
        return routeType;
    }

    /**
     * Sets route type.
     *
     * @param routeType the route type
     */
    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    /**
     * Gets page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Sets page path.
     *
     * @param pagePath the page path
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Sets redirect.
     */
    public void setRedirect() {
        this.routeType = RouteType.REDIRECT;
    }
}
