# FootballTeamManagment

## Ce qui a été fait 
### ✔️ Partie 1 : Service d'Équipes 
### ✔️ Partie 2 : Service de Joueurs 
### ✔️ Partie 3 : Service de Matchs 
### ✔️ Partie 4 : Service de Statistiques 
![image](https://github.com/MorganSauvignon/FootballTeamManagment/assets/70762614/b3fcd1ee-7e9f-4c80-a0d6-09391fddf520)
- Tous les services sont enregistrés. 

### ✔️ Partie 5 : Documentation Swagger
![image](https://github.com/MorganSauvignon/FootballTeamManagment/assets/70762614/c18a4511-e5fc-42f5-a143-9842661719cd)
- Un exemple de la documentation Swagger mise en place sur les différents microservices. Ici sur PlayerService. 

### ✔️ Partie 6 : Tolérance aux Pannes
![image](https://github.com/MorganSauvignon/FootballTeamManagment/assets/70762614/1cc03bca-52b2-4c64-b056-2b7764c9a67e)
- Tolérance aux pannes mise en place sur la méthode getTeamById.
![image](https://github.com/MorganSauvignon/FootballTeamManagment/assets/70762614/91236afd-c137-4d88-bab4-ecd67c7f7d31)

### ✔️ Partie 7 : Monitoring
- Monitoring mise en place sur tous les microservices.

- 
### ✔️ Partie 8 : Load Balancing
![image](https://github.com/MorganSauvignon/FootballTeamManagment/assets/70762614/024cdfc3-d009-4073-8b66-253438d4ed07)
- Mise en place du load balancing et de la possibilité de lancer plusieurs instances de MatchService.

## Microservices de Gestion Football

### Service d'Équipes (TeamService)

- Récupérer les détails d'une équipe
  - **Endpoint:** GET /teams/{id}
  - **Description:** Renvoie les détails d'une équipe spécifiée par son identifiant.

- Ajouter une nouvelle équipe
  - **Endpoint:** POST /teams
  - **Description:** Permet d'ajouter une nouvelle équipe à la liste des équipes.

- Mettre à jour les informations d'une équipe existante
  - **Endpoint:** PUT /teams/{id}
  - **Description:** Met à jour les informations d'une équipe existante en fonction de son identifiant.

- Supprimer une équipe par son identifiant
  - **Endpoint:** DELETE /teams/{id}
  - **Description:** Supprime une équipe spécifiée par son identifiant.

### Service de Joueurs (PlayerService)

- Récupérer les détails d'un joueur
  - **Endpoint:** GET /players/{id}
  - **Description:** Renvoie les détails d'un joueur spécifié par son identifiant.

- Ajouter un nouveau joueur
  - **Endpoint:** POST /players
  - **Description:** Permet d'ajouter un nouveau joueur à la liste des joueurs.

- Mettre à jour les informations d'un joueur existant
  - **Endpoint:** PUT /players/{id}
  - **Description:** Met à jour les informations d'un joueur existant en fonction de son identifiant.

- Supprimer un joueur par son identifiant
  - **Endpoint:** DELETE /players/{id}
  - **Description:** Supprime un joueur spécifié par son identifiant.

### Service de Matchs (MatchService)

- Récupérer les détails d'un match
  - **Endpoint:** GET /matches/{id}
  - **Description:** Renvoie les détails d'un match spécifié par son identifiant.

- Ajouter un nouveau match
  - **Endpoint:** POST /matches
  - **Description:** Permet d'ajouter un nouveau match à la liste des matchs.

- Mettre à jour les informations d'un match existant
  - **Endpoint:** PUT /matches/{id}
  - **Description:** Met à jour les informations d'un match existant en fonction de son identifiant.

- Supprimer un match par son identifiant
  - **Endpoint:** DELETE /matches/{id}
  - **Description:** Supprime un match spécifié par son identifiant.

### Service de Statistiques (StatsService)

- Récupérer les statistiques d'une équipe pour la saison
  - **Endpoint:** GET /team-stats/{teamId}
  - **Description:** Renvoie les statistiques d'une équipe pour la saison spécifiée par son identifiant.

- Récupérer les statistiques d'un joueur pour la saison
  - **Endpoint:** GET /player-stats/{playerId}
  - **Description:** Renvoie les statistiques d'un joueur pour la saison spécifiée par son identifiant.
