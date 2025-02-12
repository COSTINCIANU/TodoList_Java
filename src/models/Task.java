package models;

import java.sql.Timestamp;

public class Task {
    private int id;
    private String title;
    private String description;
    // Correspond au champ MySQL
    private Timestamp createAt;
    private boolean status;
    private int accountId; // ID du compte associé

    public Task(int id, String title, String description, Timestamp createAt, boolean status, int accountId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createAt = createAt;
        this.status = status;
        this.accountId = accountId;
    }

    public Task(String title, String description, boolean status, int accountId) {
        this.title = title;
        this.description = description;
        // Prend la date actuelle
        this.createAt = new Timestamp(System.currentTimeMillis());
        this.status = status;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', description='" + description + "', status=" + status + ", accountId=" + accountId + "}";
    }
}
