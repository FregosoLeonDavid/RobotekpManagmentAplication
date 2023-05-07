/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.robotekpproject;
import com.mycompany.db.ConnectionDB;
import com.mycompany.interfaces.DAOStudents;
import com.mycompany.models.Students;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOStudentsImpl extends ConnectionDB implements DAOStudents {    
    
    @Override
    public void enroll(Students ob) throws Exception {
        try {
            this.startConnection();
            PreparedStatement st = this.connectionMariaDB.prepareStatement("{call addStudent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            st.setString(1, ob.getNoControl());
            st.setString(2, ob.getStudentName());
            st.setInt(3, ob.getAge());
            st.setString(4, ob.getBloodType());
            st.setString(5, ob.getGradeLevel());
            st.setBoolean(6, ob.isAllergies());
            st.setString(7, ob.getAllergyDescription());
            st.setString(8, ob.getTutorName());
            st.setString(9, ob.getTutorPhoneNumber());
            st.setString(10, ob.getEmail());
            st.setString(11, ob.getEmergencyContact());
            st.setString(12, ob.getEmergencyPhoneNumber());
            st.setString(13, ob.getStreet());
            st.setString(14, ob.getExteriorNumber());
            st.setString(15, ob.getInteriorNumber());
            st.setString(16, ob.getNeighborhood());
            st.setString(17, ob.getCity());
            
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void edit(Students ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Students ob) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Students> lists() throws Exception {
        List<Students> listSt = null;
        try {
            this.startConnection();
            PreparedStatement st = this.connectionMariaDB.prepareStatement("SELECT * FROM students JOIN address WHERE students.noControl = address.noControl");
            
            listSt = new ArrayList();
            ResultSet rs = st.executeQuery();
            
            while(rs.next()) {
                Students stu = new Students();
                stu.setNoControl(rs.getString("noControl"));
                stu.setStudentName(rs.getString("studentName"));
                stu.setAge(rs.getInt("age"));
                stu.setBloodType(rs.getString("bloodType"));
                stu.setGradeLevel(rs.getString("gradeLevel"));
                stu.setAllergies(rs.getBoolean("allergies"));
                stu.setAllergyDescription(rs.getString("allergyDescription"));
                stu.setTutorName(rs.getString("tutorName"));
                stu.setTutorPhoneNumber(rs.getString("tutorPhoneNumber"));
                stu.setEmail(rs.getString("email"));
                stu.setEmergencyContact(rs.getString("emergencyContact"));
                stu.setEmergencyPhoneNumber(rs.getString("emergencyPhoneNumber"));
                stu.setIdAddress(rs.getInt("idAddres"));
                stu.setStreet(rs.getString("street"));
                stu.setExteriorNumber(rs.getString("exteriorNumber"));
                stu.setInteriorNumber(rs.getString("interiorNumber"));
                stu.setNeighborhood(rs.getString("neighborhood"));
                stu.setCity(rs.getString("city"));

                listSt.add(stu);
            }
            rs.close();
            st.close();
            
            
        } catch(Exception e) {
            throw e;
        } finally {
            this.closeConnection();
        }
        return listSt;
    }
    
}
