/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.business.domain;

import java.util.Date;

/**
 *
 * @author joseg
 */
public class UserConnection implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    
    private int userConnectionId;
    private int userId;
    private Long lastConnection;
    private int active;

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the lastConnection
     */
    public Long getLastConnection() {
        return lastConnection;
    }

    /**
     * @param lastConnection the lastConnection to set
     */
    public void setLastConnection(Long lastConnection) {
        this.lastConnection = lastConnection;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * @return the userConnectionId
     */
    public int getUserConnectionId() {
        return userConnectionId;
    }

    /**
     * @param userConnectionId the userConnectionId to set
     */
    public void setUserConnectionId(int userConnectionId) {
        this.userConnectionId = userConnectionId;
    }
    
}
