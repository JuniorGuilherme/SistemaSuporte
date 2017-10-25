package View;

import Bean.Chamado;
import Bean.Gerente;
import Bean.Pessoa;
import Bean.Tecnico;
import Connection.DbUtils;
import Dao.ChamadoDao;
import Dao.GerenteDao;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        DbUtils bd = new DbUtils();
        bd.dropTable();
        bd.criarDB();
        GerenteDao gDao = new GerenteDao();
        Scanner tc = new Scanner(System.in);
        Pessoa usuarioLogado = new Pessoa();
        int op;

        Gerente g = new Gerente("Jr", "jrg_compras","48991944425", "123",2);
        gDao.salvar(g);

        do{
            System.out.println("1- Login");
            System.out.println("0- Sair");

            System.out.println("Opcao:");
            op=tc.nextInt();

            switch (op){
                case 1:{
                    String nome;
                    String senha;
                    Pessoa p = new Pessoa();


                    System.out.println("Usuario:");
                    nome=tc.next();
                    System.out.println("Senha:");
                    senha=tc.next();

                    usuarioLogado = p.validaLogin(nome, senha);

                    if(usuarioLogado.getNome()!=null){
                        int op2;
                        do{

                            System.out.println("Selecione a Area:");
                            System.out.println("1- Cliente");
                            System.out.println("2- Tecnico");
                            System.out.println("3- Gerente");

                            op2 = tc.nextInt();

                            switch (op2){
                                case 1:{
                                    if(usuarioLogado.getTipoUsuario()>=1){
                                        int op3;
                                        do{
                                            System.out.println("MENU CLIENTE");
                                            System.out.println("1- Adicionar chamado");
                                            System.out.println("2- Alterar Chamado");
                                            System.out.println("3- Cancelar Chamado");
                                            System.out.println("4- Visualizar chamados");
                                            System.out.println("0- Sair");
                                            op3 = tc.nextInt();

                                            switch (op3){
                                                case 1:{
                                                    String descricao;
                                                    int esc;


                                                    System.out.println("Descreva o problema:");
                                                    descricao=tc.next();
                                                    do{
                                                        System.out.println("Escolha prioridade:");
                                                        System.out.println("1- Baixa");
                                                        System.out.println("2- Moderada");
                                                        System.out.println("3- Alta");
                                                        System.out.println("4- Urgente");
                                                        esc=tc.nextInt();
                                                    }while (esc<1||esc>4);

                                                    Chamado c = new Chamado(descricao, esc, usuarioLogado.getId());
                                                    c.abrirChamado(c);
                                                }
                                                break;
                                                case 4:{
                                                    Chamado c = new Chamado(usuarioLogado.getId());
                                                    c.listarChamados();
                                                }
                                            }
                                        }while(op2!=0);
                                    }else{
                                        System.out.println("Acesso negado. Nivel de permissao insuficiente.");
                                    }
                                }
                                break;
                                case 2:{
                                    int op4;
                                    if(usuarioLogado.getTipoUsuario()>=2){
                                        do{
                                            System.out.println("MENU TECNICO");
                                            System.out.println("1- Cadastrar Tecnicos");
                                            System.out.println("2- Remover Tecnicos");
                                            System.out.println("3- Atualizar");
                                            System.out.println("4- Visualizar Tecnicos");
                                            System.out.println("0- Sair");
                                            op4=tc.nextInt();

                                            switch (op4){
                                                case 1:{
                                                    Tecnico t = new Tecnico();
                                                    t.cadastrar();
                                                }
                                                break;
                                                case 4:{
                                                    Tecnico t = new Tecnico();
                                                    t.listarTecnicos();
                                                }
                                            }
                                        }while(op4!=0);
                                    }else{
                                        System.out.println("Acesso negado. Nivel de permissao insuficiente.");
                                    }
                                }
                                break;
                                case 3:{
                                    if(usuarioLogado.getTipoUsuario()>=3){
                                        System.out.println("MENU GERENTE");
                                    }else{
                                        System.out.println("Acesso negado. Nivel de permissao insuficiente.");
                                    }
                                }
                            }
                        }while (op2!=0);
                    }
                }
                break;
                case 0:{
                    System.out.println("Sistema encerrado.");
                }
            }


        }while(op!=0);
    }
}
