package com.tangYong.teachingPreparation;

import org.omg.CORBA.INTERNAL;
import sun.net.idn.StringPrep;

import java.util.Scanner;

/**
 *
 * 有向无环图
 *
 */
public class DAG {
    //学期学分上限
    private double creditLimit;
    //顶点数 -> 课程数 边数 -> 课程直接先修关系数
    private int courseNum, sideNum;
    //顶点数组 -> 课程数组
    private VNode[] courses;
    //学期总数
    private int totalSemester;

    public DAG(){
        this(0, 0, 0, null, 0);
    }

    public DAG(double creditLimit, int courseNum, int sideNum, VNode[] courses, int totalSemester){
        this.creditLimit = creditLimit;
        this.courseNum = courseNum;
        this.sideNum = sideNum;
        this.courses = courses;
        this.totalSemester = totalSemester;
    }

    //创建有向图
    public void createDAG(){
//        Scanner input = new Scanner(System.in);
//        System.out.println("请输入课程数量：");
//        courseNum = input.nextInt();
//        System.out.println("请输入课程直接先修关系数：");
//        sideNum = input.nextInt();
//        System.out.println("请输入每学期学分上限：");
//        creditLimit = input.nextDouble();
//        System.out.println("请输入学期总数：");
//        totalSemester = input.nextInt();

        courseNum = 12;
        sideNum = 16;
        creditLimit = 10;
        totalSemester = 6;

        //录入课程，图顶点
        courses = new VNode[courseNum];
//        String str;
//        int index = 0;
//        //学分
//        String credit;
//        System.out.println("请逐个输入各个课程的课程编号和学分(以空格隔开如C01 2)：");
//        str = input.nextLine();
//        for(int i = 0; i < courseNum; i++){
//            str = input.nextLine();
//            for(int j = 0; j < str.length(); j++){
//                if(str.charAt(j) == ' '){
//                    index = j;
//                    break;
//                }
//            }
//            credit = str.substring(index + 1, str.length());
//            courses[i] = new VNode(str.substring(0, index), Integer.valueOf(credit));
//            System.out.println(courses[i].getCourseNo());
//        }

        courses[0] = new VNode("C01" , 2);
        courses[1] = new VNode("C02" , 3);
        courses[2] = new VNode("C03" , 4);
        courses[3] = new VNode("C04" , 3);
        courses[4] = new VNode("C05" , 2);
        courses[5] = new VNode("C06" , 3);
        courses[6] = new VNode("C07" , 4);
        courses[7] = new VNode("C08" , 4);
        courses[8] = new VNode("C09" , 7);
        courses[9] = new VNode("C10" , 5);
        courses[10] = new VNode("C11" , 2);
        courses[11] = new VNode("C12" , 3);

        //录入课程之间先修关系，图边，构建邻接表
//        String begin;
//        String end;
//        int currentVertex;
//        int nextVertex;
//        System.out.println("请逐个输入课程直接先修关系（如：C01 C02）：");
//        for(int i = 0; i < sideNum; i++){
//            str = input.nextLine();
//            System.out.println(str);
//            for(int j = 0; j < str.length(); j++){
//                if(str.charAt(j) == ' ') {
//                    index = j;
//                    break;
//                }
//            }
//            begin = str.substring(0, index);
//            System.out.println(begin);
//            end = str.substring(index + 1, str.length());
//            System.out.println(end);
//            //弧尾，顶点
//            currentVertex = locateCourse(begin);
//            //弧头，该弧指向的下一个顶点
//            nextVertex = locateCourse(end);
//            //添加新的依附弧
//            addArc(currentVertex, nextVertex);
//        }

        addArc(locateCourse("C01"), locateCourse("C02"));
        addArc(locateCourse("C01"), locateCourse("C03"));
        addArc(locateCourse("C01"), locateCourse("C04"));
        addArc(locateCourse("C01"), locateCourse("C12"));

        addArc(locateCourse("C09"), locateCourse("C12"));
        addArc(locateCourse("C09"), locateCourse("C10"));
        addArc(locateCourse("C09"), locateCourse("C11"));

        addArc(locateCourse("C11"), locateCourse("C06"));

        addArc(locateCourse("C06"), locateCourse("C08"));

        addArc(locateCourse("C02"), locateCourse("C03"));

        addArc(locateCourse("C03"), locateCourse("C07"));
        addArc(locateCourse("C03"), locateCourse("C05"));
        addArc(locateCourse("C03"), locateCourse("C08"));

        addArc(locateCourse("C04"), locateCourse("C05"));

        addArc(locateCourse("C05"), locateCourse("C07"));

        addArc(locateCourse("C10"), locateCourse("C12"));

    }

    //添加弧，头插法将弧节点插入对应边表
    private void addArc(int currentVertex, int nextVertex){
        //指定弧指向的下一个顶点
        ArcNode arc = new ArcNode(nextVertex);
        //下一个弧为当前节点的第一个依附弧
        arc.setNextArc(courses[currentVertex].getFirstArc());
        //当前节点的第一依附弧
        courses[currentVertex].setFirstArc(arc);
    }

    //给定顶点的值，返回其在图中的位置，如果不存在则返回-1
    private int locateCourse(String course){
        for( int i = 0; i < courseNum; i++){
            if(courses[i].getCourseNo().equals(course)){
                return i;
            }
        }

        return -1;
    }

    //返回顶点的第一个依附弧的指向节点
    public int firstAdjVex(int index) throws Exception{
        if(index < 0 || index >= courseNum){
            throw new Exception("第" + index + "节点不存在！");
        }

        if(courses[index].getFirstArc() != null){
            return courses[index].getFirstArc().getAdjVex();
        }

        return -1;
    }

