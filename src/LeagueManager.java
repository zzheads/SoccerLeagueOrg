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
        ArrayList<Player> playersList = new ArrayList<Player>();
        ArrayList<Team> teams = new ArrayList<>();

        for (int i=0;i<players.length;i++) {
            playersList.add(players[i]);
        }
        // Print sorted list of players
        playersList.sort(Player::compareTo);
        String format = "%d. %30s ; " +"\t"+"\t"+"height: %d in; "+"\t"+"exp: %s ; "+"\t"+"team: %s %n";
        for (int i=0;i<playersList.size();i++) {
            System.out.printf(format,
                    i,
                    playersList.get(i).getLastName()+" "+playersList.get(i).getFirstName(),
                    playersList.get(i).getHeightInInches(),
                    playersList.get(i).isPreviousExperience(),
                    playersList.get(i).getInTeam());
        }
        // clearScreen();
        int choice = 0;
        while (choice!=7) {
            choice = promptForAction();
            switch (choice) {
                case 1:
                    createTeam();
                    break;
                case 2:
                    addPlayer();
                    break;
                case 3:
                    removePlayer();
                    break;
                case 4:
                    heightReport();
                    break;
                case 5:
                    leagueBalanceReport();
                    break;
                case 6:
                    rosterReport();
                    break;
                case 7:
                    break;
            }
        }
    }


    public static int promptForAction() {
        int choice=0;
        System.out.printf("%n%n Select an action: %n ___________________%n 1. Create team%n 2. Add player to a team%n 3. Remove player from the team 4.View report grouped by height%n 5. View league balance report%n 6. Print roster of the team%n 7. Exit%n");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
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

    public static void createTeam () {

    }

    public static void addPlayer () {

    }

    public static void removePlayer () {

    }

    public static void heightReport () {

    }

    public static void leagueBalanceReport () {

    }

    public static void rosterReport () {

    }

}
