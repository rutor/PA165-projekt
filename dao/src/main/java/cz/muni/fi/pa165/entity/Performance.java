package cz.muni.fi.pa165.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class for show performances
 *
 * @author xtrnkal
 */
@Entity
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Float price;

    @Column(nullable=false)
    @NotNull
    private LocalDate startDate;

    @ManyToOne(targetEntity = Show.class)
    private Show show;
    
    @ManyToOne(targetEntity = Hall.class)
    private Hall hall;

    /** Persistence constructor */
    public Performance() {

    }

    public Performance(Show show, Hall hall) {
        this.show = show;
        this.hall = hall;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price, startDate, hall, show);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Performance) {
            Performance other = (Performance) obj;
            if (!Objects.equals(description, other.getDescription())) {
                return false;
            }
            if (!Objects.equals(price, other.getPrice())) {
                return false;
            }
            if (!Objects.equals(startDate, other.getStartDate())) {
                return false;
            }
            if (!Objects.equals(hall, other.getHall())) {
                return false;
            }
            if (!Objects.equals(show, other.getShow())) {
                return false;
            }

            return true;
        }
        return false;
    }
}
