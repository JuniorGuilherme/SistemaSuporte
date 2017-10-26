package Bean;

import Dao.TecnicoDao;

import java.util.ArrayList;
import java.util.Scanner;

public class Tecnico extends Pessoa {
    private int numTarefas;
    Scanner tc = new Scanner(System.in);
    TecnicoDao tDao = new TecnicoDao();

    public int getNumTarefas() {
        return numTarefas;
    }

    public void setNumTarefas(int numTarefas) {
        this.numTarefas = numTarefas;
    }

    public Tecnico ler(){
        Pessoa p = new Pessoa();
        p.cadastrar();
        Tecnico t = new Tecnico();
        t.setNome(p.getNome());
        t.setTelefone(p.getTelefone());
        t.setLoginEmail(p.getLoginEmail());
        t.setSenha(p.getSenha());
        t.setTipoUsuario(p.getTipoUsuario());
        t.setNumTarefas(0);
        return t;
    }

    public void cadastrar(){
        tDao.salvar(ler());
    }

    public boolean listarTecnicos(){
        ArrayList<Tecnico> list = new ArrayList();
        list=tDao.retornaLista();
        if(list!=null) {
            for (Tecnico t : list
                    ) {
                System.out.println("");
                System.out.println("Codigo: " + t.getId());
                System.out.println("Nome: " + t.getNome());
                System.out.println("Email: " + t.getLoginEmail());
                System.out.println("Telefone: " + t.getTelefone());
                System.out.println("Senha: " + t.getSenha());
                System.out.println("Nivel de Permissao: " + t.getTipoUsuario());
                System.out.println("Numero de Tarefas: " + t.getNumTarefas());
            }
            return true;
        }else{
            System.out.println("Nenhum tecnico adicionado.");
            return false;
        }
    }

    public void alterar(){

        int id;
        if(listarTecnicos()) {
            System.out.println("Digite o codigo do gerente para alterar:");
            id = tc.nextInt();
            Tecnico t = new Tecnico();
            t = t.ler();
            t.setId(id);
            tDao.update(t);
        }else{

        }
    }

    public void remover(){
        int id;
        if(listarTecnicos()) {
            System.out.println("");
            System.out.println("Digite o codigo do tecnico para remover:");
            id = tc.nextInt();
            tDao.remover(id);
        }else{

        }
    }
}
