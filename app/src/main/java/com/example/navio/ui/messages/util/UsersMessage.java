package com.example.navio.ui.messages.util;

public class UsersMessage {
    private String name, lastMsg, msgTime;
    private int userImgResource;
    private int userStatus;
    private int seenStatus;

    public UsersMessage(String name, String lastMsg, String msgTime, int userImgResource, int userStatus, int seenStatus) {
        this.name = name;
        this.lastMsg = lastMsg;
        this.msgTime = msgTime;
        this.userImgResource = userImgResource;
        this.userStatus = userStatus;
        this.seenStatus = seenStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public int getUserImgResource() {
        return userImgResource;
    }

    public void setUserImgResource(int userImgResource) {
        this.userImgResource = userImgResource;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getSeenStatus() {
        return seenStatus;
    }

    public void setSeenStatus(int seenStatus) {
        this.seenStatus = seenStatus;
    }
}
