package Dao;

import Bean.Gerente;
import Connection.DbHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GerenteDao {
    DbHelper sqlite;

    public GerenteDao(){
        sqlite = new DbHelper();
    }

    public void salvar(Gerente g){
        if(g!=null){
            String sql = "insert into GERENTE (nome, telefone, loginEmail, senha, tipoUsuario) values ('"+g.getNome()+"','"+g.getTelefone()+"','"+g.getLoginEmail()+"','"+g.getSenha()+"',"+g.getTipoUsuario()+")";
            sqlite.executarSQL(sql);
        }
    }

    public ArrayList<Gerente> retornaLista(){
        ArrayList<Gerente> lista = new ArrayList<>();
        String sql = "SELECT id, nome, telefone, loginEmail, senha, tipousuario, numTarefas FROM gerente;";
        ResultSet rs = sqlite.querySql(sql);

        try{
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String loginEmail = rs.getString("loginEmail");
                String senha = rs.getString("senha");
                int tipoUsuario = rs.getInt("tipoUsuario");

                Gerente g = new Gerente();
                g.setId(id);;
                g.setLoginEmail(loginEmail);
                g.setNome(nome);
                g.setSenha(senha);
                g.setTelefone(telefone);
                g.setTipoUsuario(tipoUsuario);
                lista.add(g);
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
