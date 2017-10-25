package Dao;

import Bean.Tecnico;
import Connection.DbHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TecnicoDao {
    DbHelper sqlite;

    public TecnicoDao(){
        sqlite = new DbHelper();
    }

    public void salvar(Tecnico t){
        if(t!=null){
            String sql = "insert into tecnico (nome, telefone, loginEmail, senha, tipoUsuario, numTarefas) values ('"+t.getNome()+"','"+t.getTelefone()+"','"+t.getLoginEmail()+"','"+t.getSenha()+"',"+t.getTipoUsuario()+","+t.getNumTarefas()+");";
            sqlite.executarSQL(sql);
        }
    }

    public ArrayList<Tecnico> retornaLista(){
        ArrayList<Tecnico> lista = new ArrayList<>();
        String sql = "SELECT id, nome, telefone, loginEmail, senha, tipousuario, numTarefas FROM tecnico;";
        ResultSet rs = sqlite.querySql(sql);

        try{
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String loginEmail = rs.getString("loginEmail");
                String senha = rs.getString("senha");
                int tipoUsuario = rs.getInt("tipoUsuario");
                int numTarefas = rs.getInt("numTarefas");

                Tecnico t = new Tecnico();
                t.setNumTarefas(numTarefas);
                t.setTipoUsuario(tipoUsuario);
                t.setSenha(senha);
                t.setLoginEmail(loginEmail);
                t.setTelefone(telefone);
                t.setNome(nome);
                t.setId(id);

                lista.add(t);
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
