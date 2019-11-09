package Server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class History implements Comparable<History> {
    Date date;
    String name;
    String message;

    public History(Date date, String name, String message) {
        this.date = date;
        this.name = name;
        this.message = message;
    }

    public int compareTo(History h){
        return date.compareTo(h.date);
    }

    public String GetNickAndMessage(){
        return name + ": " + message;
    }
}


public class AuthService {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void Connect(){
        try {
            Class.forName("org.sqlite.JDBC"); //выбираем драйвер
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db"); //инициализация
            stmt = connection.createStatement();

            //создаем таблицу для хранения истории
            CreateHistoryTable();

            //AddMessageToHistory("Боб1", "сообщение1");
            //AddMessageToHistory("Боб2", "сообщение2");
            //AddMessageToHistory("Боб3", "сообщение3");

            //GetHistoryFromBD();
            //DeleteAllMessageInHistoryByNick("nick1");
            //ChangeRecordInHistoryByDatetime("2019-10-22 09:34:14");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) throws SQLException {
        String sql = String.format("SELECT nickname FROM main where login = '%s' and password = '%s'", login, pass);
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()) {
            return rs.getString(1); //индексанция начинается с 1 (столбик)
        }
        return null;
    }

    public static String getIdByNick(String nick) throws SQLException {
        String sql = String.format("SELECT id FROM main where nickname = '%s'", nick);
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()) {
            return rs.getString(1); //индексанция начинается с 1 (столбик)
        }
        return null;
    }

    public static List<String> getArrBlackList(String id) throws SQLException {
        List<String> blackList = new ArrayList<>();
        String sql = String.format("SELECT * FROM blackList where id = '%s'", id);
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            blackList.add(rs.getString(2));
        }
        return blackList;
    }

    public static void addUserToBlackList (String id, String nick) throws SQLException {
        String sql = String.format("INSERT INTO blackList (id, users) VALUES ('%s','%s')", id,nick);
        stmt.execute(sql);
    }


    public static void Disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CreateHistoryTable() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF not EXISTS history (" +
                "datetime TEXT," +
                "name TEXT," +
                "message TEXT)");
    }

    public static void AddMessageToHistory(String name, String message) throws SQLException {
        String dateString = format.format(new Date());
        System.out.println(dateString);

        pstmt = connection.prepareStatement("INSERT INTO history (datetime, name, message)" +
                        " VALUES (?,?,?)");
        pstmt.setString(1, dateString);
        pstmt.setString(2, name);
        pstmt.setString(3, message);

        pstmt.execute();
    }




    public static ArrayList<String> GetHistoryFromBD() throws SQLException {
        ArrayList<String> listMessage = new ArrayList<>();

        TreeSet<History> history = new TreeSet<History>();
        ResultSet rs = stmt.executeQuery("Select * From history;");

        while (rs.next()){
            try {
                Date date = format.parse(rs.getString(1));
                History el = new History(date, rs.getString(2), rs.getString(3));
                history.add(el);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        history.forEach(el -> listMessage.add(new String(el.name + ": " + el.message)));

        //history.forEach(awd -> System.out.println(awd.date + " " + awd.name));
        return listMessage;
    }

    public static void DeleteAllMessageInHistoryByNick(String name) throws SQLException {
        pstmt = connection.prepareStatement("delete from history where name=?");
        pstmt.setString(1, name);
        pstmt.executeUpdate();
    }

    public static void ChangeRecordInHistoryByDatetime(String datetime) throws SQLException {
        pstmt = connection.prepareStatement("update history set message=? where datetime=?");
        pstmt.setString(1, "что-то новое");
        pstmt.setString(2, datetime);
        pstmt.executeUpdate();
    }
}
