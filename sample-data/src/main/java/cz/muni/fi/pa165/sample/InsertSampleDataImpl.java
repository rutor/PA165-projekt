package cz.muni.fi.pa165.sample;

import static cz.muni.fi.pa165.EntityUtils.*;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.services.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
@Transactional
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
        Role userRole = createRole(null, "User", "User of system");
        Role adminRole = createRole(null, "Admin", "Administrator");
        Users userUser = createUser(null,"Robert", "Dudas", "rober@dudas.cz", "123456789", userRole, LocalDate.now(), LocalDate.now());
        Users userAdmin = createUser(null,"Tomas", "Rudolf", "tomas@rudolf.cz", "Passw0rd", adminRole, LocalDate.now(), LocalDate.now());

        Genre genreDrama = createGenre(null,"Drama", "Drama");
        Genre genreComedy = createGenre(null,"Comedy", "Comedy");
        Show show1 = createShow(null,"Show1", "Show1Description", genreDrama, 63);
        Show show2 = createShow(null,"Show2", "Show2Description", genreDrama, 42);
        Show show3 = createShow(null,"Show3", "Show3Description", genreComedy, 38);
        Show show4 = createShow(null,"Show4", "Show4Description", genreComedy, 54);

        Hall hall1 = createHall(null,"Hall1", "Hall1Description", 103l);
        Hall hall2 = createHall(null,"Hall2", "Hall2Description", 40l);
        Hall hall3 = createHall(null,"Hall3", "Hall3Description", 65l);

        Performance performance1 = createPerformance(null, "The only chance to see", 15.0f, hall1, show1, LocalDateTime.now());
        Performance performance2 = createPerformance(null, "Test performance", 12.0f, hall2, show2, LocalDateTime.now());

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

        performanceService.create(performance1);
        performanceService.create(performance2);
    }
}
