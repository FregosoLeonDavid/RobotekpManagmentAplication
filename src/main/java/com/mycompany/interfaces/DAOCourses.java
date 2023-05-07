/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.models.Courses;
import java.util.List;

/**
 *
 * @author David Fregozo
 */
public interface DAOCourses {
    public void enroll(Courses ob) throws Exception;
    public void edit(Courses ob) throws Exception;
    public void delete(Courses ob) throws Exception;
    public List<Courses> lists() throws Exception;
    
}
