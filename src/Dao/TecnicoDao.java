package Dao;

import Bean.Chamado;
import Bean.Gerente;
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
            String sql = "insert into pessoa (nome, telefone, loginEmail, senha, tipoUsuario, numTarefas) values ('"+t.getNome()+"','"+t.getTelefone()+"','"+t.getLoginEmail()+"','"+t.getSenha()+"',"+t.getTipoUsuario()+","+t.getNumTarefas()+");";
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

                Tecnico c = new Tecnico();
                c.setNumTarefas(numTarefas);
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
