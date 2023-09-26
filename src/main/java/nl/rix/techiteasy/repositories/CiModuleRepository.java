package nl.rix.techiteasy.repositories;

import nl.rix.techiteasy.dtos.CiModuleDto;
import nl.rix.techiteasy.models.CiModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiModuleRepository extends JpaRepository<CiModule, Long> {

    default CiModuleDto transferCiModuleToDto(CiModule ciModule) {
        CiModuleDto dto = new CiModuleDto();
        dto.setId(ciModule.getId());
        dto.setName(ciModule.getName());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());
        return dto;
    }

    default CiModule transferDtoToCiModule(CiModuleDto ciDto) {
        CiModule ci = new CiModule();
        ci.setId(ciDto.getId());
        ci.setName(ciDto.getName());
        ci.setType(ciDto.getType());
        ci.setPrice(ciDto.getPrice());
        return ci;
    }
}
