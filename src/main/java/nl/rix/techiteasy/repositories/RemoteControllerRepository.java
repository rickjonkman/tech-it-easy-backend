package nl.rix.techiteasy.repositories;

import nl.rix.techiteasy.dtos.RemoteControllerDto;
import nl.rix.techiteasy.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {

    default RemoteControllerDto transferRemoteControllerToDto(RemoteController remoteController) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();
        remoteControllerDto.setCompatibleWith(remoteController.getCompatibleWith());
        remoteControllerDto.setBatteryType(remoteController.getBatteryType());
        remoteControllerDto.setName(remoteController.getName());
        remoteControllerDto.setBrand(remoteController.getBrand());
        remoteControllerDto.setPrice(remoteController.getPrice());
        remoteControllerDto.setOriginalStock(remoteController.getOriginalStock());
        return remoteControllerDto;
    }

    default RemoteController transferDtoToRemoteController(RemoteControllerDto dto) {
        RemoteController remoteController = new RemoteController();
        remoteController.setCompatibleWith(dto.getCompatibleWith());
        remoteController.setBatteryType(dto.getBatteryType());
        remoteController.setName(dto.getName());
        remoteController.setBrand(dto.getBrand());
        remoteController.setPrice(dto.getPrice());
        remoteController.setOriginalStock(dto.getOriginalStock());
        return remoteController;
    }
}
