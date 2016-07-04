import com.zzheads.model.Player;
import com.zzheads.model.Players;
import com.zzheads.model.Team;

import java.util.ArrayList;
import java.util.Scanner;

public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.printf("There are currently %d registered players.%n", players.length);

        // Your code here!
        ArrayList<Player> playersList = new ArrayList<>();
        for (int i=0;i<players.length;i++) {
            playersList.add(players[i]);
        }
        playersList.sort(Player::compareTo);

        ArrayList<Team> teams = new ArrayList<>();
        teams.add(0, new Team("Montreal Canadiens","Brett Hull"));
        teams.add(1, new Team("CSKA","Victor Tikhonov"));
        teams.add(2, new Team("Pittsburg Penguins","Mario Lemeux"));

        // Print sorted list of players
        playersReport(playersList);
        // clearScreen();
        int choice = 0;
        while (choice!=7) {
            choice = promptForAction();
            switch (choice) {
                case 1:
                    Team newTeam = createTeam();
                    teams.add(newTeam);
                    break;
                case 2:
                    addPlayer(teams, playersList);
                    break;
                case 3:
                    removePlayer(teams, playersList);
                    break;
                case 4:
                    heightReport(teams.get(chooseTeam(teams)));
                    break;
                case 5:
                    leagueBalanceReport(teams);
                    break;
                case 6:
                    rosterReport(teams.get(chooseTeam(teams)));
                    break;
                case 7:
                    break;
            }
        }
    }

    public static int promptForAction() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int choice=0;
        System.out.printf("%n%n Select an action: %n ___________________%n 1. Create team%n 2. Add player to a team%n 3. Remove player from the team%n 4. View report grouped by height%n 5. View league balance report%n 6. Print roster of the team%n 7. Exit%n");
        while (choice<1 || choice>7) {
            System.out.printf(" ___________________%n%nEnter your choice: ");
            choice = reader.nextInt();
        }
        return choice;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int chooseTeam (ArrayList<Team> teams) {
        int teamNumber = 0;
        teams.sort(Team::compareTo);
        System.out.printf("%n%n");
        for (int i=0;i<teams.size();i++) {
            System.out.printf("%d. %s             (%d players)%n", i+1, teams.get(i).getTeamName(), teams.get(i).getPlayers().size());
        }
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.printf("%n________________________%nEnter the number of team: %n");
        while (teamNumber<1 || teamNumber>teams.size()) {
            teamNumber = reader.nextInt();
        }
        return teamNumber-1;
    }

    public static int choosePlayer (ArrayList<Player>players) {
        int playerNumber = 0;
        playersReport (players);
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.printf("%n________________________%nEnter the number of player: %n");
        while (playerNumber<1 || playerNumber>players.size()) {
            playerNumber = reader.nextInt();
        }
        return playerNumber-1;
    }

    public static Team createTeam () {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.printf("%nEnter the name of new team: %n");
        String newName = reader.nextLine();
        System.out.printf("Enter the coach name of new team: %n ");
        String newCoach = reader.nextLine();
        return (new Team(newName,newCoach));
    }

    public static void addPlayer (ArrayList<Team>teams,ArrayList<Player>players) {
        int team = 0, player = 0;
        boolean  success = false;
        while (!success) {
            team = chooseTeam(teams);
            player = choosePlayer(players);
            success = teams.get(team).add(players.get(player));
        }
    }

    public static void removePlayer (ArrayList<Team>teams,ArrayList<Player>players) {
        int team = 0, player = 0;
        boolean  success = false;
        while (!success) {
            team = chooseTeam(teams);
            player = choosePlayer(players);
            success = teams.get(team).remove(players.get(player));
        }
    }

    public static void playersReport (ArrayList<Player>playersList) {
        String format = "%d. %30s ; " +"\t"+"\t"+"height: %d in; "+"\t"+"exp: %s ; "+"\t"+"team: %s %n";
        for (int i=0;i<playersList.size();i++) {
            System.out.printf(format,
                    i+1,
                    playersList.get(i).getLastName()+" "+playersList.get(i).getFirstName(),
                    playersList.get(i).getHeightInInches(),
                    playersList.get(i).isPreviousExperience(),
                    playersList.get(i).getInTeam());
        }
    }

    public static void heightReport (Team team) {
        ArrayList <Player> sorted = team.getPlayers();
        sorted.sort(Player::compareByHeight);

        System.out.printf("%n%nTeam: %s | Coach: %s%n----------------------%n%n",team.getTeamName(),team.getCoach());
        System.out.printf("Height:     Player name:%n");
        String format = "%3d in     %s %n";
        for (int i=0;i<sorted.size();i++) {
            System.out.printf(format, sorted.get(i).getHeightInInches(), sorted.get(i).getLastName()+" "+sorted.get(i).getFirstName());
        }
    }


    public static void leagueBalanceReport (ArrayList<Team> teams) {
        System.out.printf("%n%n    Team:                  Experienced players - Unexperienced players%n----------------------------------------%n");
        for (int i=0;i<teams.size();i++) {
            System.out.printf("%d. %s                                   %d - %d%n",i+1,teams.get(i).getTeamName(),teams.get(i).expCount(),teams.get(i).unexpCount());
        }
    }

    public static void rosterReport (Team team) {
        System.out.printf("%n%n    Team: %s   Coach: %s%n----------------------------------------%n",team.getTeamName(),team.getCoach());
        playersReport(team.getPlayers());
    }

}
