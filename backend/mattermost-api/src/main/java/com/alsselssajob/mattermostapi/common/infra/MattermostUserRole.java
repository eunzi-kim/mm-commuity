package com.alsselssajob.mattermostapi.common.infra;

public enum MattermostUserRole {

    PROFESSOR("교수"),
    CONSULTANT("컨설턴트"),
    COACH("코치"),
    EDU_PRO("프로");

    private String role;

    MattermostUserRole(final String role) {
        this.role = role;
    }

    public String role() {
        return role;
    }
}
