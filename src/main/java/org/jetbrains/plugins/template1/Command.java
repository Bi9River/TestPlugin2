package org.jetbrains.plugins.template1;

import java.io.*;
import java.util.LinkedList;

public class Command extends Thread
{
    private java.lang.Process p;
    private InputStream is;
    private OutputStream os;
    private BufferedWriter bw;
    private BufferedReader br;
    private ProcessBuilder pb;
    private InputStream stdErr;

    public Command() {
    }


    //获取Process的输入，输出流
    public void setCmd(String cmd) {
        try {
            p = Runtime.getRuntime().exec(cmd);
            os = p.getOutputStream();
            is = p.getInputStream();
            stdErr = p.getErrorStream();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    //向Process输出命令
    public void writeCmd(String cmd) {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(cmd);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //读出Process执行的结果
    public String readCmd() {
        StringBuffer sb = new StringBuffer();
        br = new BufferedReader(new InputStreamReader(is));
        String buffer = null;
        try {
            while ((buffer = br.readLine()) != null) {
                sb.append(buffer + "\n");
            }
            System.out.println(p.waitFor());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    //将命令一股脑塞入list中
    public LinkedList<String> doCmd(LinkedList<String> lists) {
        LinkedList<String> list = new LinkedList<String>();
        for (String s : lists) {
            writeCmd(s);
            list.add(readCmd());
        }
        return list;
    }
    public void run(){
        Command cmd = new Command();
        cmd.setCmd("cmd");
        cmd.writeCmd("start cmd /k echo _RUNNING_NO_PARAMETER_");
        System.out.println(cmd.readCmd());
    }

    public void run(String command){
        Command cmd = new Command();
        cmd.setCmd("cmd");
        cmd.writeCmd("start cmd /k echo _RUNNING_NO_PARAMETER_");
        System.out.println(cmd.readCmd());
    }

    public static void main(String[] args) {
        Thread thr1 = new Command();
        Thread thr2 = new Command();
        thr1.start();
        thr2.start();
    }
}
