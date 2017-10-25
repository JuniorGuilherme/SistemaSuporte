package Connection;

public class DbUtils {
    DbHelper sqlite;

    public DbUtils(){
        sqlite = new DbHelper();
    }
    public  void criarDB()
    {
        String sql = "CREATE TABLE PESSOA " +
                "(ID  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " nome  varchar(45) NOT NULL, " +
                " telefone  varchar(45) NOT NULL, " +
                " loginEmail varchar(45) NOT NULL, " +
                " senha varchar(45) NOT NULL, " +
                " tipoUsuario INT not null);" +
                "create table chamado" +
                "(ID  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "prioridade int not null," +
                "descricao varchar(150) not null," +
                "idCliente int not null);";
        sqlite.executarSQL(sql);

        String sql2 = " create table tecnico" +
                " (ID  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " nome  varchar(45) NOT NULL," +
                " telefone  varchar(45) NOT NULL, " +
                " loginEmail varchar(45) NOT NULL, " +
                " senha varchar(45) NOT NULL, " +
                " tipoUsuario INT not null), " +
                " numTarefas int not null; ";
        sqlite.executarSQL(sql2);
    }

    public  void dropTable() {

        String sql = "DROP TABLE IF EXISTS PESSOA; DROP TABLE IF EXISTS CHAMADO; DROP TABLE IF EXISTS TECNICO;";
        sqlite.executarSQL(sql);
    }
}
