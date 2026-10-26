import java.util.Scanner;

public class Student implements Comparable<Student> {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return "姓名" + name + ",分数" + score;
    }
    public int compareTo(Student o){
        return Integer.compare(o.score,this.score);
    }
}