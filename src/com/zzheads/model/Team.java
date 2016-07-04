package com.zzheads.model;

import java.util.ArrayList;

/**
 * Created by zzhea on 04.07.2016.
 */
public class Team {
    String mTeamName;
    String mCoach;
    private ArrayList<Player> mPlayers;

    public Team(String teamName, String coach) {
        mTeamName = teamName;
        mCoach = coach;
        mPlayers = new ArrayList<>();
    }

    public String getTeamName() {
        return mTeamName;
    }

    public void setTeamName(String teamName) {
        mTeamName = teamName;
    }

    public String getCoach() {
        return mCoach;
    }

    public void setCoach(String coach) {
        mCoach = coach;
    }

    public ArrayList<Player> getPlayers() {
        return mPlayers;
    }

    public void setPlayers(ArrayList<Player> players) {
        mPlayers = players;
    }

    public void Team (String name, String coach) {
        mTeamName = name;
        mCoach = coach;
        mPlayers = new ArrayList<Player>();
    }

    public void add (Player player) {
        if (player.getInTeam()=="") {
            mPlayers.add(player);
        } else { // player can not be added, he is already in team
            System.out.printf("Error. Can't add %s to  %s team, he is already in %s team.", player.getFirstName()+" "+player.getLastName(), mTeamName, player.getInTeam());
        }
    }

    public void remove (Player player) {
        if (mPlayers.contains(player)) { // Yes, such player in our team
            mPlayers.remove(player);     // delete him from team
            player.setInTeam("");
        } else {
            System.out.printf("Error. Can't remove %s from %s team, there is no such player in that team.", player.getFirstName() + " " + player.getLastName(), mTeamName);
        }
    }
}