    //返回顶点v相对于顶点w的下一条弧所指向的顶点
    public int nextAdjVex(int indexVex, int indexW) throws Exception{
        if( indexVex < 0 || indexVex >= courseNum){
            throw new Exception("第" + indexVex + "节点不存在！");
        }

        //当前顶点第一个依附弧
        ArcNode course = courses[indexVex].getFirstArc();
        while( course != null){
            //如果当前弧指向的顶点和指定顶点相同
            if(course.getAdjVex() == indexW){
                if(course.getNextArc() != null){
                    return course.getNextArc().getAdjVex();
                }else{
                    return -1;
                }
            }

            course = course.getNextArc();
        }

        //没有指向节点为w的弧
        return -1;
    }

    //获取课程编号
    public String getCourse(int index) throws Exception{
        if( index < 0 || index >= courseNum){
            throw new Exception("第" + index + "个顶点不存在！");
        }

        return courses[index].getCourseNo();
    }

    //获取课程学分
    public double getCourseCredit(int index) throws Exception{
        if( index < 0 || index >= courseNum){
            throw new Exception("第" + index + "个顶点不存在！");
        }

        return courses[index].getCredit();
    }

    //计算顶点入度
    private int[] findInDegree(){
        int[] inDegree = new int[courseNum];
        for(int i = 0; i < courseNum; i++){
            //顶点的第一个依附弧
            ArcNode arc = courses[i].getFirstArc();

            while( arc !=null ){
                ++inDegree[arc.getAdjVex()];
                arc = arc.getNextArc();
            }
        }

        return inDegree;
    }

    //拓扑排序，返回修课顺序
    public String[] topologicalSort(){
        //删除节点
        String[] course = new String[courseNum];
        //输出顶点计数
        int count = 0;
        int[] inDegree = findInDegree();
        LinkStack linkStack = new LinkStack();
        //出栈顶点下标
        int index;

        for(int i = 0; i < courseNum; i++){
            //如果开始顶点入度为零入栈
            if( inDegree[i] == 0 ){
                linkStack.push(i);
            }
        }

        int i = 0;

        ArcNode arc;
        while (!linkStack.isEmpty()) {
            index = (int)linkStack.pop();
            //存入将要删除的顶点
            course[i++] = this.courses[index].getCourseNo();
            ++count;

            //删除以该顶点为出发点的所有弧
            //该顶点的第一个依附弧
            arc = courses[index].getFirstArc();
            while( arc != null ){

                //当前弧所指向顶点
                index = arc.getAdjVex();
                //删除当前弧，以当前弧为终止的点入度减一
                if( --inDegree[index] == 0){
                    linkStack.push(index);
                }

                //获取该顶点的下一个依附弧
                arc = arc.getNextArc();
            }
        }

        //如果删除顶点数小于顶点总数
        if( count < courseNum){
            //有回路
            return null;
        }
        else{
            return course;
        }
    }

    public String[] arrangeCourse(String[] courses, int choice){
        if( courses == null){
            return null;
        }

        String[] arrange = null;

        switch (choice){
            case 1:
                arrange = average(courses);
                break;

            case 2:
                arrange = concentrateOnFront(courses);
                break;

            default:break;
        }

        return arrange;
    }

    //平均课程安排
    private String[] average(String[] courses){
        String[] arrange = new String[totalSemester];
        for(int i = 0; i < totalSemester; i++){
            arrange[i] = "";
        }

        //获取每门学科的学分
        double[] credits = getCredits(courses);

        //理想的课程安排
        int idealArrange = courseNum/totalSemester;
        //每学期的课程数量
        int number = 0;
        double countCredit = 0;
        for(int i = 0, index = 0; i < courseNum && index < totalSemester; i++){
            //如果当前学科总学分小于每学期学分限制，并且如果剩余的课程数量大于等于学期数
            if((countCredit += credits[i]) <= creditLimit && courseNum - i >= totalSemester - index &&
                    number + 1 <= idealArrange ){
                arrange[index] = arrange[index].concat(" ").concat(courses[i]);
                number++;
            }
            else{
                //重置课程数量
                number = 1;
                //重置每学期课程学分
                countCredit = credits[i];
                if(index + 1 < totalSemester){
                    ++index;
                    arrange[index] = arrange[index].concat(" ").concat(courses[i]);
                }else{
                    arrange[index] = arrange[index].concat(" ").concat(courses[i]);
                }
            }
        }

        return arrange;
    }

    //集中在前几个学期
    private String[] concentrateOnFront(String[] courses){
        String[] arrange = new String[totalSemester];
        for(int i = 0; i < totalSemester; i++){
            arrange[i] = "";
        }
        //获取每门学科的学分
        double[] credits = getCredits(courses);

        //计算每学期学科的学分总和
        double countCredit = 0;
        for(int i = 0, index = 0; i < courseNum && index < totalSemester; i++){
            //如果当前学科总学分小于每学期学分限制，并且如果剩余的课程数量大于等于学期数
            if( (countCredit += credits[i]) <= creditLimit && courseNum - i >= totalSemester - index ){
                arrange[index] = arrange[index].concat(" ").concat(courses[i]);
            }
            else{
                //重置每学期学分
                countCredit = credits[i];
                ++index;
                arrange[index] = arrange[index].concat(" ").concat(courses[i]);
            }
        }

        return arrange;
    }

    //获取对应学科的学分
    private double[] getCredits(String[] courses){
        double[] credits = new double[courseNum];
        int i = 0;
        for(String course : courses){
            for(VNode tempCourse : this.courses){
                if( course.equals(tempCourse.getCourseNo())){
                    credits[i++] = tempCourse.getCredit();
                }
            }
        }

        return credits;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public int getSideNum() {
        return sideNum;
    }

    public VNode[] getCourses(){
        return courses;
    }
}
