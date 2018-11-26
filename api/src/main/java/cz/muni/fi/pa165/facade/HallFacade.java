package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.HallDTO;
import java.util.List;

/**
 *
 * @author xtrnkal
 */
public interface HallFacade {

    public Long createHall(HallDTO newHall);

    public List<HallDTO> getAllHalls();

    public HallDTO getHallById(Long id);

    public void removeHall(Long id);

    public void updateHall(HallDTO hall);
}
