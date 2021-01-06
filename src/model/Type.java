/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dolong
 */
public enum Type {
    LOGIN,
    LOGOUT,
    LOGIN_SUCCESS,
    LOGIN_FAIL,
    
    REGISTER,
    REGISTER_NAME,
    REGISTER_SUCCESS,
    REGISTER_FAIL,
    
    LIST_ONLINE,
    UPDATE_LIST_ONLINE,

    CHALLENGE,
    INVITE_CHALLENGE,
    ACCEPT_CHALLENGE,
    REJECT_CHALLENGE,
    PLAYING,
    
    RANKING, 
    HISTORY_GAME,
    RESULT_GAME
}