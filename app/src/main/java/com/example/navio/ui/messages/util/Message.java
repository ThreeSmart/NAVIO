package com.example.navio.ui.messages.util;

public class Message {
    private long id;
    private String content;
    private long fromUserId;
    private long toUserId;
    private String sentTime;
    private boolean seen;

    public Message(final long id, final String content, final long fromUserId, final long toUserId, final String sentTime, final boolean seen) {
        this.id = id;
        this.content = content;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.sentTime = sentTime;
        this.seen = seen;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(final long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(final long toUserId) {
        this.toUserId = toUserId;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(final String sentTime) {
        this.sentTime = sentTime;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(final boolean seen) {
        this.seen = seen;
    }
}