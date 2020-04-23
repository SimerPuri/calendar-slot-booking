import com.calendar.manager.AuthDataManager;
import com.calendar.manager.CalendarSlotBookingDataManagerImpl;
import com.calendar.models.ImmutableAvailableSlotsRequest;
import com.calendar.models.ImmutableUser;
import com.calendar.models.UserSlotsMapping;
import com.calendar.models.UserTokenInfo;
import com.calendar.service.CalendarSlotBookingService;
import com.calendar.service.CalendarSlotBookingServiceImpl;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CalendarServiceTest {
    private CalendarSlotBookingService calendarSlotBookingService;

    @Test
    public void createUser() throws Exception {
        calendarSlotBookingService = new CalendarSlotBookingServiceImpl(CalendarSlotBookingDataManagerImpl.getInstance(),
                AuthDataManager.getInstance());
        UserTokenInfo userTokenInfo = calendarSlotBookingService
                .registerUser(ImmutableUser.builder().userName("Simer").build());
        assertEquals(userTokenInfo.getUserName(), "Simer");

    }

    @Test
    public void addSlots() throws Exception {
        calendarSlotBookingService = new CalendarSlotBookingServiceImpl(CalendarSlotBookingDataManagerImpl.getInstance(),
                AuthDataManager.getInstance());
        UserTokenInfo userTokenInfo = calendarSlotBookingService
                .registerUser(ImmutableUser.builder().userName("Simer").build());
        assertEquals(userTokenInfo.getUserName(), "Simer");

        UserSlotsMapping userSlotsMapping = calendarSlotBookingService
                .addAvailableSlots(userTokenInfo.getUserId(), getAvailableSlotsRequest());
        Set<LocalTime> time = userSlotsMapping.getAvailableSlots().get(getAvailableSlotsRequest().getSlotDate());
        assertEquals(time, new HashSet<LocalTime>(Collections.singleton(LocalTime.NOON)));

    }

    @Test
    public void getAvailableSlots() throws Exception {
        calendarSlotBookingService = new CalendarSlotBookingServiceImpl(CalendarSlotBookingDataManagerImpl.getInstance(),
                AuthDataManager.getInstance());
        UserTokenInfo userTokenInfo = calendarSlotBookingService
                .registerUser(ImmutableUser.builder().userName("Simer").build());
        assertEquals(userTokenInfo.getUserName(), "Simer");

        UserSlotsMapping userSlotsMapping = calendarSlotBookingService
                .addAvailableSlots(userTokenInfo.getUserId(), getAvailableSlotsRequest());

        Map slots = calendarSlotBookingService
                .getAvailableSlotsForUser(userTokenInfo.getUserId());
        assertEquals(slots.get(getAvailableSlotsRequest().getSlotDate()),
                new HashSet<LocalTime>(Collections.singleton(LocalTime.NOON)));

    }


    private ImmutableAvailableSlotsRequest getAvailableSlotsRequest() {
        return ImmutableAvailableSlotsRequest
                .builder().slotDate(LocalDate.now()).addSlotsTime(LocalTime.NOON).build();
    }


}
