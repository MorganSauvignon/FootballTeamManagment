package com.example.matchservice.controller;

import com.example.matchservice.beans.Match;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchServiceController {

    private final List<Match> matches = new ArrayList<>(
            List.of(
                    new Match(1, "OGC", "OM", "1-0"),
                    new Match(2, "PSG", "FCB", "2-1"),
                    new Match(3, "OL", "RMA", "0-0")
            )
    );

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved match"),
            @ApiResponse(code = 404, message = "Match not found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")
    })


    @ApiOperation(value = "Get match by ID", tags = "getMatchById")
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable int id) {
        Match match = findMatchById(id);
        if (match != null) {
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Add a new match", tags = "addMatch")
    @PostMapping
    public ResponseEntity<Match> addMatch(@RequestBody Match match) {
        match.setId(generateNextId());
        matches.add(match);
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update match by ID", tags = "updateMatch")
    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable int id, @RequestBody Match updatedMatch) {
        Match existingMatch = findMatchById(id);
        if (existingMatch != null) {
            existingMatch.setTeam1(updatedMatch.getTeam1());
            existingMatch.setTeam2(updatedMatch.getTeam2());
            existingMatch.setResult(updatedMatch.getResult());
            return ResponseEntity.ok(existingMatch);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Delete match by ID", tags = "deleteMatch")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable int id) {
        Match matchToRemove = findMatchById(id);
        if (matchToRemove != null) {
            matches.remove(matchToRemove);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Méthodes auxiliaires

    private Match findMatchById(int id) {
        return matches.stream()
                .filter(match -> match.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private int generateNextId() {
        return matches.isEmpty() ? 1 : matches.get(matches.size() - 1).getId() + 1;
    }
}
