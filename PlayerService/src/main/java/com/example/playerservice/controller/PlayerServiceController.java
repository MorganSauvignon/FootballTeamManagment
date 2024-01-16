package com.example.playerservice.controller;

import com.example.playerservice.beans.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
public class PlayerServiceController {

    private final List<Player> players = new ArrayList<>(
            List.of(
                    new Player(1, "P1", 1),
                    new Player(2, "P2", 1),
                    new Player(3, "P3", 2)
            )
    );

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved player"),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")
    })

    @ApiOperation(value = "Get player by ID", tags = "getPlayerById")
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
        Player player = players.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Get players by team ID", tags = "getPlayersByTeamId")
    @GetMapping("/playersByTeam/{teamId}")
    public ResponseEntity<List<Player>> getPlayersByTeamId(@PathVariable int teamId) {
        // Implémentez la logique pour récupérer les joueurs de l'équipe avec l'ID donné
        // Vous pouvez remplacer le code ci-dessous par votre logique réelle

        List<Player> teamPlayers = players.stream()
                .filter(player -> player.getTeamId() == teamId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(teamPlayers);
    }

    @ApiOperation(value = "Add a new player", tags = "addPlayer")
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        player.setId(players.size() + 1);
        players.add(player);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update player by ID", tags = "updatePlayer")
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody Player updatedPlayer) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getId() == id) {
                players.set(i, updatedPlayer);
                updatedPlayer.setId(id);
                return ResponseEntity.ok(updatedPlayer);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Delete player by ID", tags = "deletePlayer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        players.removeIf(player -> player.getId() == id);
        return ResponseEntity.noContent().build();
    }
}
