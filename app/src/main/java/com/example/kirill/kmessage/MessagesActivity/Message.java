package com.example.kirill.kmessage.MessagesActivity;

import java.util.Date;

/**
 * Created on 23.01.2016.
 * @author Rakov Kirill
 */

public class Message {
    private int avatarId;
    private String senderName;
    private String lastMessage;
    private Date messageDate;
    public Message(int avatarId, String lastMessage, Date messageDate, String senderName) {
        this.avatarId = avatarId;
        this.lastMessage = lastMessage;
        this.messageDate = messageDate;
        this.senderName = senderName;
    }

    public int getAvatarId() {
        return avatarId;
    }
    public String getLastMessage() {
        return lastMessage;
    }
    public Date getMessageDate() {
        return messageDate;
    }
    public String getSenderName() {
        return senderName;
    }
}
