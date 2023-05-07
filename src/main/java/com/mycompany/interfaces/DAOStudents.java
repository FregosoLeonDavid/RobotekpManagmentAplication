/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.models.Students;
import java.util.List;

/**
 *
 * @author David Fregozo
 */
public interface DAOStudents {
    public void enroll(Students ob) throws Exception;
    public void edit(Students ob) throws Exception;
    public void delete(Students ob) throws Exception;
    public List<Students> lists() throws Exception;
    
}
