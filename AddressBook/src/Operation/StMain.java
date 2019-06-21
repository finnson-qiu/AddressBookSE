package Operation;

import DbOperation.DbOperator;
import Interface.ErrorInterface;
import Interface.StMainInterface;

import javax.swing.*;

import static java.lang.Thread.sleep;


public class StMain {
    private static StMainInterface stMainInterface;
    private static ErrorInterface errorInterface = new ErrorInterface();

    public void StMainOperation(){
        stMainInterface = new StMainInterface();
        stMainInterface.CreateStMainFrame();
        AddActionListener(stMainInterface.getButton1());
        SelectActionListener(stMainInterface.getButton4());
        DeleteActionListener(stMainInterface.getButton3());
        AlterActionListener(stMainInterface.getButton2());
    }

    private static int JudgeFlag(String Sno,String Snumber,String Sname,String Scity){
        int flag;
        if(Sno.isEmpty()) {
            if (Sname.isEmpty()) {
                if(Scity.isEmpty()){
                    flag = 0;
                }
                else{
                    flag = 1;
                }
            }
            else {
                if(Scity.isEmpty()){
                    flag = 2;
                }
                else{
                    flag = 3;
                }
            }
        }
        else{
            if (Sname.isEmpty()) {
                if(Scity.isEmpty()){
                    flag = 4;
                }
                else{
                    flag = 5;
                }
            }
            else {
                if(Scity.isEmpty()){
                    flag = 6;
                }
                else{
                    flag = 7;
                }
            }
        }
        return flag;
    }

    private static void ClearText(){
        stMainInterface.getTextField1().setText("");
        stMainInterface.getTextField2().setText("");
        stMainInterface.getTextField3().setText("");
        stMainInterface.getTextField4().setText("");
    }
    private static void AddAddress(){
        String sno = stMainInterface.getTextField1().getText();
        String snumber = stMainInterface.getTextField2().getText();
        String sname = stMainInterface.getTextField3().getText();
        String scity = stMainInterface.getTextField4().getText();
        if(sno.isEmpty()||snumber.isEmpty()||sname.isEmpty()||scity.isEmpty()){
            errorInterface.CreateFrame();
            errorInterface.getjLabel().setText("需要填写所有信息");
        }
        else {
            DbOperator db = new DbOperator();
            boolean can = db.AddAddressData(sno, snumber, sname, scity);
            if (!can) {
                errorInterface.CreateFrame();
                errorInterface.getjLabel().setText("该学号已经存在");
            } else {
                errorInterface.CreateFrame();
                errorInterface.getjLabel().setText("添加成功！");
                ClearText();
                String display = db.Select("", "", "", "", 0);
                stMainInterface.getTextArea().setText(display);
            }
        }
    }

    private static void SelectAddress(){
        String sno = stMainInterface.getTextField1().getText();
        String snumber = stMainInterface.getTextField2().getText();
        String sname = stMainInterface.getTextField3().getText();
        String scity = stMainInterface.getTextField4().getText();
        int flag = JudgeFlag(sno,snumber,sname,scity);
        DbOperator db = new DbOperator();
        String  display = db.Select(sno,snumber,sname,scity,flag);
        stMainInterface.getTextArea().setText(display);
        ClearText();
    }

    private static void DeleteAddress(){
        String sno = stMainInterface.getTextField1().getText();
        String snumber = stMainInterface.getTextField2().getText();
        String sname = stMainInterface.getTextField3().getText();
        String scity = stMainInterface.getTextField4().getText();
        int flag = JudgeFlag(sno,snumber,sname,scity);
        DbOperator db = new DbOperator();
        if(flag != 0) {
            boolean judge = db.DeleteAddressData(sno, snumber, sname, scity, flag);
            String  display = db.Select(sno,snumber,sname,scity,0);
            stMainInterface.getTextArea().setText(display);
            ClearText();
            errorInterface.CreateFrame();
            if(judge) {
                errorInterface.getjLabel().setText("删除成功");
            }
            else{
                errorInterface.getjLabel().setText("没有匹配项");
            }
        }
        else{
            errorInterface.CreateFrame();
            errorInterface.getjLabel().setText("请输入要删除的");
        }
    }

    private static void AlterAddress(){
        String sno = stMainInterface.getTextField1().getText();
        String snumber = stMainInterface.getTextField2().getText();
        String sname = stMainInterface.getTextField3().getText();
        String scity = stMainInterface.getTextField4().getText();
        int count = 3;
        if(sname.isEmpty()){        //有数据的框
            count--;
        }
        if(snumber.isEmpty()){
            count--;
        }
        if(scity.isEmpty()){
            count--;
        }
        if(count != 1){
            if(count > 1){
                errorInterface.CreateFrame();
                errorInterface.getjLabel().setText("一次只能改一项");
            }
            else{
                errorInterface.CreateFrame();
                errorInterface.getjLabel().setText("没有要修改项");
            }
        }
        else {
            if (sno.isEmpty()) {
                errorInterface.CreateFrame();
                errorInterface.getjLabel().setText("学号不能为空");
            } else {
                DbOperator db = new DbOperator();
                boolean judge = db.AlterAddress(sno, snumber, sname, scity);
                if (judge) {
                    String display = db.Select(sno, snumber, sname, scity, 0);
                    stMainInterface.getTextArea().setText(display);
                    ClearText();
                    errorInterface.CreateFrame();
                    errorInterface.getjLabel().setText("修改成功");
                } else {
                    errorInterface.CreateFrame();
                    errorInterface.getjLabel().setText("未找到该学号");
                }
            }
        }
    }

    private static void AddActionListener(JButton button){
        button.addActionListener(e -> {
            AddAddress();
        });
    }

    private static void SelectActionListener(JButton button){
        button.addActionListener(e->{
            SelectAddress();
        });
    }

    private static void DeleteActionListener(JButton button){
        button.addActionListener(e->{
            DeleteAddress();
        });
    }

    private static void AlterActionListener(JButton button){
        button.addActionListener(e -> {
            AlterAddress();
        });
    }
}
