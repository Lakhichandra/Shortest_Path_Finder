/**
 * Created by lucky on 01-02-2017.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javafx.util.Pair;
class Project1 extends JFrame implements ActionListener, MouseListener{
    JButton Matrix,source,destination,fill,path;
    JPanel panel;
    JPanel[] Arrp;
    JLabel[] label;
    Color c;
    JPanel[][] Arr1;
    int[][] Arr2;
    int x,y,sr,sc,Xd,YD;
    String color;
    JTextField ro;JTextField co;
    int Row,Column,sourceset=0,destinationset=0,k=0,l=0,z=1;

    public void fill(int[][] Arr,int Xs,int Ys){
        Queue<Pair<Integer,Integer>> Que=new ArrayDeque<>();
        Que.add(new Pair(Xs-1,Ys-1));
        int i=Xs-1,j=Ys-1;
        while(!(i==x && j==y) && !Que.isEmpty()){
            i=Que.element().getKey();
            j=Que.element().getValue();
            Que.poll();
            if((i+1<x))
                if((Arr[i+1][j]>Arr[i][j]+1 && Arr[i+1][j]>=0)| (Arr[i+1][j]==0 & !(i+1==Xs-1 & j==Ys-1))){
                    Arr[i+1][j]=Arr[i][j]+1;
                    Que.add(new Pair(i+1,j));
                }
            if((i-1>=0))
                if((Arr[i-1][j]>Arr[i][j]+1 && Arr[i-1][j]>=0) | (Arr[i-1][j]==0 & !(i-1==Xs-1 & j==Ys-1))){
                    Arr[i-1][j]=Arr[i][j]+1;
                    Que.add(new Pair(i-1,j));
                }
            if((j+1<y))
                if((Arr[i][j+1]>Arr[i][j]+1 && Arr[i][j+1]>=0) | (Arr[i][j+1]==0 & !(i==Xs-1 & j+1==Ys-1))){
                    Arr[i][j+1]=Arr[i][j]+1;
                    Que.add(new Pair(i,j+1));
                }
            if((j-1>=0))
                if((Arr[i][j-1]>Arr[i][j]+1 && Arr[i][j-1]>=0) | ( Arr[i][j-1]==0 & !(i==Xs-1 & j-1==Ys-1))){
                    Arr[i][j-1]=Arr[i][j]+1;
                    Que.add(new Pair(i,j-1));
                }
        }}
    public void findPath(int[][] Arr){
        Stack<Pair<Integer,Integer>> stack=new Stack<>();
        int Value=Arr[Xd-1][YD-1];
        int i=Xd-1;
        int j=YD-1;
        stack.push(new Pair(i,j));
        int k=Value;
        while(Value-->0){
            --k;
            if(i+1<x){
                if(Arr[i+1][j]==k) {
                    stack.push(new Pair(i+1,j));++i;
                    continue;
                }
            }
            if(i-1>=0){
                if(Arr[i-1][j]==k) {
                    stack.push(new Pair(i-1,j));--i;
                    continue;
                }
            }
            if(j+1<y){
                if(Arr[i][j+1]==k){
                    stack.push(new Pair(i,j+1));++j;
                    continue;
                }
            }
            if(j-1>=0){
                if(Arr[i][j-1]==k) {
                    stack.push(new Pair(i,j-1));--j;
                    continue;
                }
            }
            else
                System.out.println("Wrong");
        }
        while(!stack.empty()){
            Arr1[stack.peek().getKey()][stack.peek().getValue()].setBackground(Color.CYAN);
            stack.pop();
        }
    }
    public void func(){
        int g=0;

        for (int i=0; i<x; i++){
            for(int j=0;j<y;j++){
                label[j+g].setText(""+Arr2[i][j]);
            }g=g+y;}
    }
    Project1(){
        JLabel Row=new JLabel("Rows ");
        JLabel Column=new JLabel("Columns");
        source=new JButton("Source");
        destination=new JButton("Target");
        fill=new JButton("Fill");
        path=new JButton("Path");
        //   s.setBackground(Color.red);
        //    d.setBackground(Color.blue);
        ro= new JTextField();
        co= new JTextField();
        Matrix= new JButton("MakeGrid");
      //  B1=new JButton("Reset");
        panel=new JPanel();
        fill.setBounds(450,200,100,30);
        Row.setBounds(10,0,60,30);
        ro.setBounds(70,0,50,30);
        Column.setBounds(140,0,60,30);
        co.setBounds(220,0,50,30);
        Matrix.setBounds(10,60,100,30);
        source.setBounds(450,100,100,30);
        destination.setBounds(450,150,100,30);
        path.setBounds(450,250,100,30);
      //  B1.setBounds(450,350,100,30);
        co.addActionListener(this);
        ro.addActionListener(this);
        source.addActionListener(this);
        destination.addActionListener(this);
        Matrix.addActionListener(this);
        fill.addActionListener(this);
        path.addActionListener(this);
     //   B1.addActionListener(this);
        panel.setBounds(30,100,400,400);
        panel.setBackground(Color.gray);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                System.out.println("Stopped");
                System.exit(0);
            }
        });
        add(ro);add(Matrix);add(panel);add(source);add(destination);add(co);add(Row);add(Column);add(fill);add(path);/*add(B1)*/;
        setSize(300,300);
        setLayout(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand()=="MakeGrid"){
            x=Integer.parseInt(ro.getText());
            y=Integer.parseInt(co.getText());
            Arr2=new int[x][y];
            label=new JLabel[x*y];

            Arr1=new JPanel[x][y];
            Arrp=new JPanel[x*y];
            panel.setLayout(new GridLayout(x,y, -1, -1));
            panel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            for (int i =0; i<(x*y); i++){
                Arrp[i] = new JPanel();
                Arrp[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(Arrp[i]);
                Arrp[i].addMouseListener(this);
                Arrp[i].setBackground(Color.white);
                Arrp[i].setLayout(new CardLayout());
                label[i]=new JLabel();
                Arrp[i].add(label[i]);
            }
            int l=0;
            for(int i=0;i<x;i++){

                for(int j=0;j<y;j++){
                    Arr1[i][j]=Arrp[l++];
                  //  Arr2[i][j]=0;
                }
            }
            for(int m=0;m<x;m++)
            {
                for(int z=0;z<y;z++)
                {
                    double D=Math.random();
                    if(((m+1<x&&m-1>0)&&(z+1<y&&z-1>0))&&(((D<0.5)&&(((Arr2[m][z+1]!=-1)&&(Arr2[m][z-1]!=-1))&&((Arr2[m+1][z]!=-1)&&(Arr2[m-1][z]!=-1)))&&(((Arr2[m+1][z+1]!=-1)&&(Arr2[m+1][z-1]!=-1))&&((Arr2[m-1][z-1]!=-1)&&(Arr2[m-1][z+1]!=-1))))))
                    {
                        Arr2[m][z]=-1;
                        Arr1[m][z].setBackground(Color.red);
                    }
                    else
                    {
                        Arr2[m][z]=0;
                    }
                }
            }
        }
        if(e.getActionCommand()=="Fill"){

            fill(Arr2,sr,sc);
            func();
        }
      /*  if(e.getActionCommand()=="Hurdel"){
            c=new Color(232, 115, 81);
            color="red";

        }*/
        if(e.getActionCommand()=="Source"){
            c=new Color(35, 232, 37);
            color="green";
        }
        if(e.getActionCommand()=="Target"){

            c=new Color(0,0,255);
            color="blue";
        }
        if(e.getActionCommand()=="Path"){
            findPath(Arr2);
        }
      /*  if(e.getActionCommand()=="Reset"){
            int g=0;
            for (int i=0;i<x;i++)
            {
                for (int j=0;j<y;j++)
                {
                    Arr2[i][j]=0;
                    Arr1[i][j].setBackground(Color.white);
                    label[j+g].setText("");
                }
                g=g+y;
            }
        }*/
    }
    public void mouseClicked(MouseEvent me) {
        JPanel p=(JPanel)me.getSource();
        p.setBackground(c);
      /*  if(color=="red"){
            for(int i=0;i<x;i++)
                for(int j=0;j<y;j++){
                    if(Arr1[i][j]==p){
                        Arr2[i][j]=-1;
                    }
                }
        }*/
        if(color=="green"){
            for(int i=0;i<x;i++)
                for(int j=0;j<y;j++){
                    if(Arr1[i][j]==p){
                        Arr2[i][j]=0;
                        sr=i+1;
                        sc=j+1;
                    }
                }
        }
        if(color=="blue"){
            for(int i=0;i<x;i++)
                for(int j=0;j<y;j++){
                    if(Arr1[i][j]==p){
                        Arr2[i][j]=0;
                        Xd=i+1;
                        YD=j+1;
                    }
                }
        }

    }
    public void mouseEntered(MouseEvent me) {

    }
    public void mouseExited(MouseEvent me) {

    }
    public void mousePressed(MouseEvent me) {

    }
    public void mouseReleased(MouseEvent me) {

    }
    public static void main(String args[]){
        Project1 ob=new Project1();
    }

}
