package Bean;

import Dao.GerenteDao;

import java.util.ArrayList;

public class Gerente extends Pessoa {
    GerenteDao gDao = new GerenteDao();
    public Gerente ler(){
        Pessoa p = new Pessoa();
        p.cadastrar();
        Gerente g = new Gerente();
        g.setNome(p.getNome());
        g.setTelefone(p.getTelefone());
        g.setLoginEmail(p.getLoginEmail());
        g.setSenha(p.getSenha());
        g.setTipoUsuario(p.getTipoUsuario());
        return g;
    }
    public void cadastrar(){
        gDao.salvar(ler());
    }
    public boolean listarGerentes(){
        ArrayList<Gerente> list = new ArrayList();
        list=gDao.retornaLista();

        if(list!=null) {
            for (Gerente g: list
                    ) {
                System.out.println(" ");
                System.out.println("Codigo: "+g.getId());
                System.out.println("Nome: "+g.getNome());
                System.out.println("Email: "+g.getLoginEmail());
                System.out.println("Telefone: "+g.getTelefone());
                System.out.println("Senha: "+g.getSenha());
                System.out.println("Nivel de Permissao: "+g.getTipoUsuario());
            }
            return true;
        }else{
            System.out.println("Nenhum gerente adicionado.");
            return false;
        }
    }

    public void alterar(){

        int id;
        if(listarGerentes()) {
            System.out.println("Digite o codigo do gerente para alterar:");
            id = tc.nextInt();
            Gerente g = new Gerente();
            g = g.ler();
            g.setId(id);
            gDao.update(g);
        }else{

        }
    }

    public void remover(){
        int id;
        if(listarGerentes()) {
            System.out.println("Digite o codigo do gerente para remover:");
            id = tc.nextInt();
            gDao.remover(id);
        }else{

        }
    }
}
