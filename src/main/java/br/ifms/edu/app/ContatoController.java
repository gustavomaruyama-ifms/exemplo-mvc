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

    private static final String EDITANDO = "EDITANDO";
    private static final String INICIAL = "INICIAL";
    private static final String OBJETO_SELECIONADO = "OBJETO_SELECIONADO";

    private ContatoModel model;
    private ContatoView view;
    private ObjectTableModel tableModel;
    private String estado;

    public ContatoController(ContatoModel model, ContatoView view) {
        this.model = model;
        this.view = view;
        this.estado = ContatoController.INICIAL;
        inicializarTabela();
        inicializarAcoesDeBotoes();
        aplicarEstado();        
    }

    public void aplicarEstado() {
        if (estado.equals(ContatoController.INICIAL)) {
            view.getCampoEmail().setEnabled(false);
            view.getCampoNome().setEnabled(false);
            view.getCampoTelefone().setEnabled(false);
            view.getBotaoCancelar().setEnabled(false);
            view.getBotaoEditar().setEnabled(false);
            view.getBotaoExcluir().setEnabled(false);
            view.getBotaoSalvar().setEnabled(false);
            view.getBotaoNovo().setEnabled(true);
        }

        if (estado.equals(ContatoController.EDITANDO)) {
            view.getCampoEmail().setEnabled(true);
            view.getCampoNome().setEnabled(true);
            view.getCampoTelefone().setEnabled(true);
            view.getBotaoCancelar().setEnabled(true);
            view.getBotaoSalvar().setEnabled(true);
            view.getBotaoEditar().setEnabled(false);
            view.getBotaoExcluir().setEnabled(false);
            view.getBotaoNovo().setEnabled(false);            
        }
        
        if(estado.equals(ContatoController.OBJETO_SELECIONADO)){
            view.getCampoEmail().setEnabled(false);
            view.getCampoNome().setEnabled(false);
            view.getCampoTelefone().setEnabled(false);
            view.getBotaoEditar().setEnabled(true);
            view.getBotaoSalvar().setEnabled(false);
            view.getBotaoExcluir().setEnabled(true);
            view.getBotaoCancelar().setEnabled(false);
            view.getBotaoNovo().setEnabled(true);            
        }
    }

    public void inicializarTabela() {
        tableModel = new ObjectTableModel(Contato.class, model.getContatos(), "nome", "telefone", "email");
        view.getTabela().setModel(tableModel);
    }

    public void inicializarAcoesDeBotoes() {
        view.getBotaoNovo().addActionListener((e) -> {
            model.novo();
            view.getCampoEmail().setText("");
            view.getCampoNome().setText("");
            view.getCampoTelefone().setText("");
            this.estado = ContatoController.EDITANDO;
            aplicarEstado();
        });

        view.getBotaoSalvar().addActionListener((e) -> {
            Contato contato = model.getContato();
            contato.setNome(view.getCampoNome().getText());
            contato.setEmail(view.getCampoEmail().getText());
            contato.setTelefone(view.getCampoTelefone().getText());
            model.salvar();
            tableModel.fireTableDataChanged();
            this.estado = ContatoController.OBJETO_SELECIONADO;
            aplicarEstado();
        });
        
        view.getBotaoEditar().addActionListener((e) -> {
            this.estado = ContatoController.EDITANDO;
            aplicarEstado();
        });

        view.getBotaoExcluir().addActionListener((e) -> {
            view.getCampoEmail().setText("");
            view.getCampoNome().setText("");
            view.getCampoTelefone().setText("");
            model.remover();
            tableModel.fireTableDataChanged();
            this.estado = ContatoController.INICIAL;
            aplicarEstado();
        });

        view.getTabela().getSelectionModel().addListSelectionListener((e) -> {
            int index = view.getTabela().getSelectedRow();
            if (index < 0) {
                return;
            }
            Contato c = (Contato) tableModel.getObject(index);
            model.setContato(c);
            view.getCampoEmail().setText(c.getEmail());
            view.getCampoNome().setText(c.getNome());
            view.getCampoTelefone().setText(c.getTelefone());
            this.estado = ContatoController.OBJETO_SELECIONADO;
            aplicarEstado();
        });
        
        view.getBotaoCancelar().addActionListener((e) -> {
            Contato contato = model.getContato();
            view.getCampoEmail().setText(contato.getEmail());
            view.getCampoNome().setText(contato.getNome());
            view.getCampoTelefone().setText(contato.getTelefone());
            
            if(model.getContatos().contains(contato)){ 
                this.estado = ContatoController.OBJETO_SELECIONADO;
            }else{
                this.estado = ContatoController.INICIAL;
            }   
            aplicarEstado();
        });
    }
}
