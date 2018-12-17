/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acsendo.atm.service;

import com.acsendo.atm.dao.entities.Bill;
import com.acsendo.atm.dao.vo.ResponseVO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author OSKAR
 */
@Local
public interface AtmSessionBeanLocal {
    
    /**
     * Insert or update amount of bills add to administrator
     * @param bill 
     */
    void insertMoney(Bill bill);
        
    /**
     * Select all Bills in order descendent to denomination
     * @return 
     */
    List<Bill> findAllBills();
    
    /**
     * This method process client's request
     * @param bills
     * @param amount
     * @return 
     */
    String processTransaction(List<Bill> bills, Integer amount);
}
