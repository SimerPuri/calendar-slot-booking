package com.calendar.manager;

import com.calendar.customexception.CalendarSlotBookingException;
import com.calendar.models.AvailableSlotsRequest;
import com.calendar.models.SlotBookingRequest;
import com.calendar.models.User;
import com.calendar.models.UserInfo;
import com.calendar.models.UserSlotsMapping;
import org.eclipse.jetty.server.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Calendar slot booking data manager.
 * manages data for user info
 */
public class CalendarSlotBookingDataManagerImpl implements CalendarSlotBookingDataManager {
    private static CalendarSlotBookingDataManagerImpl calendarDataManager;
    // contains info about user slots
    private Map<UUID, UserSlotsMapping> usersSlotsInfo;

    private CalendarSlotBookingDataManagerImpl() {
        this.usersSlotsInfo = new ConcurrentHashMap<>();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CalendarSlotBookingDataManagerImpl getInstance() {
        if (calendarDataManager == null) {
            synchronized (CalendarSlotBookingDataManagerImpl.class) {
                if (calendarDataManager == null) {
                    calendarDataManager = new CalendarSlotBookingDataManagerImpl();
                }
            }
        }

        return calendarDataManager;
    }


    @Override
    public UserInfo registerUser(User user) throws Exception {
        if (isUserExists(user)) {
            throw new CalendarSlotBookingException(Response.SC_CONFLICT,
                    "User Already Exists");
        }
        UUID userId = UUID.randomUUID();
        UserInfo userInfo = new UserInfo(user.getUserName(), userId);
        UserSlotsMapping userSlotsMapping = new UserSlotsMapping(userInfo, new HashMap<>());
        usersSlotsInfo.put(userId, userSlotsMapping);
        return userInfo;
    }

    @Override
    public boolean isUserExists(User user) {
        return usersSlotsInfo.values().stream().anyMatch(u -> user.getUserName().trim()
                .equalsIgnoreCase(u.getUserInfo().getUserName()));
    }

    @Override
    public boolean isUserIdExists(UUID userid) {
        return usersSlotsInfo.values().stream().anyMatch(u -> userid
                .equals(u.getUserInfo().getUserId()));
    }

    @Override
    public UserSlotsMapping addAvailableSlots(UUID id, AvailableSlotsRequest slotsAvailable) {
        Map<LocalDate, Set<LocalTime>> userSlots = usersSlotsInfo.get(id).getAvailableSlots();

        if (userSlots.containsKey(slotsAvailable.getSlotDate())) {
            Set<LocalTime> timeSlots = new HashSet<>(userSlots.get(slotsAvailable.getSlotDate()));
            timeSlots.addAll(slotsAvailable.getSlotsTime());
            usersSlotsInfo.get(id).setAvailableSlotForDate(slotsAvailable.getSlotDate(), timeSlots);
        } else {
            usersSlotsInfo.get(id).setAvailableSlotForDate(slotsAvailable.getSlotDate(),
                    slotsAvailable.getSlotsTime());
        }

       return usersSlotsInfo.get(id);
    }

    @Override
    public Map getAvailableSlotsForUser(UUID id) {
        return usersSlotsInfo.get(id).getAvailableSlots();
    }

    @Override
    public boolean bookSlot(UUID bookingUserId, SlotBookingRequest slotBookingRequest)
            throws CalendarSlotBookingException {
        Set<LocalTime> bookingUserSlots = usersSlotsInfo.get(bookingUserId).getAvailableSlots()
                .get(slotBookingRequest.getSlotDate());

        Set<LocalTime> bookedUserSlots = usersSlotsInfo.get(slotBookingRequest.getUserId()).getAvailableSlots()
                .get(slotBookingRequest.getSlotDate());

        if (bookingUserSlots == null || !bookingUserSlots.contains(slotBookingRequest.getSlotTime())) {
            throw new CalendarSlotBookingException(Response.SC_BAD_REQUEST, "Booking User Slot not available");
        }

        if (bookedUserSlots == null || !bookedUserSlots.contains(slotBookingRequest.getSlotTime())) {
            throw new CalendarSlotBookingException(Response.SC_BAD_REQUEST, "Booked User Slot not available");
        }

        Set<LocalTime> updatedBookingUserSlots = new HashSet<>(bookingUserSlots);
        updatedBookingUserSlots.remove(slotBookingRequest.getSlotTime());
        usersSlotsInfo.get(bookingUserId).setAvailableSlotForDate(slotBookingRequest.getSlotDate(),
                updatedBookingUserSlots);


        Set<LocalTime> updatedBookedUserSlots = new HashSet<>(bookedUserSlots);
        updatedBookedUserSlots.remove(slotBookingRequest.getSlotTime());
        usersSlotsInfo.get(slotBookingRequest.getUserId()).setAvailableSlotForDate(slotBookingRequest.getSlotDate(),
                updatedBookedUserSlots);

        return true;
    }

}
