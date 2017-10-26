package View;

import Bean.*;
import Connection.DbUtils;
import Dao.ChamadoDao;
import Dao.ClienteDao;
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

        Gerente gMaster = new Gerente();
        gMaster.setNome("Jr");
        gMaster.setSenha("123");
        gMaster.setTipoUsuario(3);
        gMaster.setTelefone("48991944425");
        gMaster.setLoginEmail("jrg_compras");
        gDao.salvar(gMaster);

        do{
            System.out.println("1- Login");
            System.out.println("2- Cadastre-se");
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

                    if(usuarioLogado!=null){
                        int op2;
                        do{

                            System.out.println("Selecione a Area:");
                            System.out.println("1- Cliente");
                            System.out.println("2- Tecnico");
                            System.out.println("3- Gerente");
                            System.out.println("0- Sair");

                            op2 = tc.nextInt();

                            switch (op2){
                                case 1:{
                                    if(usuarioLogado.getTipoUsuario()>=1 && usuarioLogado.getTipoUsuario()!=2){
                                        int op3;
                                        do{
                                            Chamado c = new Chamado();
                                            ClienteDao cDao = new ClienteDao();

                                            System.out.println("MENU CLIENTE");
                                            System.out.println("1- Adicionar chamado");
                                            System.out.println("2- Alterar Chamado");
                                            System.out.println("3- Cancelar Chamado");
                                            System.out.println("4- Visualizar chamados");
                                            System.out.println("0- Sair");
                                            op3 = tc.nextInt();

                                            switch (op3){
                                                case 1:{
                                                    if(cDao.retornaLista()!=null) {
                                                        c.abrirChamado(usuarioLogado.getTipoUsuario());
                                                    }else{
                                                        System.out.println("Nao existem clientes para chamados.");
                                                    }
                                                }
                                                break;
                                                case 2:{
                                                    if(c.listarChamados(1, usuarioLogado.getId(), usuarioLogado.getTipoUsuario())){
                                                        c.alterar();
                                                    }
                                                }
                                                break;
                                                case 3:{
                                                    c.listarChamados(1, usuarioLogado.getId(), usuarioLogado.getTipoUsuario());
                                                    System.out.println("");
                                                    c.finalizar();
                                                }
                                                break;
                                                case 4:{
                                                    if(usuarioLogado.getTipoUsuario()!=3) {
                                                        c.listarChamados(1, usuarioLogado.getId(), usuarioLogado.getTipoUsuario());
                                                    }else{
                                                        System.out.println("Voce é um gerente, entre como cliente para ver os chamados do mesmo.");
                                                    }
                                                }
                                                break;
                                                case 0:{
                                                    System.out.println("Menu Suspenso.");
                                                }
                                            }
                                        }while(op3!=0);
                                    }else{
                                        System.out.println("Acesso negado. Nivel de permissao insuficiente.");
                                    }
                                }
                                break;
                                case 2:{
                                    int op4;
                                    Chamado c = new Chamado();
                                    if(usuarioLogado.getTipoUsuario()>=2){
                                        do{
                                            System.out.println("MENU TECNICO");
                                            System.out.println("1- Tarefas Pendentes");
                                            System.out.println("2- Tarefas Realizadas");
                                            System.out.println("3- Finalizar Tarefas");
                                            System.out.println("0- Sair");
                                            op4=tc.nextInt();

                                            switch (op4){
                                                case 1:{
                                                    c.listarChamados(1, usuarioLogado.getId(), usuarioLogado.getTipoUsuario());
                                                }
                                                break;
                                                case 2:{
                                                    c.listarChamados(0, usuarioLogado.getId(), usuarioLogado.getTipoUsuario());
                                                }
                                                break;
                                                case 3:{
                                                    c.finalizar();
                                                }
                                            }
                                        }while(op4!=0);
                                    }else{
                                        System.out.println("Acesso negado. Nivel de permissao insuficiente.");
                                    }
                                }
                                break;
                                case 3:{
                                    int op5;
                                    Gerente g = new Gerente();
                                    Cliente c = new Cliente();
                                    Tecnico t = new Tecnico();
                                    Chamado ch = new Chamado();

                                    if(usuarioLogado.getTipoUsuario()>=3){
                                        do{
                                            System.out.println("MENU GERENTE");
                                            System.out.println("1- Cadastrar Clientes");
                                            System.out.println("2- Cadastrar Tecnicos");
                                            System.out.println("3- Cadastrar Gerentes");
                                            System.out.println("4- Visualizar Tecnicos");
                                            System.out.println("5- Visualizar Clientes");
                                            System.out.println("6- Visualizar Gerentes");
                                            System.out.println("7- Alterar Gerentes");
                                            System.out.println("8- Alterar Clientes");
                                            System.out.println("9- Alterar Tecnicos");
                                            System.out.println("10- Remover Gerentes");
                                            System.out.println("11- Remover Tecnicos");
                                            System.out.println("12- Remover Clientes");
                                            System.out.println("13- Alterar Tecnico de Chamados");
                                            System.out.println("0- Sair");
                                            op5 = tc.nextInt();

                                            switch (op5){
                                                case 1:{
                                                    c.cadastrar();
                                                }
                                                break;
                                                case 2:{
                                                    t.cadastrar();
                                                }
                                                break;
                                                case 3:{
                                                    g.cadastrar();
                                                }
                                                break;
                                                case 4:{
                                                    t.listarTecnicos();
                                                }
                                                break;
                                                case 5:{
                                                    c.listarClientes();
                                                }
                                                break;
                                                case 6:{
                                                    g.listarGerentes();
                                                }
                                                break;
                                                case 7:{
                                                    g.alterar();
                                                }
                                                break;
                                                case 8:{
                                                    c.alterar();
                                                }
                                                break;
                                                case 9:{
                                                    t.alterar();
                                                }
                                                break;
                                                case 10:{
                                                    g.remover();
                                                }
                                                break;
                                                case 11:{
                                                    t.remover();
                                                }
                                                break;
                                                case 12:{
                                                    c.remover();
                                                }
                                                break;
                                                case 13:{
                                                    if(ch.listarChamados(1,usuarioLogado.getId(), usuarioLogado.getTipoUsuario())){
                                                        ch.alterarTecnicoChamado();
                                                    }


                                                }
                                                break;
                                                case 0:{
                                                    System.out.println("Menu suspenso.");
                                                }
                                            }
                                        }while (op5!=0);
                                    }else{
                                        System.out.println("Acesso negado. Nivel de permissao insuficiente.");
                                    }
                                }
                                break;
                                case 0:{
                                    System.out.println("Menu Suspenso.");
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
