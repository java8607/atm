/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acsendo.atm.dao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OSKAR
 */
@Entity
@Table(name = "bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
    , @NamedQuery(name = "Bill.findByDenomination", query = "SELECT b FROM Bill b WHERE b.denomination = :denomination")
    , @NamedQuery(name = "Bill.findByAmount", query = "SELECT b FROM Bill b WHERE b.amount = :amount")})
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "denomination")
    private Integer denomination;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private int amount;

    public Bill() {
    }

    public Bill(Integer denomination) {
        this.denomination = denomination;
    }

    public Bill(Integer denomination, int amount) {
        this.denomination = denomination;
        this.amount = amount;
    }

    public Integer getDenomination() {
        return denomination;
    }

    public void setDenomination(Integer denomination) {
        this.denomination = denomination;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (denomination != null ? denomination.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.denomination == null && other.denomination != null) || (this.denomination != null && !this.denomination.equals(other.denomination))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acsendo.atm.dao.entities.Bill[ denomination=" + denomination + " ]";
    }
    
}
