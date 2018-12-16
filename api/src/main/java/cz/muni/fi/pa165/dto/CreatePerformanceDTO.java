package cz.muni.fi.pa165.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author xtrnkal
 */
public class CreatePerformanceDTO {
    private LocalDateTime startDate;
    private Long showId;
    private Long hallId;
    private String description;
    private Float price;

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(String start) {
        LocalDateTime date = LocalDateTime.parse(start, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        date.minusNanos(date.getNano());
        this.startDate = date;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, hallId, showId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CreatePerformanceDTO) {
            CreatePerformanceDTO other = (CreatePerformanceDTO) obj;
            if (!Objects.equals(startDate, other.getStartDate())) {
                return false;
            }
            if (!Objects.equals(hallId, other.getHallId())) {
                return false;
            }
            if (!Objects.equals(showId, other.getShowId())) {
                return false;
            }
            return true;
        }
        return false;
    }
}
