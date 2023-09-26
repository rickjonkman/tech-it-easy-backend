package nl.rix.techiteasy.controllers;

import nl.rix.techiteasy.dtos.TelevisionDto;
import nl.rix.techiteasy.models.Television;
import nl.rix.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/techiteasy/televisions")
public class TelevisionController {

    private final TelevisionRepository tvRepos;

    public TelevisionController(TelevisionRepository tvRepos) {
        this.tvRepos = tvRepos;

    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable long id) {
        Television tv;
        Optional<Television> televisionOptional = tvRepos.findById(id);
        if (televisionOptional.isPresent()) {
            tv = televisionOptional.get();
        } else {
            // TODO: Exception Handlers toevoegen
            return null;
        }

        TelevisionDto dto = tvRepos.transferTelevisionToDto(tv);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        List<Television> allTelevisions = tvRepos.findAll();
        List<TelevisionDto> allTelevisionDtos = new ArrayList<>();

        for (Television tv : allTelevisions) {
            TelevisionDto tvDto = tvRepos.transferTelevisionToDto(tv);
            allTelevisionDtos.add(tvDto);
        }

        return ResponseEntity.ok(allTelevisionDtos);
    }

    @PostMapping
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody TelevisionDto televisionDto) {
        Television tv = tvRepos.transferDtoToTelevision(televisionDto);
        tvRepos.save(tv);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + tv.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(televisionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable long id, @RequestBody TelevisionDto televisionDto) {
        Optional<Television> televisionOptional = tvRepos.findById(id);

        if (televisionOptional.isPresent()) {
            Television tvFound = televisionOptional.get();
            tvRepos.save(tvFound);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TelevisionDto> deleteTelevision(@PathVariable long id) {
        Optional<Television> televisionOptional = tvRepos.findById(id);

        if (televisionOptional.isPresent()) {
            tvRepos.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }
}
