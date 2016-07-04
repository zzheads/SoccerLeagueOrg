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

    public int compareTo (Team other) {
        return (this.getTeamName().compareTo(other.getTeamName()));
    }

    public int expCount () {
        int count = 0;
        for (int i=0;i<getPlayers().size();i++) {
            if (getPlayers().get(i).isPreviousExperience()) {
                count++;
            }
        }
        return count;
    }

    public int unexpCount () {
        int count = 0;
        for (int i=0;i<getPlayers().size();i++) {
            if (!getPlayers().get(i).isPreviousExperience()) {
                count++;
            }
        }
        return count;
    }

    public boolean add (Player player) {
        if (!(player.getInTeam()=="")) {
            System.out.printf("Error. Can't add %s to  %s team, he is already in %s team.", player.getFirstName() + " " + player.getLastName(), mTeamName, player.getInTeam());
            return false;
        }
        if (getPlayers().size()>=11) {
            System.out.printf("Error. Can't add %s to  %s, that team is full.", player.getFirstName() + " " + player.getLastName(), mTeamName, player.getInTeam());
            return false;
        }
        mPlayers.add(player);
        player.setInTeam(getTeamName());
        return true;
    }

    public boolean remove (Player player) {
        if (mPlayers.contains(player)) { // Yes, such player in our team
            mPlayers.remove(player);     // delete him from team
            player.setInTeam("");
            return true;
        } else {
            System.out.printf("Error. Can't remove %s from %s team, there is no such player in that team.", player.getFirstName() + " " + player.getLastName(), mTeamName);
            return false;
        }
    }
}
