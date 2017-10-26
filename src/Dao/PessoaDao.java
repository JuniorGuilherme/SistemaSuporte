package Dao;

import Bean.Pessoa;
import Connection.DbHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class PessoaDao {
    DbHelper sqlite;

    public PessoaDao(){
        sqlite = new DbHelper();
    }

    public ArrayList<Pessoa> retornaLista(){
        ArrayList<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT id, nome, telefone, loginEmail, senha, tipoUsuario FROM GERENTE;";
        preencheArray(sql, lista);
        sql = "SELECT id, nome, telefone, loginEmail, senha, tipoUsuario FROM cliente;";
        preencheArray(sql, lista);
        sql = "SELECT id, nome, telefone, loginEmail, senha, tipoUsuario FROM tecnico;";
        preencheArray(sql, lista);
        if(lista.isEmpty()){
            return null;
        }else{
            return lista;
        }
    }

    public void salvar(Pessoa p){
        if(p!=null){
            String sql = "insert into pessoa("+p.getNome()+","+p.getTelefone()+","+p.getLoginEmail()+","+p.getSenha()+","+p.getTipoUsuario()+")";
            sqlite.executarSQL(sql);
        }
    }

    public void preencheArray(String sql, ArrayList lista){
        ResultSet rs = sqlite.querySql(sql);

        try{
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String loginEmail = rs.getString("loginEmail");
                String senha = rs.getString("senha");
                int tipoUsuario = rs.getInt("tipoUsuario");

                Pessoa p = new Pessoa();
                p.setId(id);
                p.setLoginEmail(loginEmail);
                p.setNome(nome);
                p.setSenha(senha);
                p.setTelefone(telefone);
                p.setTipoUsuario(tipoUsuario);

                lista.add(p);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
