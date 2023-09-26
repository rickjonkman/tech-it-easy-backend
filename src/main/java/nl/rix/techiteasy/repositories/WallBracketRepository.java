package nl.rix.techiteasy.repositories;

import nl.rix.techiteasy.dtos.WallBracketDto;
import nl.rix.techiteasy.models.WallBracket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WallBracketRepository extends JpaRepository<WallBracket, Long> {

    default WallBracket transferDtoToWallBracket(WallBracketDto dto) {
        WallBracket wb = new WallBracket();
        wb.setId(dto.getId());
        wb.setName(dto.getName());
        wb.setType(dto.getType());
        wb.setPrice(dto.getPrice());
        return wb;
    }

    default WallBracketDto transferWallBracketToDto(WallBracket wb) {
        WallBracketDto dto = new WallBracketDto();
        dto.setId(wb.getId());
        dto.setName(wb.getName());
        dto.setType(wb.getType());
        dto.setPrice(wb.getPrice());
        return dto;
    }
}
