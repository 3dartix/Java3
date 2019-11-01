package Lesson_2;

import java.sql.*;

public class MainDB {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void main(String[] args) throws SQLException {
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        getDataDb();
        close();
    }

    public static void getDataDb() throws SQLException {
        long t = System.currentTimeMillis();

//
//        stmt.executeUpdate("CREATE TABLE students (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "name TEXT," +
//                "score TEXT)");

//        connection.setAutoCommit(false);
//
//        for (int i = 0; i < 1000; i++) {
//            String res = "Bob" + i;
////            String sql = String.format("INSERT INTO students (name, score)\n" +
////                            "VALUES ('%s', %d)", res, i);
//
//            stmt.addBatch(String.format("INSERT INTO students (name, score)\n" +
//                    "VALUES ('%s', %d)", res, i));
//
//            stmt.executeUpdate(String.format("INSERT INTO students (name, score)\n" +
//                    "VALUES ('%s', %d)", res, i));
//        }
//
//
//        stmt.executeBatch();
//
//        connection.setAutoCommit(true);
//
//        System.out.println(System.currentTimeMillis() - t);
//
//        pstmt = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?)");
//        pstmt.setString(1, "Bob; DROP table students;");
//        pstmt.setInt(2, 20);
//        pstmt.execute();

        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1', 10)");
        Savepoint sql = connection.setSavepoint();

        try {
            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob2', 20)");
        }catch (Exception e) {
            connection.rollback(sql);
        }

        connection.setAutoCommit(true);
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 30)");

       // SELECT * FROM students where score = 10;  DROP table students

//        ResultSet rs = stmt.executeQuery("SELECT * FROM students where score = 10");
//        ResultSetMetaData rsmd = rs.getMetaData();
//
//        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//            System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnType(i));
//        }
//
//        while (rs.next()){
//
//            System.out.println(rs.getInt(1) + rs.getString("name"));
//           // System.out.println(rsmd.getColumnName());
//        }
    }

    public static void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public static void close() throws SQLException {
        connection.close();
    }
}
