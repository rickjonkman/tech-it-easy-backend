package nl.rix.techiteasy.controllers;

import nl.rix.techiteasy.dtos.RemoteControllerDto;
import nl.rix.techiteasy.models.RemoteController;
import nl.rix.techiteasy.repositories.RemoteControllerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/techiteasy/remotecontrollers")
public class RemoteControllerController {

    private final RemoteControllerRepository remRep;

    public RemoteControllerController(RemoteControllerRepository remRep) {
        this.remRep = remRep;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {
        List<RemoteControllerDto> allRemoteControllerDtos = new ArrayList<>();
        List<RemoteController> allRemoteControllers = remRep.findAll();

        for (RemoteController rc : allRemoteControllers) {
            RemoteControllerDto dto = remRep.transferRemoteControllerToDto(rc);
            allRemoteControllerDtos.add(dto);
        }

        return ResponseEntity.ok(allRemoteControllerDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteController(@PathVariable long id) {
        Optional<RemoteController> remoteControllerOptional = remRep.findById(id);
        RemoteControllerDto dto;

        if (remoteControllerOptional.isPresent()) {
            RemoteController rc = remoteControllerOptional.get();
            dto = remRep.transferRemoteControllerToDto(rc);
        } else {
            // TODO: Exception Handlers
            return null;
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RemoteControllerDto> addRemoteController(@RequestBody RemoteControllerDto remoteControllerDto) {
        RemoteController rc = remRep.transferDtoToRemoteController(remoteControllerDto);
        remRep.save(rc);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + rc.getId())
                .toUriString());

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteController(@RequestBody RemoteControllerDto rcDto, @PathVariable long id) {
        Optional<RemoteController> remoteControllerOptional = remRep.findById(id);
        if (remoteControllerOptional.isPresent()) {
            RemoteController rc = remoteControllerOptional.get();
            rc.setId(rcDto.getId());
            rc.setCompatibleWith(rcDto.getCompatibleWith());
            rc.setBatteryType(rcDto.getBatteryType());
            rc.setName(rcDto.getName());
            rc.setBrand(rcDto.getBrand());
            rc.setPrice(rcDto.getPrice());
            rc.setOriginalStock(rcDto.getOriginalStock());
            remRep.save(rc);
        } else {
            // TODO: Exception Handlers
            return null;
        }

        return ResponseEntity.ok(rcDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> deleteRemoteController(@PathVariable long id) {
        Optional<RemoteController> remoteControllerOptional = remRep.findById(id);
        if (remoteControllerOptional.isPresent()) {
            remRep.deleteById(id);
        } else {
            // TODO: Exception Handlers
            return null;
        }
        return ResponseEntity.noContent().build();
    }

}
