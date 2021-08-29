package com.sb.spring.authorization.util;


public interface SecurityConstants {
    String RESOURCE_ID = "micro";

    String CLIENT_ID = "client";
    String CLIENT_SECRET = "$2a$10$HlzMvb9OQayvlWfX5pzJTeZvH30eKwnc6gE0C3idjDvbQt4XJMTki";// secret
    String GRANT_TYPE_PASSWORD = "password";
    String AUTHORIZATION_CODE = "authorization_code";
    String REFRESH_TOKEN = "refresh_token";
    String IMPLICIT = "implicit";

    int ACCESS_TOKEN_VALIDITY_SECONDS = 3600;
    int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * ACCESS_TOKEN_VALIDITY_SECONDS;

    String SCOPE_READ = "read";
    String SCOPE_WRITE = "write";

    String OAUTH2_HAS_SCOPE_READ = "#oauth2.hasScope('" + SCOPE_READ + "')";
    String OAUTH2_HAS_SCOPE_WRITE = "#oauth2.hasScope('" + SCOPE_WRITE + "')";
}
