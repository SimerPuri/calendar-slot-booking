package com.calendar.resources;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AuthToken;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserInfo;
import com.calendar.models.UserSlotsMapping;
import com.calendar.service.AuthService;
import com.calendar.service.CalendarSlotBookingService;
import com.calendar.service.CalendarSlotBookingServiceImpl;
import com.calendar.validator.CalendarSlotBookingValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

import static com.calendar.constants.CalendarServiceConstants.AUTH;
import static com.calendar.constants.CalendarServiceConstants.CALENDAR;
import static com.calendar.constants.CalendarServiceConstants.VERSION;

@Path(VERSION + AUTH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    private static final Logger LOG = LoggerFactory.getLogger(AuthResource.class);

    private AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }


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
