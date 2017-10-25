package Dao;

import Bean.Cliente;
import Bean.Tecnico;
import Connection.DbHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDao {
    DbHelper sqlite;

    public ClienteDao(){
        sqlite = new DbHelper();
    }

    public void salvar(Cliente c){
        if(c!=null){
            String sql = "insert into cliente (nome, telefone, loginEmail, senha, tipoUsuario, numTarefas) values ('"+c.getNome()+"','"+c.getTelefone()+"','"+c.getLoginEmail()+"','"+c.getSenha()+"',"+c.getTipoUsuario()+");";
            sqlite.executarSQL(sql);
        }
    }

    public ArrayList<Cliente> retornaLista(){
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id, nome, telefone, loginEmail, senha, tipousuario FROM cliente;";
        ResultSet rs = sqlite.querySql(sql);

        try{
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String loginEmail = rs.getString("loginEmail");
                String senha = rs.getString("senha");
                int tipoUsuario = rs.getInt("tipoUsuario");

                Cliente c = new Cliente();
                c.setTipoUsuario(tipoUsuario);
                c.setSenha(senha);
                c.setLoginEmail(loginEmail);
                c.setTelefone(telefone);
                c.setNome(nome);
                c.setId(id);

                lista.add(c);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(lista.isEmpty()){
            return null;
        }else{
            return lista;
        }
    }
}
