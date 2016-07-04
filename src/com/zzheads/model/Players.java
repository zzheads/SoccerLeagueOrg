package com.zzheads.model;

import java.util.*;

public class Players {

    public static Player[] load() {
        return new Player[] {
                new Player("Joe", "Smith", 42, true),
                new Player("Jill", "Tanner", 36, true),
                new Player("Bill", "Bon", 43, true),
                new Player("Eva", "Gordon", 45, false),
                new Player("Matt", "Gill", 40, false),
                new Player("Kimmy", "Stein", 41, false),
                new Player("Sammy", "Adams", 45, false),
                new Player("Karl", "Saygan", 42, true),
                new Player("Suzane", "Greenberg", 44, true),
                new Player("Sal", "Dali", 41, false),
                new Player("Joe", "Kavalier", 39, false),
                new Player("Ben", "Finkelstein", 44, false),
                new Player("Diego", "Soto", 41, true),
                new Player("Chloe", "Alaska", 47, false),
                new Player("Arfalseld", "Willis", 43, false),
                new Player("Phillip", "Helm", 44, true),
                new Player("Les", "Clay", 42, true),
                new Player("Herschel", "Krustofski", 45, true),
                new Player("Andrew", "Chalklerz", 42, true),
                new Player("Pasan", "Membrane", 36, true),
                new Player("Kenny", "Lovins", 35, true),
                new Player("Alena", "Sketchings", 45, false),
                new Player("Carling", "Seacharpet", 40, false),
                new Player("Joseph", "Freely", 41, false),
                new Player("Gabe", "Listmaker", 45, false),
                new Player("Jeremy", "Smith", 42, true),
                new Player("Ben", "Droid", 44, true),
                new Player("James", "Dothnette", 41, false),
                new Player("Nick", "Grande", 39, false),
                new Player("Will", "Guyam", 44, false),
                new Player("Jason", "Seaver", 41, true),
                new Player("Johnny", "Thunder", 47, false),
                new Player("Ryan", "Creedson", 43, false)
        };
    }

    public static int countFree (ArrayList<Player> players) {
        int count = 0;
        for (int i=0;i<players.size();i++) {
            if (players.get(i).getInTeam().equals("")) {
              count++;
            }
        }
        return count;
    }

    public static ArrayList<Integer> heightsIn (Team team) { // return array of heights of players in team
        ArrayList<Integer> heights = new ArrayList<>();
        for (int i=0;i<team.getPlayers().size();i++) {
            if (!heights.contains(team.getPlayers().get(i).getHeightInInches())) {
                heights.add(team.getPlayers().get(i).getHeightInInches());
            }
        }
        return heights;
    }

    public static String howManyDiffHeightsInTeam (Team team) { // String in format:   Height - XX players | Height - XX players | ...
        String result="         ";
        int curH=0, count=0;
        team.getPlayers().sort(Player::compareByHeight);
        for (int i=0;i<team.getPlayers().size();i++) {
            if (curH>0 && curH!=team.getPlayers().get(i).getHeightInInches()) {
                result = result +curH+" - "+count+" | ";
                count=0;
            }
            curH = team.getPlayers().get(i).getHeightInInches();
            count++;
            if (i==(team.getPlayers().size()-1)) {
                result = result +curH+" - "+count+" |";
            }
        }
        return result;
    }
}