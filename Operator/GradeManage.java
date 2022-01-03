package Operator;
import Tool.Student;
import java.io.*;
import java.util.Scanner;

public class GradeManage implements Manage {

    private boolean flag=false; //判断程序是否结束的标志
    private static int N=50; //最大学生纪录数量
    private Student stu[];
    private static int n;  //当前记录次序
    private int m=0; //待增加的学生数量


    public GradeManage(Student[] stu){
        this.stu=stu;
        int cc=0;
        while(stu[cc]!=null){
            cc++;
        }
        this.n=cc;
    }

    Scanner sc=new Scanner(System.in);

    @Override
    public void help(){
        System.out.println("0.欢迎使用系统帮助！");
        System.out.println("1.初次进入系统后,请先选择增加学生信息;");
        System.out.println("2.按照菜单提示键入数字代号;");
        System.out.println("3.增加学生信息后,切记保存;");
        System.out.println("4.谢谢您的使用！");
    }


    @Override
    public void search() {
        String item;
        boolean flag = false;
        System.out.println("------------------");
        System.out.println("-----1.按学号查询-----");
        System.out.println("-----2.按姓名查询-----");
        System.out.println("-----3.退出本菜单-----");
        System.out.println("------------------");
        while(true){
            System.out.println("请选择子菜单编号：");
            item=sc.next();
            flag=false;
            switch(item){
                case "1":
                    searchbyid(false);
                    break;
                case "2":
                    searchbyname();
                case "3":
                    return;
                default:
                    System.out.println("请在1-3中选择：");
            }
        }



    }
    private void searchbyid(boolean flag){
        System.out.println("请输入要查询的学生学号：");
        String id=sc.next();
        if(!checkID(id)){
            System.out.println("学号有误或者重复请重新输入");
            return;
        }
        for(int i=0;i<stu.length;i++){
            if(stu[i].id.equals(id)){
                flag=true;
                System.out.println("学生学号   学生姓名     C语言成绩     高等数学成绩     大学英语成绩");
                System.out.println("--------------------------------------------------------------------");
                System.out.println(stu[i].id + "        "
                        + stu[i].name + "        "
                        + stu[i].gradeofC + "        "
                        + stu[i].gradeofMath + "           "
                        + stu[i].gradeofEnglish);
            }
        }
        if(flag==false)
            System.out.println("该学号不存在！");
    }

    private void searchbyname(){
        System.out.println("请输入要查询的学生姓名：");
        String name=sc.next();
        for(int i=0;i<stu.length;i++){
            if(stu[i].name.equals(name)){
                flag=true;
                System.out.println("学生学号   学生姓名    C语言成绩    高等数学成绩    大学英语成绩");
                System.out.println("--------------------------------------------------------------------");
                System.out.println(stu[i].id + "         "
                        + stu[i].name + "         "
                        + stu[i].gradeofC + "          "
                        + stu[i].gradeofMath + "            "
                        + stu[i].gradeofEnglish);
            }
        }
        if(flag==false)
            System.out.println("该学生姓名不存在！");

    }

    @Override
    public void modify() throws IOException {
        boolean flag = true;
        System.out.print("请输入要要修改的学生的学号:");
        String str = sc.next();
        int t = 0 ; // 循环外的变量记录要修改学生下标值
        String dataOfStudentOld = new String(); // 循环外的变量记录要修改学生的字符数据
        for (int i = 0; i < n; i++) {
            if (stu[i].id.equals(str)) {
                flag = false;
                t = i;
                // 定义字符串变量strT存储修改前学生的信息
                String strT = stu[t].id + " " + stu[t].name + " "
                        + stu[t].gradeofC + " " + stu[t].gradeofEnglish + " "
                        + stu[t].gradeofMath + "\r\n";
                dataOfStudentOld = strT;
                System.out.println("------------------");
                System.out.println("1.修改C语言成绩");
                System.out.println("2.修改高等数学成绩");
                System.out.println("3.修改大学英语成绩");
                System.out.println("0.保存修改信息并退出本菜单");
                System.out.println("------------------");
                while (true) {
                    System.out.println("请选择子菜单编号:");
                    String item = sc.next();
                    switch (item) {
                        case "1":
                            System.out.println("请输入新的C语言成绩:");
                            stu[i].gradeofC = Double.parseDouble(sc.next());
                            break;
                        case "2":
                            System.out.println("请输入新的高等数学成绩:");
                            stu[i].gradeofMath = Double.parseDouble(sc.next());
                            break;
                        case "3":
                            System.out.println("请输入新的大学英语成绩:");
                            stu[i].gradeofEnglish = Double.parseDouble(sc.next());
                            break;
                        case "0":
                            // 定义字符串变量dataOfStudentNew存储修改后学生的信息
                            String dataOfStudentNew = stu[t].id + " " + stu[t].name
                                    + " " + stu[t].gradeofC + " " + stu[t].gradeofEnglish
                                    + " " + stu[t].gradeofMath + "\r\n";
                            File f=new File("Student.txt");
                            FileReader fr=new FileReader(f);
                            char[] ch=new char[(int)f.length()];
                            fr.read(ch);
                            fr.close();
                            //替换substring
                            String str1=new String(ch);
                            str1=str1.replace(dataOfStudentOld,dataOfStudentNew);
                            //新字符串写入文件
                            FileWriter fw=new FileWriter(f);
                            fw.write(str1);
                            fw.close();
                            return;
                        default:
                            System.out.println("请在0-3之间选择");
                    }
                }
            }
        }
        if (flag==true)
            System.out.println("该学生不在系统内");
    }

