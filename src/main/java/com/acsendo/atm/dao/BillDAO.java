/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acsendo.atm.dao;

import com.acsendo.atm.dao.entities.Bill;
import com.acsendo.atm.dao.factory.EntityFactory;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Oscar David Rodríguez Beltrán
 */
public class BillDAO{
    
    /**
     * Insert money in data base
     * @param bill 
     */
    public void insertMoney(Bill bill){
        EntityManager em = EntityFactory.getEntityManager();
        em.getTransaction().begin();

        Bill billOrig = (Bill) em.find(Bill.class, bill.getDenomination());
        if(billOrig !=null){
            int amount = billOrig.getAmount() + bill.getAmount();
            billOrig.setAmount(amount);
        }else{
            em.persist(bill);
        }
        em.getTransaction().commit();
    }
    
    /**
     * withdraw money for denomination
     * @param bill 
     */
    public void withdraw(Bill bill){
        EntityManager em = EntityFactory.getEntityManager();
        em.getTransaction().begin();

        Bill billOrig = (Bill) em.find(Bill.class, bill.getDenomination());
        
        if(billOrig!=null){
            Integer amount = billOrig.getAmount()-bill.getAmount();
            billOrig.setAmount(amount);
        }

        em.getTransaction().commit();    
    }
    
    /**
     * Select all Bills in order descendent to denomination
     * @return 
     */
    public List<Bill> findAllBills(){
        List<Bill> bills;
        EntityManager em = EntityFactory.getEntityManager();
        bills = (List<Bill>) em.createQuery("SELECT b FROM Bill b order by b.denomination desc").getResultList();
        return bills;         
    }
}
