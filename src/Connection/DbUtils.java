package Connection;

public class DbUtils {
    DbHelper sqlite;

    public DbUtils(){
        sqlite = new DbHelper();
    }
    public  void criarDB()
    {
        String sql = "CREATE TABLE gerente " +
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
                "idCliente int not null," +
                "status int not null," +
                "idTecnico int not null," +
                "FOREIGN KEY(idTecnico) REFERENCES tecnico(id));";
        sqlite.executarSQL(sql);

        String sqlTecnico = " create table tecnico" +
                " (ID  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " nome  varchar(45) NOT NULL," +
                " telefone  varchar(45) NOT NULL, " +
                " loginEmail varchar(45) NOT NULL, " +
                " senha varchar(45) NOT NULL, " +
                " tipoUsuario INT not null," +
                " numTarefas int not null); ";
        sqlite.executarSQL(sqlTecnico);

        String sqlCliente = " create table cliente" +
                " (ID  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                " nome  varchar(45) NOT NULL," +
                " telefone  varchar(45) NOT NULL, " +
                " loginEmail varchar(45) NOT NULL, " +
                " senha varchar(45) NOT NULL, " +
                " tipoUsuario INT not null); ";
        sqlite.executarSQL(sqlCliente);
    }

    public  void dropTable() {

        String sql = "DROP TABLE IF EXISTS PESSOA; DROP TABLE IF EXISTS CHAMADO; DROP TABLE IF EXISTS TECNICO; DROP TABLE IF EXISTS gerente; DROP TABLE IF EXISTS cliente;";
        sqlite.executarSQL(sql);
    }
}
