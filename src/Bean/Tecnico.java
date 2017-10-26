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

    public void cadastrar(){
        Pessoa p = new Pessoa();
        p.cadastrar();
        Tecnico t = new Tecnico();
        t.setNome(p.getNome());
        t.setTelefone(p.getTelefone());
        t.setLoginEmail(p.getLoginEmail());
        t.setSenha(p.getSenha());
        t.setTipoUsuario(p.getTipoUsuario());
        t.setNumTarefas(0);

        tDao.salvar(t);
    }

    public void listarTecnicos(){
        ArrayList<Tecnico> list = new ArrayList();
        list=tDao.retornaLista();

        for (Tecnico t: list
                ) {
            System.out.println("");
            System.out.println("Codigo: "+t.getId());
            System.out.println("Nome: "+t.getNome());
            System.out.println("Email: "+t.getLoginEmail());
            System.out.println("Telefone: "+t.getTelefone());
            System.out.println("Senha: "+t.getSenha());
            System.out.println("Nivel de Permissao: "+t.getTipoUsuario());
            System.out.println("Numero de Tarefas: "+t.getNumTarefas());
        }
    }
}
