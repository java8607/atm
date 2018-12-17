/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acsendo.atm.dao.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author OSKAR
 */
public class EntityFactory {
    
    private static final String UNIDAD_DE_PERSISTENCIA = "com.acsendo_atm_war_1.0PU";
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA);
        
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
