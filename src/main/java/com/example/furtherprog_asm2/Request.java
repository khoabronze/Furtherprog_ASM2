package com.example.furtherprog_asm2;

public class Request {
    // Attributes
    private String rid;
    private String id;
    private String note;
    private Approval approval;

    // Default Constructor
    public Request() {}

    // Constructor
    public Request(String rid, String id, String note) {
        this.rid = rid;
        this.id = id;
        this.note = note;
        this.approval = Approval.PENDING; // Set approval to PENDING by default
    }

    // Getters and Setters
    public String getRid() {
        return rid;
    }
    public void setRid(String rid) {
        this.rid = rid;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Approval getApproval() {
        return approval;
    }

    public void setApproval(Approval approval) {
        this.approval = approval;
    }

     //Override toString method
    @Override
    public String toString() {
        return "Request{" +
                "rid='" + rid + '\'' +
                ", id='" + id + '\'' +
                ", note='" + note + '\'' +
                ", approval=" + approval +
                '}';
    }
}
