/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acsendo.atm.controller;

import com.acsendo.atm.dao.BillDAO;
import com.acsendo.atm.dao.entities.Bill;
import com.acsendo.atm.service.AtmSessionBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.print.attribute.standard.Severity;

/**
 *
 * @author OSKAR
 */
@Named(value = "atmManagedBean")
@ViewScoped
public class AtmManagedBean implements Serializable{
    
    
    @EJB
    private AtmSessionBeanLocal atm;
    
    /**
     * List of bills
     */
    private List<Bill> billsAdmin;
    
    /*Attribute that contain denomination of the bill*/
    private Integer denomination;

    /*Represent amont to withdraw*/
    private Integer amount;
    
    /**
     * Creates a new instance of AtmBean
     */
    public AtmManagedBean() {
      
    }

    @PostConstruct
    public void init() {
        billsAdmin = new ArrayList<>();
        billsAdmin = atm.findAllBills();
    }
    
    /**
     * Register amount with denomination and amount
     * @return 
     */
    public String registerMoney(){
        Bill bill = new Bill();
        bill.setAmount(amount);
        bill.setDenomination(denomination);
        atm.insertMoney(bill);
        atm.findAllBills();
        FacesContext context = FacesContext.getCurrentInstance();         
        String message =  "Registro exitoso";
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
        return "admin.xhtml";
    }
    
    /**
     * Init transaction to withdraw
     */
    public void startTransaction(){
        List<Bill> bills = atm.findAllBills();
        FacesContext context = FacesContext.getCurrentInstance();         
        String message =  atm.processTransaction(bills, amount);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }
    
    //Getters and setters
    public Integer getDenomination() {
        return denomination;
    }

    public void setDenomination(Integer denomination) {
        this.denomination = denomination;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }        

    public List<Bill> getBillsAdmin() {
        return billsAdmin;
    }

    public void setBillsAdmin(List<Bill> billsAdmin) {
        this.billsAdmin = billsAdmin;
    }
    
    
    
}
