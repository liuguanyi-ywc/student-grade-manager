import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Properties;
import java.sql.*;


public class Main {
    static void showMenu() {
        System.out.println("====== 学生成绩管理系统 ======");
        System.out.println("1.录入成绩");
        System.out.println("2.显示全部");
        System.out.println("3.按分数排序");
        System.out.println("4.退出");
    }
static void sortbyscore(Connection conn) throws SQLException {
    PreparedStatement ps = conn.prepareStatement(
            "SELECT name,score FROM student ORDER BY score DESC");
    ResultSet rs = ps.executeQuery();


    boolean hasData = false;
    int i = 0;
    while(rs.next()){
        System.out.println((i + 1) + ". " + rs.getString("name") + " " + rs.getDouble("score"));
        i++;
        hasData = true;
    }
    if(hasData == false){
        System.out.println("还没有录入学生");
    }

}

static void addStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.println("请输入姓名");
        String name = sc.next();
        while(true){
        System.out.println("请输入分数");
        try{
            int score = sc.nextInt();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student (name, score) VALUES (?,?) ");
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.executeUpdate();
            System.out.println("录入成功！");
            return;
        } catch(InputMismatchException e){
            System.out.println("请输入数字");
            sc.nextLine();
        }
    }
}

static void showAll(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
        "SELECT name,score FROM student");
        ResultSet rs = ps.executeQuery();


        boolean hasData = false;
        int i = 0;
        while(rs.next()){
            System.out.println((i + 1) + ". " + rs.getString("name") + " " + rs.getDouble("score"));
            i++;
            hasData = true;
        }
        if(hasData == false){
            System.out.println("还没有录入学生");
        }

    }

    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.load(new FileInputStream("db.properties"));
        Connection conn = DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("user"),
                props.getProperty("password"));
        Scanner sc = new Scanner(System.in);
        while (true) {
            showMenu();
            int choice;
            try{
                choice = sc.nextInt();
            }  catch(InputMismatchException e){
                System.out.println("请输入数字");
                sc.nextLine();
                continue;
            }
            if (choice == 1) {
                addStudent(conn, sc);
            }
            else if (choice == 2) {
                showAll(conn);
            }
            else if(choice == 3) {
                sortbyscore(conn);
            }
            else if (choice == 4) {
                break;
            }
            else{
                System.out.println("「没有这个选项，请重新输入！」");
            }
        }
    }
}
