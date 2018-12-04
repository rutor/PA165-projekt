package cz.muni.fi.pa165.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author xtrnkal
 */
public class PerformanceDTO {

    private Long id;
    private String description;
    private Float price;
    private LocalDate startDate;
    private ShowDTO show;
    private HallDTO hall;

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

    public ShowDTO getShow() {
        return show;
    }

    public void setShow(ShowDTO show) {
        this.show = show;
    }

    public HallDTO getHall() {
        return hall;
    }

    public void setHall(HallDTO hall) {
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
        if (obj instanceof PerformanceDTO) {
            PerformanceDTO other = (PerformanceDTO) obj;
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
