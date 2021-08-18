package view;

import controller.ControladorRequerimientosReto4;
import java.awt.*;
import java.awt.event.*;
import model.vo.Requerimiento2;
import model.vo.Requerimiento3;
import model.vo.Requerimiento1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

public class VistaRequerimientosReto4 extends JFrame {
    
    private JLabel lbTitulo;
    private JButton btnConsulta1;
    private JButton btnConsulta2;
    private JButton btnConsulta3;
    private JTable jtDatos;
    private JScrollPane desp;
    
    public static final ControladorRequerimientosReto4 controlador = new ControladorRequerimientosReto4();

    public VistaRequerimientosReto4() {
        
        //contenedor 
        this.setTitle("Consultas SQL");
        this.setBounds(300, 10, 950, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        
        
        //componentes 
        // label
        lbTitulo = new JLabel();
        lbTitulo.setText("CONSLUTAS SQL A BASE DE DATOS PROYECTOS DE CONSTRUCCION");
        lbTitulo.setBounds(130, 0, 700, 100);
        lbTitulo.setVerticalAlignment(JLabel.CENTER);
        lbTitulo.setHorizontalAlignment(JLabel.CENTER);
        this.add(lbTitulo);
        
        //botones
        
        btnConsulta1 = new JButton();
        btnConsulta1.setText("5 Proyectos con menor gastos");
        btnConsulta1.setBounds(10, 100, 300, 50);
        this.add(btnConsulta1);
        
        btnConsulta2 = new JButton();
        btnConsulta2.setText("Ranking de bancos con mayor área");
        btnConsulta2.setBounds(320, 100, 300, 50);
        this.add(btnConsulta2);
        
        btnConsulta3 = new JButton();
        btnConsulta3.setText("Ranking materiales importados");
        btnConsulta3.setBounds(630, 100, 300, 50);
        this.add(btnConsulta3);
        
        // tabla para mostrar resultados
        
        jtDatos = new JTable();
        //jtDatos.setFillsViewportHeight(true);
        jtDatos.setBounds(200, 200, 500, 200);
        //jtDatos.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        //jtDatos.setPreferredScrollableViewportSize(new Dimension(200, 200));
        this.add(jtDatos);
        // scroll
        //desp = new JScrollPane(jtDatos);
        //this.add(desp, BorderLayout.CENTER);
        /*desp = new JScrollPane();
        desp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        desp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        */
        //acciones de los botones 
        
        btnConsulta1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                try {
                    String[] Titulos = {"ID_Proyecto", "Clasificacion", "Gasto_Compras", "Serial"};
                    DefaultTableModel tb1 = new DefaultTableModel();
                    
                    tb1.setColumnIdentifiers(Titulos);
                    jtDatos.setModel(tb1);
                    tb1.addRow(Titulos);
                    
                    ArrayList<Requerimiento1> rankingProyectosCompras = controlador.consultarProyectosCompras10();
                    for (Requerimiento1 proyecto: rankingProyectosCompras){
                        tb1.addRow(new Object[]{proyecto.getIdProyecto(), proyecto.getClasificacion(), Math.round(proyecto.getGastoCompras()), proyecto.getSerial()});
                    }
                }
                catch(SQLException e){
                    System.out.println(e);
                }
            
            }
    
    });
    
        
        btnConsulta2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                try {
                    String[] Titulos2 = {"Banco_Vinculado", "Área_Promedio"};
                    DefaultTableModel tb2 = new DefaultTableModel();
                    tb2.setColumnIdentifiers(Titulos2);
                    jtDatos.setModel(tb2);
                    tb2.addRow(Titulos2);
                    
                    ArrayList<Requerimiento2> bancoRankeadoAreaPromedio = controlador.consultarBancosRankeadosAreaPromedio();
                    for (Requerimiento2 banco: bancoRankeadoAreaPromedio){
                        tb2.addRow(new Object[]{banco.getBancoVinculado(), banco.getAreaPromedio()});
                    }
                }
                catch(SQLException e){
                    System.out.println(e);
                }
            
            }
    
    });
         
     btnConsulta3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                try {
                    String[] Titulos3 = {"Nombre_Material", "Importado", "No_Compras"};
                    DefaultTableModel tb3 = new DefaultTableModel();
                    tb3.setColumnIdentifiers(Titulos3);
                    jtDatos.setModel(tb3);
                    tb3.addRow(Titulos3);
                    
                    ArrayList<Requerimiento3> materialRankeadoCompras = controlador.consultarMaterialesRankeadosCompras();
                    for (Requerimiento3 material: materialRankeadoCompras){
                        tb3.addRow(new Object[]{material.getNombreMaterial(), material.getImportado(), material.getNoCompras()});
                    }
                }
                catch(SQLException e){
                    System.out.println(e);
                }
            
            }
    
    });    
        
    }
     
    
    

}