package Tool;

import Operator.GradeManage;
import Operator.Manage;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public boolean flag;

    public Menu() throws IOException,InterruptedException {

        System.out.println("****************************************************");
        System.out.println("        *           欢迎来到学生成绩管理系统                     *");
        System.out.println("****************************************************");
        Thread.sleep(200);
        System.out.println("********************系统功能菜单***********************");
        System.out.println("*  0.系统帮助及说明  *  * 1.刷新学生信息   *  * 2.查询学生信息    *  ");
        System.out.println("****************************************************   ");
        System.out.println("*  3.修改学生信息      *  * 4.增加学生信息   *  * 5.按学号删除信息 * ");
        System.out.println("*****************************************************     ");
        System.out.println("*  6.显示当前信息      *  * 7.保存当前学生信息* *  8.退出系统         *    ");
        System.out.println("*****************************************************    ");
        System.out.println("请选择菜单编号:");


        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();

        //error:Non-static method cannot be referenced from a static context
        //Reason:You can't call something that doesn't exist. Since you haven't created an object,
        //      the non-static method doesn't exist yet. A static method (by definition) always exists.
        IOfile IO=new IOfile();
        Student[] stu=IO.readFile();
        Manage method=new GradeManage(stu);

        switch(s){
            case "0" :
                method.help();
                break;
            case "2":
                method.search();
                break;
            case "3":
                method.modify();
                break;
            case"4":
                method.insert();
                break;
            case"5":
                method.delete();
                break;
            case"6":
                method.display();
                break;
            case"8":
                flag = false;
                scanner.close();
                System.out.println("您已经离开！");
                break;
            default:
                System.out.println("请重新在0-8之间选择：");
        }
    }
}
