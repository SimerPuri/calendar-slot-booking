package com.calendar;

import com.calendar.filter.AuthenticateRequestFilter;
import com.calendar.manager.AuthDataManager;
import com.calendar.manager.CalendarSlotBookingDataManagerImpl;
import com.calendar.resources.AuthResource;
import com.calendar.resources.CalendarSlotBookingResource;
import com.calendar.service.AuthService;
import com.calendar.service.CalendarSlotBookingServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;


/**
 * The type Calendar service application.
 */
public class CalendarServiceApplication extends Application<Configuration> {
    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        AuthDataManager authDataManager = AuthDataManager.getInstance();
        CalendarSlotBookingServiceImpl calendarService = new CalendarSlotBookingServiceImpl(
                CalendarSlotBookingDataManagerImpl.getInstance(), authDataManager);
        AuthResource authResource = new AuthResource(new AuthService(authDataManager));
        environment.jersey().register(new CalendarSlotBookingResource(calendarService));
        environment.jersey().register(authResource);
        environment.jersey().register(new AuthenticateRequestFilter());

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        new CalendarServiceApplication().run(args);
    }
}
