import com.zzheads.model.Player;
import com.zzheads.model.Players;

public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.printf("There are currently %d registered players.%n", players.length);
        // Your code here!
    }

}
