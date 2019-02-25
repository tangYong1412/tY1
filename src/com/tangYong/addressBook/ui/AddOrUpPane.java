package com.tangYong.addressBook.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AddOrUpPane extends Pane {
   public TextField name;
   public TextField phoneNum;
   public TextField eMail;
   public Button oKBtn;

   public AddOrUpPane(){
       VBox addPane = new VBox();
       addPane.setSpacing(10);
       addPane.setAlignment(Pos.CENTER);
       addPane.setPrefWidth(200);

       //姓名
       HBox namePane = new HBox();
       this.name = new TextField();
       namePane.getChildren().addAll(new Label("姓名："), name);

       //电话
       HBox phoneNumPane = new HBox();
       this.phoneNum = new TextField();
       phoneNumPane.getChildren().addAll(new Label("电话："), phoneNum);

       //邮箱
       HBox eMailNumPane = new HBox();
       this.eMail = new TextField();
       eMailNumPane.getChildren().addAll(new Label("邮箱："), eMail);

       this.oKBtn = new Button("确定");
       addPane.getChildren().addAll(namePane, phoneNumPane, eMailNumPane, this.oKBtn);

       getChildren().add(addPane);
   }
}
