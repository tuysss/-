package Tool;

import java.io.*;

public class IOfile {

    public Student[] readFile() throws IOException {
        //读取文件中所有内容到字符串str中
        File f = new File("Student.txt");
        FileReader fr = new FileReader(f);
        int length = (int) f.length();
        if (length == 0)
            return null;
        char[] ch = new char[length];
        fr.read(ch);
        fr.close();
        String str = new String(ch);

        //正则表达，切割字符串to对象数组
        String regex1 = "\\s{2}";     //关键字之间的2个空白字符，i.e.换行
        String regex2 = "\\p{Blank}";     //空格
        String[] strArray = str.split(regex1);
        int n = strArray.length;

        //创建实例对象
        Student stu[]=new Student[50];
        for (int i = 0; i < n; i++) {
            stu[i] = new Student();
        }


        //初始化实例对象
        for (int i = 0; i < strArray.length; i++) {
            String[] strArray2 = strArray[i].split(regex2);
            stu[i].id = strArray2[0];
            stu[i].name = strArray2[1];
            stu[i].gradeofC = Double.parseDouble(strArray2[2]);
            stu[i].gradeofEnglish = Double.parseDouble(strArray2[3]);
            stu[i].gradeofMath = Double.parseDouble(strArray2[4]);
        }

        return stu;
    }
}
