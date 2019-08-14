/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.edu.swing;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gustavo Y. M. {gustavo.maruyama@ifms.edu.br}
 */
public class ObjectTableModel extends AbstractTableModel {

    private Class clazz;
    private List lista;
    private String[] colunas;

    public ObjectTableModel(Class clazz, List lista, String... colunas) {
        this.clazz = clazz;
        this.lista = lista;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = lista.get(rowIndex);
        String col = colunas[columnIndex];

        try {
            Field f = clazz.getDeclaredField(col);
            f.setAccessible(true);
            return f.get(obj);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(ObjectTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ObjectTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ObjectTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ObjectTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        try {
            Field field = clazz.getDeclaredField(colunas[column]);
            Tabela annotation = field.getAnnotation(Tabela.class);
            return annotation.descricaoColuna();
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(ObjectTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ObjectTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    public Object getObject(int index){
        return lista.get(index);
    }
}
