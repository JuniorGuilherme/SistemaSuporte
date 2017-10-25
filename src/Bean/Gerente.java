package Bean;

public class Gerente extends Pessoa {
    public Gerente(String nome, String loginEmail, String telefone, String senha, int tipoUsuario){
        this.setNome(nome);
        this.setLoginEmail(loginEmail);
        this.setTelefone(telefone);
        this.setSenha(senha);
        this.setTipoUsuario(tipoUsuario);
    }
}
