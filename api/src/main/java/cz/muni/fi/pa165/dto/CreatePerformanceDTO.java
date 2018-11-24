package cz.muni.fi.pa165.dto;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author xtrnkal
 */
public class CreatePerformanceDTO {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private Long showId;

    @NotNull
    private Long hallId;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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
