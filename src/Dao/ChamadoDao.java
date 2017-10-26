package Dao;

import Bean.Chamado;
import Bean.Gerente;
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
            String sql = "insert into chamado (prioridade, descricao, idCliente, status, idTecnico) values ("+c.getPrioridade()+",'"+c.getDescricao()+"',"+c.getIdCliente()+","+c.getStatus()+","+c.getIdTecnico()+");";
            sqlite.executarSQL(sql);
        }
    }

    public ArrayList<Chamado> retornaLista(int idUsuario, int tipoUsuario){
        ArrayList<Chamado> lista = new ArrayList<>();
        String sql;

        if(tipoUsuario==1) {
            sql = "SELECT id, prioridade, descricao, idCliente, status FROM chamado where idCliente = "+idUsuario+";";
        }
        else if(tipoUsuario==2){
            sql = "SELECT id, prioridade, descricao, idCliente, status FROM chamado where idTecnico = "+idUsuario+";";
        }
        else {
            sql = "SELECT id, prioridade, descricao, idCliente, status FROM chamado;";
        }

        ResultSet rs = sqlite.querySql(sql);

        try{
            while(rs.next()){
                int id = rs.getInt("id");
                int prioridade = rs.getInt("prioridade");
                String descricao = rs.getString("descricao");
                int idCliente = rs.getInt("idCliente");
                int status = rs.getInt("status");

                Chamado c = new Chamado();
                c.setPrioridade(prioridade);
                c.setId(id);
                c.setDescricao(descricao);
                c.setStatus(status);

                lista.add(c);
                //
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

    public void finalizar(int id){
        String sql = "update chamado set status = 0 where id="+id;
        sqlite.executarSQL(sql);
    }

    public void update(Chamado c){
        String sql = "update chamado set descricao='"+c.getDescricao()+"', prioridade='"+c.getPrioridade()+" where id = "+c.getId()+";";

        sqlite.executarSQL(sql);
    }

    public void alterarTecnico(int idChamado, int idTecnico){
        String sql = "update chamado set idTecnico ="+idTecnico+" where id="+idChamado+";";
        sqlite.executarSQL(sql);
    }
}
