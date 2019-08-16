/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.edu.app;

import br.ifms.edu.datamodel.Contato;
import maruyama.components.swing.ObjectTableModel;

/**
 *
 * @author gustavo
 */
public class ContatoController {

    private ContatoModel model;
    private ContatoView view;
    private ObjectTableModel tableModel;

    public ContatoController(ContatoModel model, ContatoView view) {
        this.model = model;
        this.view = view;
        inicializarTabela();
        inicializarAcoesDeBotoes();
    }

    public void inicializarTabela() {
        tableModel = new ObjectTableModel(Contato.class, model.getContatos(),"nome", "telefone", "email");
        view.getTabela().setModel(tableModel);
    }

    public void inicializarAcoesDeBotoes() {
        view.getBotaoNovo().addActionListener((e) -> {
            model.novo();
            view.getCampoEmail().setText("");
            view.getCampoNome().setText("");
            view.getCampoTelefone().setText("");
        });

        view.getBotaoSalvar().addActionListener((e) -> {
            Contato contato = model.getContato();
            contato.setNome(view.getCampoNome().getText());
            contato.setEmail(view.getCampoEmail().getText());
            contato.setTelefone(view.getCampoTelefone().getText());
            model.salvar();
            tableModel.fireTableDataChanged();
        });

        view.getBotaoExcluir().addActionListener((e) -> {
            model.remover();
            tableModel.fireTableDataChanged();
        });
        
        view.getTabela().getSelectionModel().addListSelectionListener((e) -> {
            int index = view.getTabela().getSelectedRow();
            Contato c = (Contato) tableModel.getObject(index);
            model.setContato(c);
            view.getCampoEmail().setText(c.getEmail());
            view.getCampoNome().setText(c.getNome());
            view.getCampoTelefone().setText(c.getTelefone());
        });
    }
}
