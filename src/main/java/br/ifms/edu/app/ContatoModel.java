package br.ifms.edu.app;

import br.ifms.edu.datamodel.Contato;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class ContatoModel {

    private Contato contato;
    private List<Contato> contatos;

    public ContatoModel() {
        contatos = new ArrayList<Contato>();
    }

    public void novo() {
        contato = new Contato();
    }

    public void salvar() {
        if (contato == null) {
            return;
        }
        if (contatos.contains(contato)) {
            return;
        }
        contatos.add(contato);
    }

    public void remover() {
        if (contato == null) {
            return;
        }
        contatos.remove(contato);
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
