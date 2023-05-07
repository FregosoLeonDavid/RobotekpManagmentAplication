/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.models.Payments;
import java.util.List;

/**
 *
 * @author David Fregozo
 */
public interface DAOPayments {
    public void enroll(Payments ob) throws Exception;
    public void edit(Payments ob) throws Exception;
    public void delete(Payments ob) throws Exception;
    public List<Payments> lists() throws Exception;
    
}
