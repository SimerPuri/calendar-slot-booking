package com.calendar.filter;

import com.calendar.manager.AuthDataManager;
import com.calendar.models.AuthToken;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.calendar.constants.CalendarServiceConstants.AUTH;
import static com.calendar.constants.CalendarServiceConstants.PARAM_AUTHORIZATION;

@Provider
public class AuthenticateRequestFilter implements ContainerRequestFilter {
    private static final String PARAM_TOKEN = "token";
    private AuthDataManager authDataManager = AuthDataManager.getInstance();

    @Override
    public void filter(ContainerRequestContext context) {
        if (context.getUriInfo().getPath().contains(AUTH)) {
            return;
        }
        String args[] = context.getUriInfo().getPath().split("/");
        if (args.length <= 3) {
            return;
        }
        final String authToken = context.getHeaderString(PARAM_AUTHORIZATION);
        if (StringUtils.isEmpty(authToken)) {
            context.abortWith(responseMissingParameter(PARAM_AUTHORIZATION));
            return;
        }

        if (!isAuthenticate(UUID.fromString(authToken), UUID.fromString(args[3]))) {
            context.abortWith(responseUnauthorized());
            return;
        }
    }

    private boolean isAuthenticate(UUID authToken, UUID userId) {
        AuthToken token = authDataManager.getToken(userId);

        if (token == null || !token.getAuthToken().equals(authToken) || token.getTokenValidity()
                .isBefore(LocalDateTime.now())) {
            return false;
        }

        return true;
    }

    private Response responseMissingParameter(String name) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Parameter '" + name + "' is required.")
                .build();
    }

    private Response responseUnauthorized() {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Unauthorized")
                .build();
    }
}
