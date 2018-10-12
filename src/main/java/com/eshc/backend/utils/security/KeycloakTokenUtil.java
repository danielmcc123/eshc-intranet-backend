package com.eshc.backend.utils.security;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;

import javax.ws.rs.core.Context;
import java.security.Principal;

public class KeycloakTokenUtil {

    @Context
    static
    HttpServletRequest request;

    public static UserDetails GetUserDetails(Principal principal){
        KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = ((KeycloakPrincipal<KeycloakSecurityContext>) ((KeycloakAuthenticationToken) principal).getPrincipal());
        final AccessToken token = keycloakPrincipal.getKeycloakSecurityContext().getToken();
        return new UserDetails(Long.parseLong(token.getOtherClaims().get("eshcId").toString()), token.getGivenName(),
                token.getFamilyName(), token.getEmail(),
                token.getRealmAccess().getRoles());
    }
}
