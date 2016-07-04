import com.zzheads.model.Player;
import com.zzheads.model.Players;
import com.zzheads.model.Team;

import java.util.ArrayList;
import java.util.Scanner;

import static com.zzheads.model.Players.howManyDiffHeightsInTeam;

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
        teams.add(0, new Team("Montreal Canadiens","Brett Hull")); // added just for debugging
        teams.add(1, new Team("CSKA","Victor Tikhonov"));
        teams.add(2, new Team("Pittsburg Penguins","Mario Lemeux"));

        // Print sorted list of players
        playersReport(playersList);
        // clearScreen();
        int choice = 0;
        while (choice!=8) {
            choice = promptForAction();
            switch (choice) {
                case 1:
                    if (Players.countFree(playersList)>0) {
                        Team newTeam = createTeam();
                        teams.add(newTeam);
                    } else {
                        System.out.printf("%n%nError. There is no free players, you can't add more teams.");
                    }
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
                    autoBuildTeams(teams, playersList);
                    break;
                case 8:
                    break;
            }
        }
    }

    public static int promptForAction() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int choice=0;
        System.out.printf("%n%n Select an action: %n ___________________%n 1. Create team%n 2. Add player to a team%n 3. Remove player from the team%n 4. View report grouped by height%n 5. View league balance report%n 6. Print roster of the team%n 7. Auto build teams%n 8. Exit%n");
        while (choice<1 || choice>8) {
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
        System.out.printf("   Name:                      Height(inches):     Exp:           Team:%n------------------------------------------------------------------------------------%n");
        String format = "%d. %-20s  " +"\t"+"\t"+" %d           "+"\t"+" %s "+"\t"+" %s %n";
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

        System.out.printf("%n%nTeam: %s | Coach: %s%n---------------------------------------%n",team.getTeamName(),team.getCoach());
        System.out.printf("Height(inches):     Player name:%n");
        String format = "%3d             %s %n";
        for (int i=0;i<sorted.size();i++) {
            System.out.printf(format, sorted.get(i).getHeightInInches(), sorted.get(i).getLastName()+" "+sorted.get(i).getFirstName());
        }
    }

    public static void leagueBalanceReport (ArrayList<Team> teams) {
        String heightsResult = "";
        int expLevel=0;
        System.out.printf("%n%n    Team:                        Exp - Unexp players   ExpLevel     Height - players%n-------------------------------------------------------------------------------------------------- %n");
        for (int i=0;i<teams.size();i++) {
            heightsResult = "%d. %-20s              %d - %d            %3d   "+howManyDiffHeightsInTeam(teams.get(i))+"%n";
            if (teams.get(i).getPlayers().size()>0) {
                expLevel = teams.get(i).expCount()*100/teams.get(i).getPlayers().size();
            } else {
                expLevel = 0;
            }
            System.out.printf(heightsResult,
                    i+1,
                    teams.get(i).getTeamName(),
                    teams.get(i).expCount(),
                    teams.get(i).unexpCount(),
                    expLevel);
        }
    }

    public static void autoBuildTeams (ArrayList<Team> teams, ArrayList<Player> players) {
        int countTeams = teams.size();
        int countPlayers = players.size();
        int averageAddEachTeam = Math.round(countPlayers/countTeams);
        players.sort(Player::compareByImportance);
        for (int i=0;i<players.size();) {
            for (int j=0;j<teams.size();j++) {
                if (teams.get(j).getPlayers().size()<11) {
                    teams.get(j).add(players.get(i));
                    i++;
                }
            }
        }

    }

    public static void rosterReport (Team team) {
        System.out.printf("%n%n    Team: %s   Coach: %s%n----------------------------------------%n",team.getTeamName(),team.getCoach());
        playersReport(team.getPlayers());
    }

}
