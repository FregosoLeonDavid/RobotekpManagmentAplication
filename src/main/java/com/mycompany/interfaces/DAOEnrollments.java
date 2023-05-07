/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.models.Enrollments;
import java.util.List;

/**
 *
 * @author David Fregozo
 */
public interface DAOEnrollments {
    public void enroll(Enrollments ob) throws Exception;
    public void edit(Enrollments ob) throws Exception;
    public void delete(Enrollments ob) throws Exception;
    public List<Enrollments> lists() throws Exception;
    
}
