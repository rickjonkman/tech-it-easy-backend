package nl.rix.techiteasy.controllers;

import nl.rix.techiteasy.dtos.WallBracketDto;
import nl.rix.techiteasy.models.WallBracket;
import nl.rix.techiteasy.repositories.WallBracketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/techiteasy/wallbrackets")
public class WallBracketController {

    private final WallBracketRepository wbRepos;

    public WallBracketController(WallBracketRepository wbRepos) {
        this.wbRepos = wbRepos;
    }

    @GetMapping
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {
        List<WallBracket> allWallBrackets = wbRepos.findAll();
        List<WallBracketDto> allWallBracketDtos = new ArrayList<>();

        for (WallBracket wb : allWallBrackets) {
            WallBracketDto wbDto = wbRepos.transferWallBracketToDto(wb);
            allWallBracketDtos.add(wbDto);
        }

        return ResponseEntity.ok(allWallBracketDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable long id) {
        Optional<WallBracket> wallBracketOptional = wbRepos.findById(id);
        WallBracketDto dto = new WallBracketDto();

        if (wallBracketOptional.isPresent()) {
            WallBracket wb = wallBracketOptional.get();
            dto = wbRepos.transferWallBracketToDto(wb);
        }

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<WallBracketDto> addWallBracket(@RequestBody WallBracketDto wallBracketDto) {
        WallBracket wb = wbRepos.transferDtoToWallBracket(wallBracketDto);
        wbRepos.save(wb);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/"+wb.getId())
                .toUriString());

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@RequestBody WallBracketDto dto, @PathVariable long id) {
        Optional<WallBracket> wallBracketOptional = wbRepos.findById(id);
        WallBracketDto wallBracketDto = new WallBracketDto();

        if (wallBracketOptional.isPresent()) {
            WallBracket wb = wallBracketOptional.get();
            wb.setId(dto.getId());
            wb.setName(dto.getName());
            wb.setType(dto.getType());
            wb.setPrice(dto.getPrice());
            wbRepos.save(wb);
            wallBracketDto = wbRepos.transferWallBracketToDto(wb);
        } else {
            // TODO: exception handlers
            return null;
        }

        return ResponseEntity.ok(wallBracketDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WallBracketDto> deleteWallBracket(@PathVariable long id) {
        Optional<WallBracket> wallBracketOptional = wbRepos.findById(id);

        if (wallBracketOptional.isPresent()) {
            wbRepos.deleteById(id);
        } else {
            // TODO: exception Handlers
            return null;
        }

        return ResponseEntity.noContent().build();
    }
}
