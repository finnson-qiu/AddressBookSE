package DbOperation;

import Interface.ErrorInterface;

import javax.naming.Name;
import java.sql.*;

public class DbOperator {

    private static String USERNAME = "scott";
    private static String PASSWORD = "tiger";
    private static String DRIVER = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";    //localhost本机IP，1521端口号，ORCL为Oracle默认数据库名

    Connection connection = null;                    //创建一个数据库连接
    PreparedStatement pst = null;       // 创建预编译语句对象，一般都是用这个而不用Statement
    ResultSet resultSet = null;                         // 创建一个结果集对象

    private Connection getConnection(){
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (ClassNotFoundException e){
            throw new RuntimeException("class not find !",e);
        }catch (SQLException e){
            throw new RuntimeException("get connection error !",e);
        }
        return  connection;
    }

    private ResultSet GetDResultSet( String Sno,String Snumber,String Sname,String Scity,int flag){
        connection = getConnection();
        String sql;
        ResultSet res = null;
        if(flag == 0){
            sql = "select * from ADDRESS where 1 = 1 order by SNO asc ";         //四个全为空
        }
        else if(flag == 1) {
            sql = "select * from ADDRESS where SCITY = ?";
        }
        else if(flag == 2){
            sql = "select * from ADDRESS where SNAME = ?";
        }
        else if(flag == 3){
            sql = "select * from ADDRESS where SNAME = ? AND SCITY = ?";
        }
        else if(flag == 4){
            sql = "select * from ADDRESS where SNO = ?";
        }
        else if(flag == 5){
            sql = "select * from ADDRESS where SNO = ? AND SCITY = ?";
        }
        else if(flag == 6){
            sql = "select * from ADDRESS where SNO = ? AND SNAME = ?";
        }
        else{
            sql = "select * from ADDRESS where SNO = ? AND SNAME = ? AND SCITY = ?";
        }
        try{
            pst = connection.prepareStatement(sql);
            if(flag == 1) {
                pst.setString(1, Scity);
            }
            else if(flag == 2){
                pst.setString(1,Sname);
            }
            else if(flag == 3){
                pst.setString(1,Sname);
                pst.setString(2,Scity);
            }
            else if(flag == 4){
                pst.setString(1,Sno);
            }
            else if(flag == 5){
                pst.setString(1,Sno);
                pst.setString(2,Scity);
            }
            else if(flag == 6){
                pst.setString(1,Sno);
                pst.setString(2,Sname);
            }
            else if(flag == 7){
                pst.setString(1,Sno);
                pst.setString(2,Sname);
                pst.setString(3,Scity);
            }
            res = pst.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    private ResultSet GetResultSet(String Sno,String Snumber,String Sname,String Scity,int flag){
        connection = getConnection();
        String sql;
        ResultSet res = null;
        if(flag == 0){
            sql = "select * from ADDRESS where 1 = 1 order by SNO asc ";         //四个全为空
        }
        else if(flag == 1) {
            sql = "select * from ADDRESS where SCITY LIKE ?";
        }
        else if(flag == 2){
            sql = "select * from ADDRESS where SNAME LIKE ?";
        }
        else if(flag == 3){
            sql = "select * from ADDRESS where SNAME LIKE ? AND SCITY LIKE ?";
        }
        else if(flag == 4){
            sql = "select * from ADDRESS where SNO LIKE ?";
        }
        else if(flag == 5){
            sql = "select * from ADDRESS where SNO LIKE ? AND SCITY LIKE ?";
        }
        else if(flag == 6){
            sql = "select * from ADDRESS where SNO LIKE ? AND SNAME LIKE ?";
        }
        else{
            sql = "select * from ADDRESS where SNO LIKE ? AND SNAME LIKE ? AND SCITY LIKE ?";
        }
        try{

            pst = connection.prepareStatement(sql);
            if(flag == 1) {
                    pst.setString(1, "%"+Scity+"%");
            }
            else if(flag == 2){
                    pst.setString(1,"%"+Sname+"%");
            }
            else if(flag == 3){
                pst.setString(1,"%"+Sname+"%");
                pst.setString(2,"%"+Scity+"%");
            }
            else if(flag == 4){
                pst.setString(1,"%"+Sno+"%");
            }
            else if(flag == 5){
                pst.setString(1,"%"+Sno+"%");
                pst.setString(2,"%"+Scity+"%");
            }
            else if(flag == 6){
                pst.setString(1,"%"+Sno+"%");
                pst.setString(2,"%"+Sname+"%");
            }
            else if(flag == 7){
                pst.setString(1,"%"+Sno+"%");
                pst.setString(2,"%"+Sname+"%");
                pst.setString(3,"%"+Scity+"%");
            }
            res = pst.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public String Select(String Sno,String Snumber,String Sname,String Scity,int flag){
        resultSet = GetResultSet(Sno,Snumber,Sname,Scity,flag);
        String temp = "";
        try {
            while (resultSet.next()) {
                temp = temp + "\t" + resultSet.getString("SNO");
                temp = temp + "\t\t" + resultSet.getString("SNAME");
                temp = temp + "\t\t   " + resultSet.getString("SNUMBER");
                temp = temp + "\t\t  " + resultSet.getString("SCITY");
                temp = temp + "\n";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ReleaseResource();
        }
        return temp;
    }

    private boolean JudgeNo(String Sno,String Snumber,String Sname,String Scity,int flag){
        resultSet = GetDResultSet(Sno,Snumber,Sname,Scity,flag);
        boolean volite = false;
        try {
            while(resultSet.next()){
                String no = resultSet.getString("SNO");
                if(no.equals(Sno)){
                    volite = true;
                    ReleaseResource();
                    break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return volite;
    }

    private boolean JudgeIfDelete(String Sno,String Snumber,String Sname,String Scity,int flag) {
        resultSet = GetDResultSet(Sno,Snumber,Sname,Scity,flag);
        boolean volite = false;
        try {
            while(resultSet.next()){
                volite = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return volite;
    }

    public boolean AddAddressData(String sno,String snumber,String sname, String scity){
        boolean volite = JudgeNo(sno,snumber,sname,scity,0);
        if(volite){
            return false;
        }
        connection = getConnection();
        String sqlStr = "insert into ADDRESS values(?,?,?,?)";
        try {
            pst = connection.prepareStatement(sqlStr);
            pst.setString(1,sno);
            pst.setString(2,snumber);
            pst.setString(3,sname);
            pst.setString(4,scity);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ReleaseResource();
        }
        return true;
    }

    public boolean DeleteAddressData(String sno,String snumber,String sname,String scity,int flag) {
        boolean volite = true;
        if(!JudgeIfDelete(sno,snumber,sname,scity,flag)){
            ReleaseResource();
            return false;
        }
        connection = getConnection();
        String sqlStr = "";
        if(flag == 1) {
            sqlStr = "delete from ADDRESS where SCITY=?";         //城市不为空
        }
        else if(flag == 2){
            sqlStr = "delete from ADDRESS where SNAME=?";         //姓名不为空
        }
        else if(flag == 3){
            sqlStr = "delete from ADDRESS where SNAME=? AND SCITY = ?";               //姓名和城市不为空
        }
        else if(flag == 4){
            sqlStr = "delete from ADDRESS where SNO=?";               //学号不为空
        }
        else if(flag == 5){
            sqlStr = "delete from ADDRESS where SNO=? AND SCITY = ?";               //学号和城市不为空
        }
        else if(flag == 6){
            sqlStr = "delete from ADDRESS where SNO=? AND SNAME = ?";               //学号和姓名不为空
        }
        else if(flag == 7){
            sqlStr = "delete from ADDRESS where SNO=? AND SNAME=? AND SCITY = ?";               //学号、姓名、城市都不为空
        }
        try {
            // 执行删除数据操作
            pst = connection.prepareStatement(sqlStr);
            if(flag == 1) {
                pst.setString(1, scity);
            }
            else if(flag ==2){
                pst.setString(1, sname);
            }
            else if(flag == 3){
                pst.setString(1, sname);
                pst.setString(2, scity);
            }
            else if(flag ==4){
                pst.setString(1, sno);
            }
            else if(flag == 5){
                pst.setString(1, sno);
                pst.setString(2, scity);
            }
            else if(flag == 6){
                pst.setString(1, sno);
                pst.setString(2, sname);
            }
            else if(flag == 7){
                pst.setString(2, sname);
                pst.setString(3, scity);
                pst.setString(1, sno);
            }
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
        return volite;
    }

    public boolean AlterAddress(String sno,String snumber,String sname,String scity){
        boolean judge = JudgeNo(sno,"","","",0);
        if(!judge){
            return false;
        }
        connection = getConnection();
        String sqlStr = "";
        if(!snumber.isEmpty()){
            sqlStr = "update ADDRESS set SNUMBER=?where SNO=?";
        }
        else if(!sname.isEmpty()) {
            sqlStr = "update ADDRESS set SNAME=? where SNO=?";
        }
        else if(!scity.isEmpty()){
            sqlStr = "update ADDRESS set SCITY=?where SNO=?";
        }
        try {
            pst = connection.prepareStatement(sqlStr);
            if(!snumber.isEmpty()) {
                pst.setString(1, snumber);
                pst.setString(2,sno);
            }
            else if(!sname.isEmpty()) {
                pst.setString(1, sname);
                pst.setString(2,sno);
            }
            else if(!scity.isEmpty()) {
                pst.setString(1, scity);
                pst.setString(2,sno);
            }
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ReleaseResource();
        }
        return true;
    }

    private void ReleaseResource() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
