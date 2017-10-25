package Bean;

import Dao.GerenteDao;

public class Gerente extends Pessoa {
    public void cadastrar(){
        Pessoa p = new Pessoa();
        p.cadastrar();
        this.setNome(p.getNome());
        this.setTelefone(p.getTelefone());
        this.setLoginEmail(p.getLoginEmail());
        this.setSenha(p.getSenha());
        this.setTipoUsuario(p.getTipoUsuario());
        GerenteDao gDao = new GerenteDao();
        gDao.salvar(this);
    }
}
