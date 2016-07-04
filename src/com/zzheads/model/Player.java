package com.zzheads.model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private int heightInInches;
    private boolean previousExperience;
    private String inTeam;
    private int mImportance;

    public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightInInches = heightInInches;
        this.previousExperience = previousExperience;
        this.inTeam = ""; // our Player is not in any team
        // maximum difference in heights between players is 10 inches, so weight of previous experience must be 10 also...
        if (previousExperience) {
            this.mImportance = (heightInInches + 10) / 20;
        } else {
            this.mImportance = (heightInInches + 0) /20;
        }
    }

    public String getInTeam() {
        return inTeam;
    }

    public void setInTeam(String inTeam) {
        this.inTeam = inTeam;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHeightInInches(int heightInInches) {
        this.heightInInches = heightInInches;
    }

    public void setPreviousExperience(boolean previousExperience) {
        this.previousExperience = previousExperience;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getHeightInInches() {
        return heightInInches;
    }

    public boolean isPreviousExperience() {
        return previousExperience;
    }

    public int getImportance() {
        return mImportance;
    }

    public void setImportance(int importance) {
        mImportance = importance;
    }

    @Override
    public int compareTo(Player other) {
        // We always want to sort by last name then first name
        String name_1 = getLastName()+getFirstName();
        String name_2 = other.getLastName()+other.getFirstName();
        return name_1.compareTo(name_2);
    }

    public  int compareByHeight (Player other) {
        if (getHeightInInches() > other.getHeightInInches()) {
            return 1;
        }
        if (getHeightInInches() < other.getHeightInInches()) {
            return -1;
        }
        return 0;
    }

    public  int compareByImportance (Player other) {
        if (getImportance() > other.getImportance()) {
            return 1;
        }
        if (getImportance() < other.getImportance()) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (heightInInches != player.heightInInches) return false;
        if (previousExperience != player.previousExperience) return false;
        if (!firstName.equals(player.firstName)) return false;
        return lastName.equals(player.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + heightInInches;
        result = 31 * result + (previousExperience ? 1 : 0);
        return result;
    }

}