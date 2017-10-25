package Bean;

import Dao.ChamadoDao;
import Dao.TecnicoDao;

import java.util.ArrayList;

public class Chamado {
    ChamadoDao cDao = new ChamadoDao();

    private int prioridade, idCliente, id, idTecnico;
    private String descricao;

    public Chamado(int id, String descricao, int prioridade, int idCliente){
        this.prioridade=prioridade;
        this.descricao=descricao;
        this.idCliente=idCliente;
        this.id=id;
    }

    public Chamado(String descricao, int prioridade, int idCliente){
        this.prioridade=prioridade;
        this.descricao=descricao;
        this.idCliente=idCliente;
    }

    public Chamado(int idCliente){
        this.idCliente=idCliente;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void abrirChamado(Chamado c){
        TecnicoDao tDao = new TecnicoDao();
        Tecnico tVago = new Tecnico();
        ArrayList<Tecnico> list;
        list=tDao.retornaLista();
        if (!list.isEmpty()) {
            int menor = list.get(0).getNumTarefas();
            for (Tecnico t : list
                    ) {
                if(t.getNumTarefas()<menor){
                    menor=t.getNumTarefas();
                    tVago=t;
                }
            }
            c.setIdTecnico(tVago.getId());
            cDao.salvar(c);
        }
        else{
            System.out.println("Tecnicos indisponiveis.");
        }
    }

    public void listarChamados(){
        ArrayList<Chamado> list = new ArrayList();
        list=cDao.retornaLista();

        for (Chamado c: list
             ) {
            System.out.println("");
            System.out.println("Codigo: "+c.getId());
            System.out.println("Descricao: "+c.getDescricao());
            System.out.println("Prioridade: "+getPrioridade());
            System.out.println("Codigo do Cliente: "+getIdCliente());
        }
    }
}
