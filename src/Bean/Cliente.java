package Bean;

import Dao.ClienteDao;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    ClienteDao cDao = new ClienteDao();

    public void cadastrar(){
        Pessoa p = new Pessoa();
        p.cadastrar();
        Cliente c = new Cliente();
        c.setNome(p.getNome());
        c.setTelefone(p.getTelefone());
        c.setLoginEmail(p.getLoginEmail());
        c.setSenha(p.getSenha());
        c.setTipoUsuario(p.getTipoUsuario());

        cDao.salvar(c);
    }

    public void listarClientes(){
        ArrayList<Cliente> list;
        list= cDao.retornaLista();

        if(list!=null) {
            for (Cliente c : list
                    ) {
                System.out.println("");
                System.out.println("Codigo: " + c.getId());
                System.out.println("Nome: " + c.getNome());
                System.out.println("Email: " + c.getLoginEmail());
                System.out.println("Telefone: " + c.getTelefone());
                System.out.println("Senha: " + c.getSenha());
                System.out.println("Nivel de Permissao: " + c.getTipoUsuario());
            }
        }else{
            System.out.println("Nenhum cliente adicionado.");
        }
    }
}
