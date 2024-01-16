package com.example.matchservice.beans;

public class Match {
    private int id;
    private String team1;
    private String team2;
    private String result;

    // Constructeur avec paramètres
    public Match(int id, String team1, String team2, String result) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
