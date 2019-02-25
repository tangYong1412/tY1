package com.tangYong.TFPointCardGame.ui;

import com.tangYong.TFPointCardGame.Card;
import com.tangYong.TFPointCardGame.Node;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MainInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //纸牌类
        Card card = new Card();

        //头部，刷新，显示可能解
        HBox head = new HBox();
        head.setSpacing(10);
        head.setAlignment(Pos.CENTER);
        //寻找可能解按钮
        Button solveBtn = new Button("Find a Solution");
        TextField solution = new TextField();
        //设置为不能编写
        solution.setEditable(false);
        Button refreshBtn = new Button("Refresh");
        head.getChildren().addAll(solveBtn, solution, refreshBtn);

        //中心，显示纸牌
        HBox center = new HBox();
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);
        center.setPrefSize(400, 200);

        //底部，输入表达式
        HBox foot = new HBox();
        foot.setSpacing(10);
        foot.setAlignment(Pos.CENTER);
        Label label = new Label("Enter an expression:");
        TextField inputExpression = new TextField();
        Button verifyBtn = new Button("Verify");
        foot.getChildren().addAll(label, inputExpression, verifyBtn);

        //自己输入
        Button inputFourBtn = new Button("Input");

        inputFourBtn.setOnAction(e->{
            Stage newStage = new Stage();

            //主面板
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            //提示信息
            Label label1 = new Label("Input four numbers between 1 to 13");

            //输入框
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setAlignment(Pos.CENTER);

            TextField number1 = new TextField();
            TextField number2 = new TextField();
            TextField number3 = new TextField();
            TextField number4 = new TextField();
            hBox.getChildren().addAll(number1, number2, number3, number4);

            //寻找可能表达式
            HBox hBox1 = new HBox();
            hBox1.setSpacing(10);
            hBox1.setAlignment(Pos.CENTER);

            TextField solution1 = new TextField();
            solution1.setEditable(false);
            Button findBtn = new Button("Find a Solution");
            hBox1.getChildren().addAll(solution1, findBtn);

            //主面板添加组件
            vBox.getChildren().addAll(label1, hBox, hBox1);

            Scene scene = new Scene(vBox, 300, 300);
            newStage.setTitle("24-Point Card Game");
            newStage.setScene(scene);
            newStage.show();

            findBtn.setOnAction(e1->{
                int[] fourCards = new int[4];
                Card card1 = new Card();

                fourCards[0] = Integer.valueOf(number1.getText());
                fourCards[1] = Integer.valueOf(number2.getText());
                fourCards[2] = Integer.valueOf(number3.getText());
                fourCards[3] = Integer.valueOf(number4.getText());

                card1.setFourCards(fourCards);
                card1.getExpressions(card1.withoutBracket());

                findBtn.setOnAction(e2->{
                    Node root = card1.getRoot().next;
                    if( root == null){
                        solution1.clear();
                        solution1.setText("No solution");
                    }else{
                        solution1.clear();
                        solution1.setText((String)root.data);

                        //将指针向后移动
                        if(card1.getRoot().next != null){
                            card1.setRoot(card1.getRoot().next);
                        }
                    }
                });
            });
        });

        //主面板
        VBox main = new VBox();
        main.setSpacing(10);
        main.setAlignment(Pos.CENTER);
        main.getChildren().addAll(head, inputFourBtn, center, foot);

        //设置事件驱动
        //纸牌刷新
        refreshBtn.setOnAction(e->{
            //清除以前纸牌
            center.getChildren().clear();

            //随机产生四张纸牌
            int[] fourCards = card.generateRandomly();
            //计算所有可能的表达式
            card.getExpressions(card.withoutBracket());

            ImageView[] cards = new ImageView[4];
            String str = "com/tangYong/TFPointCardGame/image/card";

            //循环拼接路径
            for(int i = 0; i < fourCards.length; i++){
//                System.out.println(fourCards[i]);
                //拼接路径
                str = str.concat(String.valueOf(fourCards[i] - 1).concat("/"));
                int num = (int)(Math.random()*3);
                str = str.concat(String.valueOf(num).concat(".png"));

                cards[i] = new ImageView(str);

                //添加纸牌节点
                center.getChildren().add(cards[i]);

                //重置
                str = "com/tangYong/TFPointCardGame/image/card";
            }
        });

        Button sureBtn = new Button("确定");

        //表达式验证
        verifyBtn.setOnAction(e->{
            Stage newStage = new Stage();
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Label message = new Label();

            //获取表达式
            String expression = inputExpression.getText();

            //如果表达式合法
            if(card.checkNumbers(expression)){
                if(card.judgeExpression(expression)){
                    message.setText("Correct");
                    vBox.getChildren().add(message);
                }else{
                    message.setText("Incorrect result");
                    vBox.getChildren().add(message);
                }
            }else{
                message.setText("The numbers in the expression don't match the number in the set");
                vBox.getChildren().add(message);
            }

            //确定按钮
            vBox.getChildren().add(sureBtn);

            Scene scene = new Scene(vBox, 400, 100);
            newStage.setTitle("消息");
            newStage.setScene(scene);
            newStage.show();

            sureBtn.setOnAction(e1->{
                newStage.close();
            });
        });

        //寻找可能的表达式
        solveBtn.setOnAction(e->{
            Node root = card.getRoot().next;

            if( root == null){
                solution.clear();
                solution.setText("No solution");
            }else{
                solution.clear();
                solution.setText((String)root.data);

                //将指针向后移动
                if(card.getRoot().next != null){
                    card.setRoot(card.getRoot().next);
                }
            }
        });

        //场景
        Scene scene = new Scene(main, 400, 300);

        primaryStage.setTitle("24-Point Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
