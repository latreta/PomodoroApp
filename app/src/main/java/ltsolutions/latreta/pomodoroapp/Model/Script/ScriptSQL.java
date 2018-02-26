package ltsolutions.latreta.pomodoroapp.Model.Script;

/**
 * Created by LaTreTz on 26/05/2016.
 */
public class ScriptSQL {
    public static String generate(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS TAREFA ( ");
        sqlBuilder.append("_id               INTEGER NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME             VARCHAR(200), ");
        sqlBuilder.append("CATEGORIA        VARCHAR(100), ");
        sqlBuilder.append("DURACAO          VARCHAR(2) ");
        sqlBuilder.append(");");
        return sqlBuilder.toString();
    }
    public static String generateCat(){
        StringBuilder sqlBuilder2 = new StringBuilder();
        sqlBuilder2.append(" CREATE TABLE IF NOT EXISTS CATEGORIA ( ");
        sqlBuilder2.append("_id               INTEGER NOT NULL ");
        sqlBuilder2.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder2.append("NOME             VARCHAR(200) ");
        sqlBuilder2.append(");");
        return sqlBuilder2.toString();
    }
}
