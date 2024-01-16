package com.example.playerservice.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    private int id;
    private String name;
    private int teamId; // L'ID de l'équipe à laquelle le joueur appartient

    // Constructeurs, getters et setters

    // Ajoutez le constructeur par défaut si nécessaire
    public Player(int id, String name, int teamId) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;

    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("teamId")
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

}
