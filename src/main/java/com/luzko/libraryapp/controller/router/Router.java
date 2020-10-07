package com.luzko.libraryapp.controller.router;

public class Router {
    private RouterType routerType;
    private String pagePath;

    public Router() {

    }

    public Router(RouterType routerType, String pagePath) {
        this.routerType = routerType;
        this.pagePath = pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public void setRouterType(RouterType routerType) {
        this.routerType = routerType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }
}