    @Override
    public void insert() throws IOException {
        int j = n;//当前为第j个学生
        System.out.println("输入待添加的学生个数");
        String str = sc.next();
        /*if (!checkNum(str)) {
            System.out.println("输入的学生数量有误！");
            return;
        }*/
        m = Integer.parseInt(str);
        for (int i = j; i < j + m; i++) {
            stu[i] = new Student();
        }
        do {
            // 输入学号并判断
            System.out.println("请输入第" + (j - n + 1) + "个学生的学号(三位数):");
            stu[j].id = sc.next();
            if (!checkID(stu[j].id)) {
                System.out.println("输入有误或学号已存在，请重新输入该学生的信息");
                continue;
            }

            // 输入姓名
            System.out.println("请输入第" + (j - n + 1) + "个学生的姓名:");
            stu[j].name = sc.next();

            // 输入成绩并判断
            System.out.println("请输入第" + (j - n + 1) + "个学生的C语言成绩");
            stu[j].gradeofC = Double.parseDouble(sc.next());

            System.out.println("请输入第" + (j - n + 1) + "个学生的大学英语成绩:");
            stu[j].gradeofEnglish = Double.parseDouble(sc.next());

            System.out.println("请输入第" + (j - n + 1) + "个学生的高等数学成绩:");
            stu[j].gradeofMath = Double.parseDouble(sc.next());

            // 将新输入的信息存入文档
            FileWriter fw=new FileWriter("Student.txt",true);
            String strTemp=new String(stu[j].id+" "+stu[j].name+" "+
                    stu[j].gradeofC+" "+stu[j].gradeofEnglish+" "+stu[j].gradeofMath
                    +"\r\n");
            fw.write(strTemp);
            fw.close();
            j++;
        } while (j < n + m);
        n = m+n;
        System.out.println("信息添加完毕!");
        // 按照学号升序进行排序
        sort();


    }


    @Override
    public void delete() throws IOException {
        System.out.println("请输入要删除学生的学号:");
        String id = sc.next();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (stu[i].id.equals(id)) {
                flag = true;
                // 定义字符串变量dataOfStudent存储符合条件学生的信息
                String dataOfStudent = stu[i].id + " " + stu[i].name
                        + " " + stu[i].gradeofC + " " + stu[i].gradeofEnglish
                        + " " + stu[i].gradeofMath + "\r\n";
                // 删除的是最后一名学生
                if (i == n - 1) {
                    stu[i] = null;
                    n -= 1;
                }
                // 删除的不是最后一名学生
                else {
                    for (int j = i; j < n - 1; j++) {
                        stu[j] = stu[j + 1];
                    }
                    stu[n - 1] = null;
                    n -= 1;
                }
                // 对文件里该学生的信息进行删除
                File f = new File("Student.txt");
                FileReader fr=new FileReader(f);
                char[] ch=new char[(int) f.length()];
                fr.read(ch);
                fr.close();
                String str=new String();
                str=str.replace(dataOfStudent,"");
                FileWriter fw=new FileWriter(f);
                fw.write(str);
                fw.close();
            }
        }
        if (flag == false)
            System.out.println("该学号不存在！");
        if (flag == true)
            System.out.println("删除成功,显示结果请选择菜单");
    }

    @Override
    public void display() {
        System.out.println("共有" + n + "位学生的信息:");

        if (0 != n) {
            System.out.println("学生学号  学生姓名  C语言成绩  高等数学  大学英语成绩");
            System.out
                    .println("----------------------------------------------------------");
            for (int i = 0; i < n; i++) {
                System.out.println(stu[i].id + "        " + stu[i].name + "        " +
                        stu[i].gradeofC + "         " + stu[i].gradeofEnglish + "         "
                        + stu[i].gradeofMath+"\r" );
            }
        }

    }

    //判断输入的学生数量是否合法
    private boolean checkNum(String str){
        char[] ch=str.toCharArray();
        //check数据类型合法
        for(int i=0;i<ch.length;i++)
            if(ch[i]<48||ch[i]>57)
                return false;
        //check数据范围合法
        int num= Integer.parseInt(str);
        if(num<=0||num>N-n)
            return false;
        return true;
    }

    //判断输入的学号是否合法且唯一
    private boolean checkID(String str){
        char[] ch=str.toCharArray();
        //数据长度
        if(ch.length!=3)
            return false;
        //check数据类型合法
        /*for(int i=0;i<ch.length;i++)
            if(ch[i]<48||ch[i]>57)
                return false;
        //check学号唯一性
        for(int i=0;i<stu.length;i++){
            if(stu[i].id.equals(str))
                return false;
        }*/
        return true;
    }

    /**
     * 按学号升序排序
     */
    private void sort() throws IOException {
        int[] idArray=new int[n];
        Student studTemp;
        for(int i=0;i<n;i++){
            idArray[i]=Integer.parseInt(stu[i].id);
        }
        //对Student对象进行选择排序
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(idArray[i]>idArray[j]){
                    studTemp=stu[i];
                    stu[i]=stu[j];
                    stu[j]=studTemp;
                }
            }
        }
        //按学号升序将学生信息写入文件
        FileWriter fw=new FileWriter("student.txt");
        for(int i=0;i<n;i++){
            fw.write(stu[i].id + " " + stu[i].name + " " +
                    stu[i].gradeofC + " " + stu[i].gradeofEnglish + " "
                    + stu[i].gradeofMath+"\r\n");
        }
        fw.close();
    }


}
