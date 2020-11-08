package com.luzko.libraryapp.controller;

/**
 * The type represents a router, containing the type of the router and the path to the page.
 */
public class Router {
    /**
     * The enum represents type of the router.
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
     * @param pagePath the path to the page
     */
    public Router(String pagePath) {
        this.routeType = RouteType.FORWARD;
        this.pagePath = pagePath;
    }

    /**
     * Instantiates a new Router.
     *
     * @param routeType the type of the router
     * @param pagePath  the path to the page
     */
    public Router(RouteType routeType, String pagePath) {
        this.routeType = routeType;
        this.pagePath = pagePath;
    }

    /**
     * Gets type of the router.
     *
     * @return the type of the router
     */
    public RouteType getRouteType() {
        return routeType;
    }

    /**
     * Sets type of the router.
     *
     * @param routeType the type of the router
     */
    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    /**
     * Gets path to the page.
     *
     * @return the path to the page
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Sets path to the page.
     *
     * @param pagePath the path to the page
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Sets the type of router redirection.
     */
    public void setRedirect() {
        this.routeType = RouteType.REDIRECT;
    }
}
