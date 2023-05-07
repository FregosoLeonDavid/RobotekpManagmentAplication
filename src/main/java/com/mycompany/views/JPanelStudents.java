



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.views;

import com.mycompany.interfaces.DAOStudents;
import com.mycompany.robotekpproject.DAOStudentsImpl;
import com.mycompany.robotekpproject.DashboardMainJFrame;
import com.mycompany.viewsoperations.JPanelEditStudent;
import com.mycompany.viewsoperations.JPanelAddStudent;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author David Fregozo
 */
public class JPanelStudents extends javax.swing.JPanel {

    /**
     * Creates new form home
     */
    public JPanelStudents() {
        initComponents();
        InitStyles();
        InitTableComponents();
        LoadUsers();
    }
    
    public void LoadUsers() {
        try {
            DAOStudents dao = new DAOStudentsImpl();
            DefaultTableModel model = (DefaultTableModel) jtStudents.getModel();
            dao.lists().forEach((u) -> model.addRow(new Object[] {
                u.getStudentName(),
                u.getNoControl(),
                u.getAge(),
                u.getBloodType(),
                u.getGradeLevel(),
                u.isAllergies(),
                u.getAllergyDescription(),
                u.getTutorName(),
                u.getTutorPhoneNumber(),
                u.getEmail(),
                u.getEmergencyContact(),
                u.getEmergencyPhoneNumber(),
                u.getStreet(),
                u.getExteriorNumber(),
                u.getInteriorNumber(),
                u.getNeighborhood(),
                u.getCity()
                }));
            
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void InitTableComponents() {
        Color blue=new Color(140, 223, 223);
        
        jtStudents.setShowVerticalLines(true);
        jtStudents.setGridColor(blue);
        
        JTableHeader header = jtStudents.getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(blue));
       
        Border border = BorderFactory.createLineBorder(blue, 2);
        header.setBorder(border);

        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        jtStudents.getColumnModel().getColumn(2).setCellRenderer(renderer);
        
        for (int x=0; x<17; x++) {
            jtStudents.getColumnModel().getColumn(x).setPreferredWidth(200);
        }
        
    }
    
    public void InitStyles() {
        JButton[] rows = {btnSearch, btnDelete, btnAdd, btnEdit};
        
        lblTitle.putClientProperty( "FlatLaf.style", "font: $h2.regular.font" );
        lblTitle.setForeground(Color.black);
        
        Color colorGris=new Color(153, 153, 153);
        
        txtSearch.putClientProperty( "FlatLaf.style", "font: 15 $h3.regular.font" );
        txtSearch.setForeground(colorGris);
        txtSearch.setBackground(Color.white);
        
        for (int x=0; x<rows.length; x++) {
            rows[x].putClientProperty( "FlatLaf.style", "font: 15 $h3.regular.font" );
            rows[x].setForeground(Color.white);
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnSearch = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtStudents = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(744, 487));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(744, 487));

        lblTitle.setText("ALUMNOS");

        txtSearch.setText("Ingrese el nombre del alumno:");

        btnDelete.setBackground(new java.awt.Color(235, 52, 57));
        btnDelete.setText("ELIMINAR");

        btnSearch.setBackground(new java.awt.Color(48, 161, 255));
        btnSearch.setText("BUSCAR");
        btnSearch.setPreferredSize(new java.awt.Dimension(125, 32));

        btnAdd.setBackground(new java.awt.Color(1, 167, 91));
        btnAdd.setText("AGREGAR");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(216, 196, 49));
        btnEdit.setText("EDITAR");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        jtStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alumno", "Numero de control", "Edad", "Tipo de sangre", "Grado escolar", "Alergias", "Descripcion de alergias", "Tutor", "Telefono", "Correo", "Contacto auxiliar", "Telefono auxiliar", "Calle", "Numero exterior", "Numero interior", "Colonia", "Ciudad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtStudents.setAutoResizeMode(0);
        jtStudents.setShowGrid(false);
        jScrollPane1.setViewportView(jtStudents);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(txtSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 8, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        JPanelAddStudent as = new JPanelAddStudent();
        DashboardMainJFrame.showContent(as);
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        JPanelEditStudent es = new JPanelEditStudent();
        DashboardMainJFrame.showContent(es);
    }//GEN-LAST:event_btnEditMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jtStudents;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
