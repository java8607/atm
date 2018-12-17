/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acsendo.atm.service;

import com.acsendo.atm.dao.BillDAO;
import com.acsendo.atm.dao.entities.Bill;
import com.acsendo.atm.dao.vo.ResponseVO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author OSKAR
 */
@Stateless
public class AtmSessionBean implements AtmSessionBeanLocal {

    private BillDAO billDao;

    /**
     * Contain values substract with valid value
     */
    private List<ResponseVO> responses;

    /**
     * Containt response for one cicle to subtract
     */
    private ResponseVO response;
    
    /**
     * Containt the value that client request
     */
    private int value;

    @Override
    public void insertMoney(Bill bill) {
        billDao = new BillDAO();
        billDao.insertMoney(bill);
    }

    @Override
    public List<Bill> findAllBills() {
        billDao = new BillDAO();
        return billDao.findAllBills();
    }

    @Override
    public String processTransaction(List<Bill> bills, Integer value) {
        int valueOrig = value;
        this.value = value;
        
        responses = new ArrayList<>();
        response = new ResponseVO();
        for (Bill bill : bills) {
            response.setDenomination(bill.getDenomination());
            subtractBill(bill);
            if (response.getAmountBills() > 0) {
                responses.add(response);
            }
        }

        if (this.value == 0) {
            return analizerReponse(valueOrig);
        }

        return "No es posible entregar ese valor, intentelo con uno diferente";
    }

    /**
     * This method update data base with value of the withdraw
     * @param bill 
     */
    public void withdraw(Bill bill) {
        billDao = new BillDAO();
        billDao.withdraw(bill);
    }
    
    /**
     * This method evaluate the answer to the client
     * @param valueOrig
     * @return 
     */
    public String analizerReponse(int valueOrig) {
        String output = "Valor Solicitado: "+valueOrig+"\nBilletes entregados :\n";
        Bill bill;
        for (ResponseVO responseVO : responses) {
            bill = new Bill();
            bill.setAmount(responseVO.getAmountBills());
            bill.setDenomination(responseVO.getDenomination());
            withdraw(bill);
            output+= " "+responseVO.getAmountBills()+"("+responseVO.getDenomination()+")\n";
        }
        return output;
    }

    /**
     * This method substract redundantly value with the money available
     * @param bill
     * @return
     */
    public Bill subtractBill(Bill bill) {
        int amount = 0;
        int amountBills = 0;
        int previous = 0;

        amount = bill.getAmount();

        if (amount != 0) {
            previous = value - bill.getDenomination();
            if (previous >= 0) {
                value -= bill.getDenomination();
                bill.setAmount(--amount);                
                amountBills = response.getAmountBills();
                response.setAmountBills(++amountBills);
                subtractBill(bill);
            } else {
                return bill;
            }
        }
        return bill;
    }
}
