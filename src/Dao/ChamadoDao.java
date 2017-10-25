package Dao;

import Bean.Chamado;
import Bean.Gerente;
import Bean.Pessoa;
import Connection.DbHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ChamadoDao {
    DbHelper sqlite;

    public ChamadoDao(){
        sqlite = new DbHelper();
    }

    public void salvar(Chamado c){
        if(c!=null){
            String sql = "insert into chamado (prioridade, descricao, idCliente) values ("+c.getPrioridade()+",'"+c.getDescricao()+"',"+c.getIdCliente()+")";
            sqlite.executarSQL(sql);
        }
    }

    public ArrayList<Chamado> retornaLista(){
        ArrayList<Chamado> lista = new ArrayList<>();
        String sql = "SELECT id, prioridade, descricao, idCliente FROM chamado;";
        ResultSet rs = sqlite.querySql(sql);

        try{
            while(rs.next()){
                int id = rs.getInt("id");
                int prioridade = rs.getInt("prioridade");
                String descricao = rs.getString("descricao");
                int idCliente = rs.getInt("idCliente");

                Chamado c = new Chamado(id, descricao, prioridade, idCliente);

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
