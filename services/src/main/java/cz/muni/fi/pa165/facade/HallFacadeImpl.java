package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.HallDTO;
import cz.muni.fi.pa165.entity.Hall;
import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.services.HallService;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author xtrnkal
 */
public class HallFacadeImpl implements HallFacade {

    @Inject
    private HallService hallService;

    @Inject
    private BeanMappingService mappingService;

    @Override
    public Long createHall(HallDTO newHall) {
        Hall mappedHall = mappingService.mapTo(newHall, Hall.class);
        hallService.create(mappedHall);
        return mappedHall.getId();
    }

    @Override
    public List<HallDTO> getAllHalls() {
        return mappingService.mapTo(hallService.findAll(), HallDTO.class);
    }

    @Override
    public HallDTO getHallById(Long id) {
        return mappingService.mapTo(hallService.findById(id), HallDTO.class);
    }

    @Override
    public void removeHall(HallDTO hall) {
        hallService.remove(mappingService.mapTo(hall, Hall.class));
    }

    @Override
    public void updateHall(HallDTO hall) {
        hallService.update(mappingService.mapTo(hall, Hall.class));
    }
}
