package com.tangYong.hfmtree.ui;

import com.tangYong.hfmtree.HuffmanNode;
import com.tangYong.hfmtree.HuffmanTree;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //哈夫曼树类
        HuffmanTree huffmanTree = new HuffmanTree();

        //主面板
        VBox vBox = new VBox(10);

        Scene scene = new Scene(vBox, 800, 400);

        //顶部面板
        HBox top = new HBox(5);
        top.setAlignment(Pos.CENTER);

        //中间面板
        HBox center = new HBox();

        center.setAlignment(Pos.CENTER);
        center.setMinSize(800, 320);
        center.setStyle("-fx-border-color: black");
        center.minHeightProperty().bind(scene.heightProperty().subtract(100));

        //底部面板
        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);

        //编码输入框
        Label label = new Label("Enter a text");
        TextField input = new TextField();
        input.setPrefWidth(500);

        //构造哈夫曼树确认按钮
        Button showBtn = new Button("Show Huffman Tree");
        showBtn.setOnAction(e->{
            String text = input.getText();
            //全部转换为小写字母（去除空格方法trim()只对半角空格有效--英文空格（占一个字符））
            text = text.toLowerCase();

            HuffmanNode[] huffmanNodes = getLetterFreq(text);

            //创建哈夫曼树，获取返回编码
            int[][] huffCode = huffmanTree.createHuffmanTree(huffmanNodes);
            String huffStr = "";

            //将哈夫曼编码数组转换为字符串
            for(int i = 0; i < huffCode.length; i++){
                for(int j = 0; j < huffCode[i].length; j++){
                    if(huffCode[i][j] != 0){
                        huffStr += (char)huffCode[i][j];

                        for(int k = j + 1; k < huffCode[i].length; k++){
                            huffStr += (huffCode[i][k]+"");
                        }
                        break;
                    }
                }
            }

            //获取编码字符串
            String UIDisplay = "";
            for(int i = 0; i < text.length(); i++){
                for(int j = 0; j < huffStr.length(); j++){
                    if(text.charAt(i) == huffStr.charAt(j)){
                        for(int k = j + 1; ; k++){
                            if(k >= huffStr.length()){
                                break;
                            }

                            if(huffStr.charAt(k) != '0' && huffStr.charAt(k) != '1'){
                                break;
                            }

                            UIDisplay += (huffStr.charAt(k) + "");
                        }

                        break;
                    }
                }
            }

            //将编码写入文件codefile
            outputText(new File("src/com/tangYong/hfmtree/text/compressedFile/codefile.txt"), UIDisplay);

            //数组储存哈夫曼树
            huffStr = "";
            HuffmanNode[] huffmanNodes1 = huffmanTree.createArray();
            for(int i =0; i < huffmanNodes1.length; i++){
                huffStr += (huffmanNodes1[i].getLetter() + "") + (huffmanNodes1[i].getWeight()+" ");
            }
            //将哈夫曼树写入文件hfmtree
            outputText(new File("src/com/tangYong/hfmtree/text/compressedFile/hfmtree.txt"), huffStr);

            //在中间面板显示哈夫曼树
            HuffmanTreePane huffmanTreePane = new HuffmanTreePane(huffmanTree);
            center.getChildren().clear();
            center.getChildren().add(huffmanTreePane);

            //在新的窗口显示编码
            outputText(new File("src/com/tangYong/hfmtree/text/decompressedFile/codeprint.txt"), text + " is encoded to " + UIDisplay);
            displayOnUI(text + " is encoded to " + UIDisplay, "Encode Text to Bits");
        });

        top.getChildren().addAll(label, input, showBtn);

        //译码输入框
        Label label1 = new Label("Enter a bit string");
        TextField input1 = new TextField();
        input1.setMinWidth(520);

        //译码确认按钮
        Button showBtn1 = new Button("Decode Text");
        bottom.getChildren().addAll(label1, input1, showBtn1);
        showBtn1.setOnAction(e->{
            String text = input1.getText();

            //读取哈夫曼树
            String hfmTree = inputText(new File("src/com/tangYong/hfmtree/text/compressedFile/hfmtree.txt"));
            String decodeText = huffmanTree.decoding(text, hfmTree);

            //将译码内容写入文件textfile
            outputText(new File("src/com/tangYong/hfmtree/text/decompressedFile/textfile.txt"), decodeText);
            //将译码内容显示在UI
            displayOnUI(text + " is decoded to " +decodeText, "Decode Bits to Text");
        });

        //主面板添加结点
        vBox.getChildren().addAll(top, center, bottom);

        primaryStage.setTitle("HuffmanTree");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayOnUI(String text, String title){
        Stage stage = new Stage();

        StackPane stackPane = new StackPane(new Label(text));
        Scene scene = new Scene(stackPane, 500, 100);

        stage.setTitle(title);
        stage.setScene(scene);
        //窗口置顶
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    //根据频度设置权值
    private HuffmanNode[] getLetterFreq(String s){
        //26个小写字母
        int[] letters = new int[26];
        //循环统计字母频率
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != ' '){
                letters[s.charAt(i)-'a'] += 1;
            }
        }

        //出现多少个不同的字母
        int length = 0;
        for(int i = 0; i < letters.length; i++){
            if(letters[i] != 0){
                length++;
            }
        }

        //储存哈夫曼节点数组
        HuffmanNode[] huffmanNodes = new HuffmanNode[length];

        int index = 0;
        //根据频度设置权值
        for(int i = 0; i < letters.length; i++){
            if(letters[i] != 0){
                huffmanNodes[index] = new HuffmanNode();
                huffmanNodes[index].setLetter((char)(i + 'a'));
                huffmanNodes[index].setWeight(letters[i]);
                index++;
            }
        }

        return huffmanNodes;
    }

    //写入文件（字节流）
    private void outputText(File filename, String str){
        FileOutputStream fos = null;

        try {
            if(!filename.exists()){
                filename.createNewFile();
            }

            fos = new FileOutputStream(filename);
            byte[] bytes = str.getBytes();

            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读文件（字节流）
    private String inputText(File filename){
        FileInputStream fis = null;
        String s = "";

        try {
            if(!filename.exists()){
                System.out.println("文件不存在。");
                return s;
            }

            fis = new FileInputStream(filename);
            byte[] bytes = new byte[1024];
            int n;
            while((n = fis.read(bytes)) != -1){
                s += new String(bytes, 0, n);
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }

}
