//import com.calendar.enums.Rooms;
//import com.calendar.models.EventRequest;
//import com.calendar.models.ImmutableEventRequest;
//import com.calendar.service.CalendarSlotBookingService;
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class CalendarServiceTest {
//    private CalendarSlotBookingService calendarService;
////a
//    @Test
//    public void createEventTest() {
////        calendarService = new CalendarSlotBookingServiceImpl();
////        EventResponse eventResponse = calendarService.createEvent(getRequest());
////        assertEquals(eventResponse.getOwner(), "Walmart");
////        assertEquals(eventResponse.getGuests().get(0).getGuestName(), "Simer");
////        assertEquals(eventResponse.getTitle(), "Test");
//
//    }
//    // TODO: similarly cases for update and cancel events.
//
//    @Test
//    public void getGuestEventTest() {
////        calendarService = new CalendarSlotBookingServiceImpl();
////        EventResponse eventResponse = calendarService.createEvent(getRequest());
////        assertEquals(eventResponse.getOwner(), "Walmart");
////        assertEquals(eventResponse.getGuests().get(0).getGuestName(), "Simer");
////        assertEquals(eventResponse.getTitle(), "Test");
////
////        List<Guest> guests = calendarService.getMeetingGuests(eventResponse.getEventId());
////
////        assertEquals(eventResponse.getGuests().get(0).getGuestName(), "Simer");
////        assertEquals(eventResponse.getGuests().get(0).getGuestResponse(), GuestResponse.NEUTRAL);
//
//
//    }
//
//    private EventRequest getRequest() {
//        return ImmutableEventRequest.builder()
//                .owner("Walmart")
//                .startTime(LocalDateTime.now())
//                .endTime(LocalDateTime.now())
//                .addGuestList("Simer")
//                .location(Rooms.AGRA)
//                .title("Test")
//                .build();
//    }
//
//
//}
