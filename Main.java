import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Main {
    static void showMenu() {
        System.out.println("====== 学生成绩管理系统 =====");
        System.out.println("1.录入成绩");
        System.out.println("2.显示全部");
        System.out.println("3.按分数排序");
        System.out.println("4.退出");
    }
static void sortbyscore(ArrayList<Student> students){
        Collections.sort(students);
        showAll(students);
}

static void addStudent(ArrayList<Student> students, Scanner sc){
        System.out.println("请输入姓名");
        String name = sc.next();
        while(true){
        System.out.println("请输入分数");
        try{
            int score = sc.nextInt();
            students.add(new Student(name,score));
            System.out.println("录入成功！");
            return;
        } catch(InputMismatchException e){
            System.out.println("请输入数字");
            sc.nextLine();
        }
    }
}

static void showAll(ArrayList<Student> students){
        if(students.size() == 0){
            System.out.println("还没有录入学生");
            return;
        }
        for(int i = 0; i < students.size() ; i++){
        System.out.println((i + 1) + ". " + students.get(i));
    }
}

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
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
                addStudent(students, sc);
            }
            else if (choice == 2) {
                showAll(students);
            }
            else if(choice == 3) {
                sortbyscore(students);
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