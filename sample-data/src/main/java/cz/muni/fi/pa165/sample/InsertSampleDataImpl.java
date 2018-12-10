package cz.muni.fi.pa165.sample;

import static cz.muni.fi.pa165.EntityUtils.*;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.services.*;

import javax.inject.Inject;
import java.time.LocalDate;


public class InsertSampleDataImpl implements InsertSampleData {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Inject
    private ShowService showService;

    @Inject
    private HallService hallService;

    @Inject
    private PerformanceService performanceService;

    @Inject
    private GenreService genreService;

    @Inject
    private BookingService bookingService;

    @Inject
    private TicketService ticketService;

    @Override
    public void insertData() {
        Role userRole = createRole("User", "User of system");
        Role adminRole = createRole("Admin", "Administrator");
        Users userUser = createUser("Robert", "Dudas", "rober@dudas.cz", "123456789", userRole, LocalDate.now(), LocalDate.now());
        Users userAdmin = createUser("Tomas", "Rudolf", "tomas@rudolf.cz", "Passw0rd", adminRole, LocalDate.now(), LocalDate.now());

        Genre genreDrama = createGenre("Drama", "Drama");
        Genre genreComedy = createGenre("Comedy", "Comedy");
        Show show1 = createShow("Show1", "Show1Description", genreDrama, 63);
        Show show2 = createShow("Show2", "Show2Description", genreDrama, 42);
        Show show3 = createShow("Show3", "Show3Description", genreComedy, 38);
        Show show4 = createShow("Show4", "Show4Description", genreComedy, 54);

        Hall hall1 = createHall("Hall1", "Hall1Description", 103l);
        Hall hall2 = createHall("Hall2", "Hall2Description", 40l);
        Hall hall3 = createHall("Hall3", "Hall3Description", 65l);

        Performance performance = createPerformance("The only chance to see", 15.0f, hall1, show1, LocalDate.now());

        roleService.create(userRole);
        roleService.create(adminRole);

        userService.create(userUser);
        userService.create(userAdmin);

        genreService.create(genreDrama);
        genreService.create(genreComedy);

        showService.create(show1);
        showService.create(show2);
        showService.create(show3);
        showService.create(show4);

        hallService.create(hall1);
        hallService.create(hall2);
        hallService.create(hall3);

        performanceService.create(performance);
    }
}
