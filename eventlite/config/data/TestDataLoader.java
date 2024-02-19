package uk.ac.man.cs.eventlite.config.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
@Profile("test")
public class TestDataLoader {

	private final static Logger log = LoggerFactory.getLogger(TestDataLoader.class);

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			// Build and save test events and venues here.
			// The test database is configured to reside in memory, so must be initialized
			// every time.
			Venue venue = new Venue();
			venue.setName("Manchester");
			venue.setCapacity(100);
			venueService.save(venue);
            // Build and save initial events here.
            for (int i = 1; i <= 5; i++) {
                Event event = new Event();
                event.setName("Event " + i);
//                event.setDate(LocalDate.now());
                if (i == 1) {
                    event.setTime(LocalTime.of(8, 30, 0, 0));
                    event.setDate(LocalDate.of(2020, 12, 25));
                }
                else if (i == 2) {
                    event.setTime(LocalTime.of(10, 30, 0, 0));
                    event.setDate(LocalDate.of(2020, 12, 25));
                }
                else if (i == 3) {
                    event.setTime(LocalTime.of(7, 30, 0, 0));
                    event.setDate(LocalDate.of(2021, 12, 25));
                }
                else if (i == 4) {
                    event.setTime(LocalTime.MIN);
                    event.setDate(LocalDate.of(2019, 12, 25));
                }
                else if (i == 5) {
                    event.setTime(LocalTime.of(9, 30, 0, 0));
                    event.setDate(LocalDate.of(2020, 12, 25));
                }
//                event.setTime(LocalTime.now());
                event.setVenue(venue);
                eventService.save(event);
            }
		};
	}
}
