package nl.rix.techiteasy.repositories;

import nl.rix.techiteasy.dtos.TelevisionDto;
import nl.rix.techiteasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository<Television, Long> {

    default Television transferDtoToTelevision(TelevisionDto dto) {
        Television tv = new Television();
        tv.setId(dto.getId());
        tv.setType(dto.getType());
        tv.setName(dto.getName());
        tv.setPrice(dto.getPrice());
        tv.setAvailableSize(dto.getAvailableSize());
        tv.setRefreshRate(dto.getRefreshRate());
        tv.setScreenType(dto.getScreenType());
        tv.setScreenQuality(dto.getScreenQuality());
        tv.setSmartTv(dto.isSmartTv());
        tv.setWifi(dto.isWifi());
        tv.setVoiceControl(dto.isVoiceControl());
        tv.setHdr(dto.isHdr());
        tv.setBluetooth(dto.isBluetooth());
        tv.setAmbiLight(dto.isAmbiLight());
        tv.setOriginalStock(dto.getOriginalStock());
        tv.setSold(dto.getSold());
        return tv;
    }

    default TelevisionDto transferTelevisionToDto(Television tv) {
        TelevisionDto dto = new TelevisionDto();
        dto.setId(tv.getId());
        dto.setType(tv.getType());
        dto.setName(tv.getName());
        dto.setPrice(tv.getPrice());
        dto.setAvailableSize(tv.getAvailableSize());
        dto.setRefreshRate(tv.getRefreshRate());
        dto.setScreenType(tv.getScreenType());
        dto.setScreenQuality(tv.getScreenQuality());
        dto.setSmartTv(tv.isSmartTv());
        dto.setWifi(tv.isWifi());
        dto.setVoiceControl(tv.isVoiceControl());
        dto.setHdr(tv.isHdr());
        dto.setBluetooth(tv.isBluetooth());
        dto.setAmbiLight(tv.isAmbiLight());
        dto.setOriginalStock(tv.getOriginalStock());
        dto.setSold(tv.getSold());
        return dto;
    }
}
