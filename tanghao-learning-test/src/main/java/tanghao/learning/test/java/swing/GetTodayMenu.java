package tanghao.learning.test.java.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;


public class GetTodayMenu {
    //主面板
    JFrame rFrame=new JFrame("随机点菜器");
    //名字
    String[] sideDish={"香干肉丝","土豆肉丝","肉末茄子","家常豆腐","辣炒花蛤","小鸡焗口蘑","小炒牛肉","爆炒螺丝","干锅花菜","番茄炒蛋"};
    String[] mainDish={"土豆牛肉","啤酒鸭","红烧仔鸡","可乐鸡翅","红烧鸭翅","红烧牛蛙","红烧肉","千张结烧肉","鹌鹑蛋烧肉","萝卜烧肉","糖醋排骨","红烧鸽子","秘制小龙虾","清蒸鲈鱼","汪刺鱼烧豆腐","双椒鱼头","红烧鲫鱼","水煮鱼","清蒸小海鲜","避风塘炒虾"};
    String[] vegetableDish ={"苋菜","空心菜","菜心","油菜","胡萝卜","酸辣白/包菜","耗油生菜","凉拌黄瓜"};

    //用于存储名字的标签
    JLabel name = new JLabel();
    JLabel name2 = new JLabel();
    JLabel name3 = new JLabel();

    //按钮
    JButton btn = new JButton("开始点菜");
    //采用的是伪随机数，大家也可以不用这个，这个在网上可以找到java随机数的设置
    Random rd = new Random();
    public void init()
    {
        //提示标签页面
        JLabel jt= new JLabel("今日菜单");
        //设置标签居中
        jt.setHorizontalAlignment(SwingConstants.CENTER);
        //设置字体大小
        jt.setFont(new java.awt.Font("今日菜单",1,25));
        //设置名字显示的标签居中
        name.setHorizontalAlignment(SwingConstants.CENTER);
        //通过匿名类实现Action按钮的监听事件
        btn.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //获取随机的姓名
                String n=getMainDishName();
                //设置name标签的文字
                name.setText("主菜:"+n);
                //设置字体
                name.setFont(new java.awt.Font(n,1,20));
                //设置字体颜色
                name.setForeground(Color.red);
                name.setBorder(BorderFactory.createLineBorder(Color.black));
                name.setSize(150,30);

                //获取随机的姓名
                String n2=getSideDishName();
                //设置name标签的文字
                name2.setText("快手菜:"+n2);
                //设置字体
                name2.setFont(new java.awt.Font(n2,1,20));
                //设置字体颜色
                name2.setForeground(Color.gray);
                name2.setBorder(BorderFactory.createLineBorder(Color.black));
                name2.setSize(150,30);

                //获取随机的姓名
                String n3=getVegetableDishName();
                //设置name标签的文字
                name3.setText("素菜:"+n3);
                //设置字体
                name3.setFont(new java.awt.Font(n3,1,20));
                //设置字体颜色
                name3.setForeground(Color.blue);
                name3.setBorder(BorderFactory.createLineBorder(Color.black));
                name3.setSize(150,30);
            }

        });
        JPanel jp1=new JPanel();
        jp1.add(name,"cell 0 0,alignx trailing");
        jp1.add(name2,"cell 0 1,alignx trailing");
        jp1.add(name3,"cell 0 2,alignx trailing");
        jp1.add(btn);



        //获取JFrame的面板
//        Container p = this.rFrame.getContentPane();
//        //设置布局方式，我采用的BorderLayout布局
//        p.setLayout(new BorderLayout(3,1));
//        //添加提示标签在北方
//        p.add(jt,BorderLayout.NORTH);
//        //添加姓名标签在中央
//        p.add(name,BorderLayout.CENTER);
//        p.add(name2,BorderLayout.CENTER);
//        p.add(name3,BorderLayout.CENTER);
//
//        //添加按钮控件在南方
//        p.add(btn,BorderLayout.SOUTH);
        //调整大小，这个是java中无法设置标签的大小
        rFrame.pack();
        //设置窗体大小
        rFrame.setSize(300, 300);
        //设置可以显示
        rFrame.setVisible(true);
        rFrame.add(jp1);


    }
    //获取随机的姓名
    public String getSideDishName()
    {
        int a = 0;
        //random类去实现随机数时，只能设置上限，也就是说随机数产生的都是0-stuName.length之间的数字
        a = rd.nextInt(sideDish.length);
        //rd.setSeed();
        //a = (int)Math.random()*stuName.length;

        return sideDish[a];
    }
    public String getMainDishName()
    {
        int a = 0;
        //random类去实现随机数时，只能设置上限，也就是说随机数产生的都是0-stuName.length之间的数字
        a = rd.nextInt(mainDish.length);
        //rd.setSeed();
        //a = (int)Math.random()*stuName.length;

        return mainDish[a];
    }
    public String getVegetableDishName()
    {
        int a = 0;
        //random类去实现随机数时，只能设置上限，也就是说随机数产生的都是0-stuName.length之间的数字
        a = rd.nextInt(vegetableDish.length);
        //rd.setSeed();
        //a = (int)Math.random()*stuName.length;

        return vegetableDish[a];
    }

    public static void main(String[] args)
    {
        GetTodayMenu rn=new GetTodayMenu();
        rn.init();
    }
}
