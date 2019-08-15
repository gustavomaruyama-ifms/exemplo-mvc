package br.ifms.edu.datamodel;

import br.ifms.edu.swing.Tabela;

/**
 *
 * @author gustavo
 */
public class Contato {
    @Tabela(descricaoColuna = "Nome")
    private String nome;
    @Tabela(descricaoColuna = "Telefone")
    private String telefone;
    @Tabela(descricaoColuna = "E-mail")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
