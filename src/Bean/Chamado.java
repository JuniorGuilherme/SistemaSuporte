package Bean;

import Dao.ChamadoDao;
import Dao.ClienteDao;
import Dao.TecnicoDao;

import java.util.ArrayList;
import java.util.Scanner;

public class Chamado {
    ChamadoDao cDao = new ChamadoDao();
    Scanner tc = new Scanner(System.in);
    ClienteDao clDao = new ClienteDao();

    private int prioridade;
    private int idCliente;
    private int id;
    private int idTecnico;


    private int status;
    private String descricao;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public void abrirChamado(int idUsuarioLogado){
        TecnicoDao tDao = new TecnicoDao();
        Tecnico tVago = new Tecnico();
        ArrayList<Tecnico> list;

        Chamado c = new Chamado();
        int esc, idCl, flag=0;


        if(idUsuarioLogado==3){
            System.out.println("Voce e um gerente. Digite o CODIGO do cliente:");

            do{
                idCl = tc.nextInt();
                if(clDao.consultaId(idCl)){
                    c.setIdCliente(idCl);
                    flag=1;
                }
                else{
                    System.out.println("ID inexistentes. Clientes cadastrados:");
                    Cliente cl = new Cliente();
                    cl.listarClientes();
                    System.out.println("Digite um codigo de cliente existente:");
                }
            }while (flag==0);

        }else{
            c.setIdCliente(idUsuarioLogado);
        }
        System.out.println("Descreva o problema:");
        descricao=tc.next();
        do{
            System.out.println("Escolha prioridade:");
            System.out.println("1- Baixa");
            System.out.println("2- Moderada");
            System.out.println("3- Alta");
            System.out.println("4- Urgente");
            esc=tc.nextInt();
            c.setPrioridade(esc);
        }while (esc<1||esc>4);
        c.setStatus(1);
        c.setDescricao(descricao);

        list=tDao.retornaLista();
        if (list!=null) {
            int menor = list.get(0).getNumTarefas();
            for (Tecnico t : list
                    ) {
                if(t.getNumTarefas()<=menor){
                    menor=t.getNumTarefas();
                    tVago=t;
                }
            }
            c.setIdTecnico(tVago.getId());
            c.setStatus(1);
            cDao.salvar(c);
            tDao.incrementaTarefa(tVago.getId());
        }
        else{
            System.out.println("Tecnicos indisponiveis.");
        }
    }

    public boolean listarChamados(int status, int idUsuario, int tipoUsuario){
        ArrayList<Chamado> list;
        list=cDao.retornaLista(idUsuario, tipoUsuario);


        if(list!=null) {
            System.out.println("Chamados registrados:");
            for (Chamado c : list
                    ) {
                if (c.getStatus() == status) {
                    System.out.println("");
                    System.out.println("Codigo: " + c.getId());
                    System.out.println("Descricao: " + c.getDescricao());
                    System.out.println("Prioridade: " + c.getPrioridade());
                    System.out.println("Codigo do Cliente:  " + c.getIdCliente());
                    System.out.println("Codigo do Tecnico: " + c.getIdTecnico());
                }
            }
            return true;
        }else{
            System.out.println("Nenhum chamado adicionado.");
            return false;
        }
    }

    public void finalizar(){
        int id;
        System.out.println("DIgite o codigo do chamado para finalizar:");
        id=tc.nextInt();
        cDao.finalizar(id);
    }

    public void alterar(){

        int id;
            System.out.println("Digite o codigo do chamado para alterar:");
            id = tc.nextInt();
            Chamado c = new Chamado();
            c = c.ler();
            c.setId(id);
            cDao.update(c);
    }

    public Chamado ler(){
        int esc;
        Chamado c = new Chamado();

        System.out.println("Descreva o problema:");
        descricao=tc.next();
        do{
            System.out.println("Escolha  prioridade:");
            System.out.println("1- Baixa");
            System.out.println("2- Moderada");
            System.out.println("3- Alta");
            System.out.println("4- Urgente");
            esc=tc.nextInt();
            c.setPrioridade(esc);
        }while (esc<1||esc>4);
        c.setStatus(1);
        c.setDescricao(descricao);

        return c;
    }

    public void alterarTecnicoChamado(){
        Tecnico t = new Tecnico();

        System.out.println("Digite o codigo do chamado a modificar:");
        int codChamado = tc.nextInt();
        System.out.println("Tecnicos disponiveis:");
        t.listarTecnicos();

        System.out.println("Escolha o novo tecnico atraves do codigo para o chamado:");
        int idTec = tc. nextInt();
        cDao.alterarTecnico(codChamado, idTecnico);

    }
}
