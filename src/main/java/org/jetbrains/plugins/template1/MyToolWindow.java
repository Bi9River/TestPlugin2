package org.jetbrains.plugins.template1;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.util.diff.Diff;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.*;

public class MyToolWindow {
    private JButton chooseDSLPathButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton chooseResultPathButton;
    private JCheckBox checkBox1;
    private JButton executeButton;
    private JPanel myToolWindowContent;
    private JLabel executeState;
    private JLabel title;
    private JTree resultTree;

    public MyToolWindow(ToolWindow toolWindow) {
        // title.setFont(new Font("Jetbrains Mono", Font.BOLD, 25));

        executeButton.addActionListener(e -> ExecuteDsl());
    }

    private void ChangeExecuteState(String content, Color color) {
        executeState.setForeground(color);
        executeState.setText(content);
    }

    public void ExecuteDsl() {
        ChangeExecuteState("executing...", Color.BLUE);


        if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
            ChangeExecuteState("path can NOT be empty!", Color.RED);
            return;
        }

        String command = "";

        // TODO 解压jar文件



        var dslRunnerPathUrl =getClass().getResource("/dslRunner").getPath().toString();
        var dslRunnerPath = URLDecoder.decode(dslRunnerPathUrl);
        File folder = new File("/tempDslRunner");

        if (!folder.exists() && !folder.isDirectory()) {

            folder.mkdirs();


        } else {
            // TODO if(numberOfFiles("/tempDslRunner"))

        }
        Un_jar_zip_gzip_Utils.UnAllFile(removePrevFileStr(dslRunnerPath).replace('/','\\'),"/tempDslRunner");

        if (checkBox1.isSelected()) {

            command = "cmd /c " + removePrevFileStr(dslRunnerPath).replace('/','\\') + " " + textField1.getText() + " " + textField2.getText() + " " + "execalt";
        } else {
            command = "cmd /c " + removePrevFileStr(dslRunnerPath).replace('/','\\') + " " + textField1.getText() + " " + textField2.getText();
        }
        try {
            var p = Runtime.getRuntime().exec(command);
            var s = IOUtils.toString(p.getInputStream(), "UTF-8");

            if (s.isEmpty()) {
                ChangeExecuteState(command, Color.cyan);
                // ChangeExecuteState("success!", Color.GREEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

    private String removePrevFileStr(String str) {
        for (int i = 0; i < 6; i++) {
            str = str.substring(1);
        }

        return str;
    }

    private void loadResultInfo() {

    }

    private void showResultsInTree() {

    }

    private int numberOfFiles(String filePath) {
        File folder = new File(filePath);
        File[] list = folder.listFiles();
        int fileCount = 0, folderCount = 0;
        long length = 0;
        for (File file : list) {
            if (file.isFile()) {
                fileCount++;
                length += file.length();
            } else {
                folderCount++;
            }
        }
        return fileCount;
    }
}
