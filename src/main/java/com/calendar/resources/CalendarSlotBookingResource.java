package com.calendar.resources;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserSlotsMapping;
import com.calendar.models.UserTokenInfo;
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

import static com.calendar.constants.CalendarServiceConstants.CALENDAR;
import static com.calendar.constants.CalendarServiceConstants.USER_ID_VALIDATION_MESSAGE;
import static com.calendar.constants.CalendarServiceConstants.VERSION;

/**
 * The type Calendar slot booking resource.
 * user slot booking
 */
@Path(VERSION + CALENDAR + "/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalendarSlotBookingResource {
    private static final Logger LOG = LoggerFactory.getLogger(CalendarSlotBookingResource.class);

    private CalendarSlotBookingService calendarSlotBookingService;

    /**
     * Instantiates a new Calendar slot booking resource.
     *
     * @param calendarSlotBookingService the calendar slot booking service
     */
    public CalendarSlotBookingResource(CalendarSlotBookingServiceImpl calendarSlotBookingService) {
        this.calendarSlotBookingService = calendarSlotBookingService;
    }


    /**
     * Register user response.
     *
     * @param user the user
     * @return the response
     */
    @POST
    public Response registerUser(User user) {
        try {

            UserTokenInfo userInfo = calendarSlotBookingService.registerUser(user);
            return Response.ok(userInfo).build();
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


    /**
     * Add available slots response.
     *
     * @param userId         the user id
     * @param slotsAvailable the slots available
     * @return the response
     */
    @PUT
    @Path("/{userId}")
    public Response addAvailableSlots(@PathParam("userId") UUID userId, AvailableSlotsRequest slotsAvailable) {
        try {
            // validate id
            if (!CalendarSlotBookingValidator.isUserIdUUIDValid(userId)) {
                throw new CalendarSlotBookingException(Response.Status.BAD_REQUEST.getStatusCode(),
                        USER_ID_VALIDATION_MESSAGE);
            }
            UserSlotsMapping userSlotsMapping = calendarSlotBookingService.addAvailableSlots(userId, slotsAvailable);
            return Response.ok(userSlotsMapping).build();
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

    /**
     * Gets available slots for user.
     *
     * @param userId the user id
     * @return the available slots for user
     */
    @GET
    @Path("/{userId}")
    public Response getAvailableSlotsForUser(@PathParam("userId") UUID userId) {
        try {
            // validate id
            if (!CalendarSlotBookingValidator.isUserIdUUIDValid(userId)) {
                throw new CalendarSlotBookingException(Response.Status.BAD_REQUEST.getStatusCode(),
                        USER_ID_VALIDATION_MESSAGE);
            }
            Map availableSlotsForUser = calendarSlotBookingService.getAvailableSlotsForUser(userId);
            if (availableSlotsForUser != null && availableSlotsForUser.size() > 0) {
                return Response.ok(availableSlotsForUser).build();
            } else {
                return Response.status(Response.Status.OK)
                        .entity(String.format("No Available Slots found for userId - %s", userId))
                        .build();
            }
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


    /**
     * Book slots response.
     *
     * @param bookingUserId      the booking user id
     * @param slotBookingRequest the slot booking request
     * @return the response
     */
    @POST
    @Path("/{userId}/bookSlots")
    public Response bookSlots(@PathParam("userId") UUID bookingUserId, SlotBookingRequest slotBookingRequest) {
        try {
            // validate id
            if (!CalendarSlotBookingValidator.isUserIdUUIDValid(bookingUserId) ||
                    !CalendarSlotBookingValidator.isUserIdUUIDValid(slotBookingRequest.getUserId())) {
                throw new CalendarSlotBookingException(Response.Status.BAD_REQUEST.getStatusCode(),
                        "Booking User/ Booked User Id not Valid");
            }
            calendarSlotBookingService.bookSlot(bookingUserId, slotBookingRequest);
            return Response.noContent().build();
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
