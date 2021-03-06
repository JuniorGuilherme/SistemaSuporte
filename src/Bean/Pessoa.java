package Bean;

import Dao.PessoaDao;

import java.util.ArrayList;
import java.util.Scanner;

public class Pessoa {
    private String nome;
    private String telefone;
    private String loginEmail;
    private String senha;
    private int tipoUsuario;
    Scanner tc = new Scanner(System.in);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

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

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String login) {
        this.loginEmail = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Pessoa validaLogin(String nome, String senha){
        PessoaDao pDao = new PessoaDao();
        ArrayList<Pessoa> pList = pDao.retornaLista();

        if(pList!=null) {
            for (Pessoa p : pList
                    ) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    if (p.getSenha().equals(senha)) {
                        System.out.println("Acesso concedido.");
                        return p;
                    } else {
                        System.out.println("Senha incorreta.");
                        return null;
                    }
                }
            }
        }
        System.out.println("Usuario nao encontrado.");
        return null;
    }

    public void cadastrar(){
        System.out.println("Nome: ");
        this.nome = tc.next();
        System.out.println("Telefone:");
        this.telefone = tc.next();
        System.out.println("Email: ");
        this.loginEmail=tc.next();
        System.out.println("Senha: ");
        this.senha=tc.next();
        System.out.println("Nivel de Permissao: ");
        this.tipoUsuario=tc.nextInt();
    }
}
