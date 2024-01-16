package com.example.statsservice.controller;

import com.example.statsservice.beans.StatsPlayer;
import com.example.statsservice.beans.StatsTeam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class StatsServiceController {

    private final List<StatsTeam> teamStatsList = new ArrayList<>();
    private final List<StatsPlayer> playerStatsList = new ArrayList<>();

    // Initialisation de quelques statistiques de team
    {
        teamStatsList.add(new StatsTeam(1L, 10, 7, 2, 1, 25, 10));
        teamStatsList.add(new StatsTeam(2L, 12, 8, 3, 1, 30, 12));
        teamStatsList.add(new StatsTeam(3L, 11, 6, 4, 1, 22, 15));
    }

    // Initialisation de quelques statistiques de joueur
    {
        playerStatsList.add(new StatsPlayer(1L, 8, 5, 3, 2, 0));
        playerStatsList.add(new StatsPlayer(2L, 10, 8, 4, 1, 0));
        playerStatsList.add(new StatsPlayer(3L, 9, 3, 6, 0, 1));
    }

    @GetMapping("/team-stats/{teamId}")
    public StatsTeam getTeamStats(@PathVariable Long teamId) {
        return teamStatsList.stream()
                .filter(playerStats -> playerStats.getTeamId().equals(teamId))
                .findFirst()
                .orElse(null);
    }


    @GetMapping("/player-stats/{playerId}")
    public StatsPlayer getPlayerStats(@PathVariable Long playerId) {
        return playerStatsList.stream()
                .filter(playerStats -> playerStats.getPlayerId().equals(playerId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/player-stats")
    public List<StatsPlayer> getPlayerStats() {
        return playerStatsList;
    }
}