package Bean;

import Dao.ClienteDao;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    ClienteDao cDao = new ClienteDao();

    public Cliente ler(){
        Pessoa p = new Pessoa();
        p.cadastrar();
        Cliente c = new Cliente();
        c.setNome(p.getNome());
        c.setTelefone(p.getTelefone());
        c.setLoginEmail(p.getLoginEmail());
        c.setSenha(p.getSenha());
        c.setTipoUsuario(p.getTipoUsuario());
        return c;
    }

    public void cadastrar(){
        cDao.salvar(ler());
    }

    public boolean listarClientes(){
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
            return true;
        }else{
            System.out.println("Nenhum cliente adicionado.");
            return false;
        }
    }

    public void alterar(){
        int id;
        if(listarClientes()) {
            System.out.println("Digite o codigo do gerente para alterar:");
            id = tc.nextInt();
            Cliente c = new Cliente();
            c = c.ler();
            c.setId(id);
            cDao.update(c);
        }else{

        }
    }

    public void remover(){
        int id;
        if(listarClientes()) {
            System.out.println("");
            System.out.println("Digite o codigo do cliente para remover:");
            id = tc.nextInt();
            cDao.remover(id);
        }else{

        }
    }
}
