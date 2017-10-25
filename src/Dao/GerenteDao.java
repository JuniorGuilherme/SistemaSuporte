package Dao;

import Bean.Gerente;
import Connection.DbHelper;

public class GerenteDao {
    DbHelper sqlite;

    public GerenteDao(){
        sqlite = new DbHelper();
    }

    public void salvar(Gerente g){
        if(g!=null){
            String sql = "insert into pessoa (nome, telefone, loginEmail, senha, tipoUsuario) values ('"+g.getNome()+"','"+g.getTelefone()+"','"+g.getLoginEmail()+"','"+g.getSenha()+"',"+g.getTipoUsuario()+")";
            sqlite.executarSQL(sql);
        }
    }
}
