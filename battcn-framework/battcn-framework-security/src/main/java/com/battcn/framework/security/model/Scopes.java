package com.battcn.framework.security.model;

/**
 * Scopes
 *
 * @author Levin
 * @since 2017-05-25
 */
public enum Scopes {

    /**
     * 刷新TOKEN
     */
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
