package com.devsuperior.project.resources;

import com.devsuperior.project.DTO.ClientDTO;
import com.devsuperior.project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    ClientService service;

    @GetMapping("/{id}")
    private ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
            ClientDTO dto = service.findById(id);
            return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    private ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClientDTO> list = service.findPages(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    private ResponseEntity<ClientDTO> inputClient(@RequestBody ClientDTO client) {
            ClientDTO dto = service.inputClient(client);
            return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
