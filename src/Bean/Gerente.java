package Bean;

import Dao.GerenteDao;

import java.util.ArrayList;

public class Gerente extends Pessoa {
    GerenteDao gDao = new GerenteDao();
    public void cadastrar(){
        Pessoa p = new Pessoa();
        p.cadastrar();
        Gerente g = new Gerente();
        g.setNome(p.getNome());
        g.setTelefone(p.getTelefone());
        g.setLoginEmail(p.getLoginEmail());
        g.setSenha(p.getSenha());
        g.setTipoUsuario(p.getTipoUsuario());
        GerenteDao gDao = new GerenteDao();
        gDao.salvar(g);
    }
    public void listarGerentes(){
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
        }else{
            System.out.println("Nenhum gerente adicionado.");
        }
    }
}
