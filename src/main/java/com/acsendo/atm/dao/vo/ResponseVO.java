/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acsendo.atm.dao.vo;

/**
 *
 * @author OSKAR
 */
public class ResponseVO {
    
    private int amountBills;
    
    private int denomination;

    public ResponseVO() {
    }

    public ResponseVO(int amountBills, int denomination) {
        this.amountBills = amountBills;
        this.denomination = denomination;
    }

    //Getters and Setters
    
    public int getAmountBills() {
        return amountBills;
    }

    public void setAmountBills(int amountBills) {
        this.amountBills = amountBills;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }
      
}
