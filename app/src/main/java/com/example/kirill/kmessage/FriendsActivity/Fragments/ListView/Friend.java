package com.example.kirill.kmessage.FriendsActivity.Fragments.ListView;

/**
 * Created on 05.02.2016.
 * @author Rakov Kirill
 */

public class Friend {
    private int avatarId;
    private String FIO;
    private boolean isOnLine;
    public Friend(int avatarId, String firstName, boolean isOnLine) {
        this.avatarId = avatarId;
        this.FIO = firstName;
        this.isOnLine = isOnLine;
    }

    public int getAvatarId() {
        return avatarId;
    }
    public String getFIO() {
        return FIO;
    }
    public boolean isOnLine() {
        return isOnLine;
    }
}
