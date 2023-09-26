package nl.rix.techiteasy.controllers;

import nl.rix.techiteasy.dtos.CiModuleDto;
import nl.rix.techiteasy.models.CiModule;
import nl.rix.techiteasy.repositories.CiModuleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/techiteasy/cimodules")
public class CiModulesController {

    private final CiModuleRepository ciRepos;

    public CiModulesController(CiModuleRepository ciRepos) {
        this.ciRepos = ciRepos;
    }

    @GetMapping
    public ResponseEntity<List<CiModuleDto>> getAllCiModules() {
        List<CiModuleDto> allCiModuleDtos = new ArrayList<>();
        List<CiModule> allCiModules = ciRepos.findAll();

        for (CiModule ci : allCiModules) {
            CiModuleDto ciDto = ciRepos.transferCiModuleToDto(ci);
            allCiModuleDtos.add(ciDto);
        }

        return ResponseEntity.ok().body(allCiModuleDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiModuleDto> getCiModule(@PathVariable long id) {
        Optional<CiModule> ciModuleOptional = ciRepos.findById(id);
        CiModuleDto dto;
        if (ciModuleOptional.isPresent()) {
            CiModule ci = ciModuleOptional.get();
            dto = ciRepos.transferCiModuleToDto(ci);
        } else {
            //TODO: exception handlers
            return null;
        }
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CiModuleDto> addCiModule(@RequestBody CiModuleDto ciModuleDto) {
        CiModule ciModule = ciRepos.transferDtoToCiModule(ciModuleDto);
        ciRepos.save(ciModule);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + ciModule.getId())
                .toUriString());

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CiModuleDto> updateCiModule(@RequestBody CiModuleDto ciModuleDto, @PathVariable long id) {
        Optional<CiModule> ciModuleOptional = ciRepos.findById(id);
        CiModuleDto dto;

        if (ciModuleOptional.isPresent()) {
            CiModule ci = ciModuleOptional.get();
            ci.setId(ciModuleDto.getId());
            ci.setName(ciModuleDto.getName());
            ci.setType(ciModuleDto.getType());
            ci.setPrice(ciModuleDto.getPrice());
            ciRepos.save(ci);

            dto = ciRepos.transferCiModuleToDto(ci);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CiModuleDto> deleteCiModule(@PathVariable long id) {
        Optional<CiModule> ciModuleOptional = ciRepos.findById(id);
        if (ciModuleOptional.isPresent()) {
            ciRepos.deleteById(id);
        } else {
            // TODO: exception handlers
            return null;
        }
        return ResponseEntity.noContent().build();
    }
}
