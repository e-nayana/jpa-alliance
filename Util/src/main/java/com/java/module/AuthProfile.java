package com.java.module;

public class AuthProfile {

    private final String keycloakId;
    private final String sessionState;
    private final String name;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String email;
    private final Boolean emailVerified;
    private final String phoneNumber;
    private final Boolean phoneNumberVerified;
    private final Integer authTime;
    private final Long updatedAt;
    private final Integer expiration;
    private final Integer issuedAt;
    private final String issuer;

    public AuthProfile(String keycloakId, String sessionState, String name, String firstName, String lastName, String username, String email, Boolean emailVerified, String phoneNumber, Boolean phoneNumberVerified, Integer authTime, Long updatedAt, Integer expiration, Integer issuedAt, String issuer) {
        this.keycloakId = keycloakId;
        this.sessionState = sessionState;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.emailVerified = emailVerified;
        this.phoneNumber = phoneNumber;
        this.phoneNumberVerified = phoneNumberVerified;
        this.authTime = authTime;
        this.updatedAt = updatedAt;
        this.expiration = expiration;
        this.issuedAt = issuedAt;
        this.issuer = issuer;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public String getSessionState() {
        return sessionState;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public Integer getAuthTime() {
        return authTime;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public Integer getIssuedAt() {
        return issuedAt;
    }

    public String getIssuer() {
        return issuer;
    }
}
