package com.example.teamservice.controller;

import com.example.teamservice.beans.PlayerDTO;
import com.example.teamservice.beans.Team;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamServiceController {

    private RestTemplate restTemplate = new RestTemplate();

    private final List<Team> teams = new ArrayList<>(
            List.of(
                    new Team(1, "Team1"),
                    new Team(2, "Team2"),
                    new Team(3, "Team3")
            )
    );

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved student"),
            @ApiResponse(code = 404, message = "Student not found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")
    })

    @ApiOperation(value = "Get a list of all team", tags = "getAllTeams")
    @GetMapping("/getall")
    public ResponseEntity<List<Team>> getAllTeams() {
        // Implémentez la logique pour récupérer toute la liste des équipes
        return ResponseEntity.ok(teams);
    }

    @ApiOperation(value = "Get team by ID", tags = "getTeamById")
    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<Team> getTeamById(@PathVariable int id) {
        // Implémentez la logique pour récupérer une équipe par son ID depuis la liste
        Team team = teams.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if (team != null) {
            // Appeler le service des joueurs pour récupérer les joueurs de l'équipe
            String playersUrl = "http://localhost:8011/players/playersByTeam/" + id;
            ResponseEntity<List<PlayerDTO>> responseEntity = restTemplate.exchange(
                    playersUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<PlayerDTO>>() {
                    });

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                List<PlayerDTO> teamPlayers = responseEntity.getBody();
                team.setPlayers(teamPlayers);  // Mettre à jour les joueurs de l'équipe
                return ResponseEntity.ok(team);
            } else {
                return ResponseEntity.status(responseEntity.getStatusCode()).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @ApiOperation(value = "Add a new team", tags = "addTeam")
    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        // Implémentez la logique pour ajouter une nouvelle équipe dans la liste
        team.setId((teams.size() + 1)) ;
        teams.add(team);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update team by ID", tags = "updateTeam")
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable int id, @RequestBody Team updatedTeam) {
        // Implémentez la logique pour mettre à jour une équipe existante dans la liste
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            if (team.getId() == id) {
                teams.set(i, updatedTeam);
                updatedTeam.setId(id);
                return ResponseEntity.ok(updatedTeam);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Delete team by ID", tags = "deleteTeam")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int id) {
        // Implémentez la logique pour supprimer une équipe par son ID de la liste
        teams.removeIf(team -> team.getId() == id);
        return ResponseEntity.noContent().build();
    }


    // Méthode de secours renvoyant une chaîne "Fallback method"
    public ResponseEntity<Team> fallbackMethod(@PathVariable int id) {
        // Logique de secours, renvoyer une chaîne "Fallback method"
        return ResponseEntity.ok(new Team(-1, "Fallback method"));
    }
}