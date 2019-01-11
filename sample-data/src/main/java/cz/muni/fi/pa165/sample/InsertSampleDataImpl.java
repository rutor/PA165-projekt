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
        Users userRobo = createUser(null,"Robert", "Dudas", "rober@dudas.cz", "123456789", userRole, LocalDate.now(), LocalDate.now());
        Users userLukas = createUser(null,"Lukas", "Tyrychtr", "lukas@tyrychtr.cz", "lukastyrychtr", userRole, LocalDate.now(), LocalDate.now());
        Users userAnna = createUser(null,"Anna", "Trnkalova", "anna@trnkalova.cz", "annatrnkalova", userRole, LocalDate.now(), LocalDate.now());
        Users userTomas = createUser(null,"Tomas", "Rudolf", "tomas@rudolf.cz", "Passw0rd", adminRole, LocalDate.now(), LocalDate.now());

        Genre genreDrama = createGenre(null,"Drama", "Drama");
        Genre genreComedy = createGenre(null,"Comedy", "Comedy");
        Genre genreOpera‎ = createGenre(null,"Opera", "Opera");
        Genre genreSatire‎ = createGenre(null,"Satire", "Satire");
        Genre genreTragedy = createGenre(null,"Tragedy", "Tragedy");
        Genre genreMusic = createGenre(null,"Music", "Music");
        Genre genrePhysical = createGenre(null,"Physical", "Physical");
        Genre genreFamilyKids = createGenre(null,"Family_Kids", "Family_Kids");
        Genre genreChristmas = createGenre(null,"Christmas", "Christmas");

        Show Betrayal = createShow(null,"Betrayal", "The plot of the play covers the seven-year extramarital affair between two characters, Emma and Jerry, who would meet in secret at a flat. Pinter has said before that Betrayal is the one play of his that most resembles his own life.", genreDrama, 65);
        Show Company = createShow(null,"Company", "Broadway legend Patti LuPone returns to London in a new gender-reversed production of Stephen Sondheim and George Furth's 1970 musical Company.", genreMusic, 83);
        Show Disneys_The_Lion_King = createShow(null,"Disney's The Lion King", "Walt Disney's long-running and multi-award winning musical The Lion King continues to delight audiences in London's West End.", genreOpera‎, 78);
        Show A_Christmas_Carol = createShow(null,"A Christmas Carol", "Esteemed writer Jack Thorne's joyous, atmospheric adaptation of the timeless Charles Dickens classic returns to The Old Vic folowing a successful run last Christmas.", genreChristmas, 98);
        Show Wicked = createShow(null,"Wicked", "Since opening on Broadway in 2003, Wicked has taken the world of musical theatre by storm.", genreMusic, 76);
        Show La_boheme = createShow(null,"La boheme", "When Mimì knocks at the door of four impoverished friends, Rodolfo answers and it’s love at first site.", genreOpera‎, 69);

        Show BalletFashion = createShow(null,"Ballet & Fashion", "The ballet of National Theater prepares for today´s show, in which are mixed world of dance and fashion.", genreMusic, 65);
        Show BedrichSmetana = createShow(null,"Bedřich Smetana", "Main role is to show important composers, art of the time and other topics.", genreOpera‎, 120);
        Show Zelary = createShow(null,"Zelary", "Abandoned mountain village somewhere in Beskydes, where is life controlled by hard changes and pure nature. Destine of the villagers in the background of the Second World War and german occupation, when intractable people lose their freedom.", genreDrama, 65);


        Hall bigHall = createHall(null,"Hall1", "Hall1Description", 103l);
        Hall hall400 = createHall(null,"Hall2", "Hall2Description", 400l);
        Hall hall500 = createHall(null,"Hall3", "Hall3Description", 500l);
        Hall smallHall = createHall(null,"Hall4", "Hall4Description", 65l);

        Performance performance1 = createPerformance(null, "The only chance to see", 15.0f, bigHall, BalletFashion, LocalDateTime.now());
        Performance performance2 = createPerformance(null, "ZelaryPerformance", 12.0f, hall400,Zelary, LocalDateTime.now());
        Performance performance3 = createPerformance(null, "CompanyPerformance", 12.0f, hall500,Company, LocalDateTime.now());
        Performance performance4 = createPerformance(null, "The best performance", 18.0f, bigHall, BedrichSmetana, LocalDateTime.now());

        roleService.create(userRole);
        roleService.create(adminRole);

        userService.create(userRobo);
        userService.create(userLukas);
        userService.create(userAnna);
        userService.create(userTomas);

        genreService.create(genreDrama);
        genreService.create(genreComedy);
        genreService.create(genreOpera‎);
        genreService.create(genreChristmas);
        genreService.create(genreMusic);
        genreService.create(genreTragedy);
        genreService.create(genrePhysical);
        genreService.create(genreFamilyKids);
        genreService.create(genreSatire‎);

        showService.create(BalletFashion);
        showService.create(Company);
        showService.create(BedrichSmetana);
        showService.create(Zelary);
        showService.create(La_boheme);
        showService.create(Betrayal);
        showService.create(Wicked);
        showService.create(A_Christmas_Carol);
        showService.create(Disneys_The_Lion_King);

        hallService.create(bigHall);
        hallService.create(smallHall);
        hallService.create(hall400);
        hallService.create(hall500);

        performanceService.create(performance1);
        performanceService.create(performance2);
        performanceService.create(performance3);
        performanceService.create(performance4);
    }
}
