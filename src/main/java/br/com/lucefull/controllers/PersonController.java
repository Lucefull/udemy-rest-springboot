package br.com.lucefull.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucefull.data.vo.v1.PersonVO;
import br.com.lucefull.data.vo.v2.PersonVOV2;
import br.com.lucefull.services.PersonService;
import br.com.lucefull.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

//@CrossOrigin
@RestController
@RequestMapping("/api/person")
@Tag(name = "People", description = "Endpoint to manage people")
public class PersonController {
        @Autowired
        private PersonService personService;

        @CrossOrigin(origins = "http://localhost:9000")
        @GetMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML })
        @Operation(summary = "Find all peoples", description = "Find all peoples", tags = { "People" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))

                        }),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
        })

        public List<PersonVO> findByAll() throws Exception {
                return personService.findAll();
        }

        @GetMapping(value = "/v1/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML })
        @Operation(summary = "Find a people", description = "Find a people", tags = { "People" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
        })
        public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
                return personService.findById(id);
        }

        @CrossOrigin(origins = { "http://localhost:9000", "https://lucefull.com" })
        @PostMapping(value = "/v1", consumes = {
                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML }, produces = {
                                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                                        MediaType.APPLICATION_YAML })

        @Operation(summary = "Find a people", description = "Find a people", tags = { "People" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
        })
        public PersonVO create(@RequestBody PersonVO person) throws Exception {
                return personService.create(person);
        }

        @PostMapping(value = "/v2", consumes = {
                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML }, produces = {
                                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                                        MediaType.APPLICATION_YAML })
        public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
                return personService.createV2(person);
        }

        @PutMapping(value = "/v1", consumes = {
                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML }, produces = {
                                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                                        MediaType.APPLICATION_YAML })

        @Operation(summary = "Update a people", description = "Update a people", tags = { "People" }, responses = {
                        @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
        })
        public PersonVO update(@RequestBody PersonVO person) throws Exception {
                return personService.update(person);
        }

        @PutMapping(value = "/v2", consumes = {
                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML }, produces = {
                                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                                        MediaType.APPLICATION_YAML })
        public PersonVOV2 update(@RequestBody PersonVOV2 person) {
                return personService.updateV2(person);
        }

        @DeleteMapping(value = "/v1/{id}", consumes = {
                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML }, produces = {
                                        MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                                        MediaType.APPLICATION_YAML })

        @Operation(summary = "Delete a person", description = "Delete a people", tags = { "People" }, responses = {
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Server Error", responseCode = "500", content = @Content),
        })
        public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
                personService.delete(id);
                return ResponseEntity.noContent().build();
        }

}
