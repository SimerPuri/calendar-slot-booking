package com.calendar.resources;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AuthToken;
import com.calendar.service.AuthService;
import com.calendar.validator.CalendarSlotBookingValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static com.calendar.constants.CalendarServiceConstants.AUTH;
import static com.calendar.constants.CalendarServiceConstants.VERSION;

/**
 * The type Auth resource.
 *
 * for auth tokens
 */
@Path(VERSION + AUTH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    private static final Logger LOG = LoggerFactory.getLogger(AuthResource.class);

    private AuthService authService;

    /**
     * Instantiates a new Auth resource.
     *
     * @param authService the auth service
     */
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }


    /**
     * Generate auth token response.
     *
     * @param userId the user id
     * @return the response
     */
    @GET
    @Path("/{id}")
    public Response generateAuthToken(@PathParam("id") UUID userId) {
        try {
            // validate id
            if (!CalendarSlotBookingValidator.isUserIdUUIDValid(userId)) {
                throw new CalendarSlotBookingException(Response.Status.BAD_REQUEST.getStatusCode(),
                        "User Id not Valid");
            }
            AuthToken authToken = authService.generateAuthToken(userId);
            return Response.ok(authToken).build();
        } catch (CalendarSlotBookingException e) {
            LOG.warn("Error occurrred with status {}", e.getStatusCode(), e);
            return Response.status(e.getStatusCode())
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            LOG.error("Error occurrred with exception", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error Occurred")
                    .build();
        }
    }

}
