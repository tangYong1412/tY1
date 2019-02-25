package com.tangYong.addressBook.ui;

import com.tangYong.addressBook.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainInterface extends Application {
    //将中文转换为英文
    private GetHeaderOfCN getHeaderOfCN = new GetHeaderOfCN();
    //联系人
    private AddressBook addressBook = new AddressBook();
    //首字母面板数组
    private VBox[] headCharPanes = new VBox[26];
    //添加长度
    private double length = 180;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //主面板
        VBox mainPane = new VBox();
        mainPane.setPrefSize(400, 500);
        mainPane.setSpacing(10);
        mainPane.setAlignment(Pos.CENTER_LEFT);

        //头部面板
        HBox headPane = new HBox();
        headPane.setSpacing(10);
        //查询框
        Label query = new Label("查询：");
        TextField queryBox = new TextField();

        queryBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //按下enter键盘
                if(event.getCode().equals(KeyCode.ENTER)){
                    String str = queryBox.getText();

                    if( isNumber(str) ){//按电话查找
                        System.out.println("电话");

                        findByPhoneNum(str);
                    }else if( isChinese(str) ){//按名字查找
                        System.out.println("姓名");

                        //获取名字首字母下标
                        String firstLetter = getHeaderOfCN.getFirstLetter( String.valueOf(str.charAt(0)) );
                        int firstIndex = firstLetter.charAt(0) - 'a';

                        //获取对应链表
                        LinkList linkList = addressBook.getContacts()[firstIndex];
                        findByName(linkList, str);
                    }else{//按邮箱查找
                        System.out.println("邮箱");

                        findByEMail(str);
                    }
                }
            }
        });


        //添加按钮
        Button addBtn = new Button("添加");
        headPane.getChildren().addAll(query, queryBox, addBtn);
        mainPane.getChildren().add(headPane);

        Label[] labels = new Label[26];
        //初始化并添加在主面板
        for(int i = 0; i < headCharPanes.length; i++){
            headCharPanes[i] = new VBox();
            headCharPanes[i].setSpacing(5);
            labels[i] = new Label( String.valueOf((char)('A' + i)) );
            mainPane.getChildren().addAll(labels[i], headCharPanes[i]);
        }

        addBtn.setOnAction(e->{
            add();
        });

        //导航条
        VBox navigationBar = new VBox();
        ScrollPane scrollPane = new ScrollPane( mainPane );
        scrollPane.setPrefSize(450, 550);

        Button[] buttons = new Button[26];
        for(int i = 0; i < 26; i++){
            buttons[i] = new Button(String.valueOf( (char)('A' + i) ));
            buttons[i].setAlignment(Pos.CENTER);
            buttons[i].setPrefSize(40, 30);

            int index = i;
            buttons[i].setOnAction(e->{
//                System.out.println( labels[index].getLayoutY() );
//                System.out.println( mainPane.getHeight() );
                scrollPane.setVvalue(  ( labels[index].getLayoutY() )/mainPane.getHeight() );
//                System.out.println(scrollPane.getVvalue());
            });

            navigationBar.getChildren().add(buttons[i]);
        }

        Scene scene = new Scene(new HBox(scrollPane, new ScrollPane(navigationBar)));
        primaryStage.setScene(scene);
        primaryStage.setTitle("通讯录");
        primaryStage.show();
    }

    //通过电话查找
    private void findByPhoneNum(String phoneNumber){
        //主面板
        VBox mainPane = new VBox();
        mainPane.setPrefSize(300, 400);

        LinkList[] linkLists = addressBook.getContacts();

        for( LinkList linkList : linkLists ){
            mainPane.getChildren().add( find(linkList, phoneNumber) );
        }

        Scene scene = new Scene(mainPane);
        Stage stage = new Stage();
        stage.setTitle("电话查找");
        stage.setScene(scene);
        stage.show();
    }

    //通过邮箱查找
    private void findByEMail(String eMail){
        //主面板
        VBox mainPane = new VBox();
        mainPane.setPrefSize(300,300);

        LinkList[] linkLists = addressBook.getContacts();

        for( LinkList linkList : linkLists ){
            mainPane.getChildren().add( find(linkList, eMail) );
        }

        Scene scene = new Scene(mainPane);
        Stage stage = new Stage();
        stage.setTitle("邮箱查找");
        stage.setScene(scene);
        stage.show();
    }

    //通过姓名查找
    private void findByName(LinkList linkList, String name){
        //主面板
        VBox mainPane = new VBox();
        mainPane.setPrefSize(300,300);

        mainPane.getChildren().add(find(linkList, name));
        Scene scene = new Scene(mainPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("姓名查找");
        stage.show();
    }

    private VBox find(LinkList linkList, String message){
        VBox pane = new VBox();

        //获取头节点
        Node temp = linkList.getHead();

        String text;
        Contact contact;
        while( temp.next != null ){
            temp = temp.next;
            contact = (Contact)temp.data;

            if( isNumber(message) && contact.phoneNumber.equals( message ) ){
                //如果电话相同
                text = contact.name + " 电话：" + contact.phoneNumber + " 邮箱：" + contact.eMail;
                //将姓名相同的联系人信息添加在面板上
                pane.getChildren().add(new Text(text));
            }else if( isChinese(message) && contact.name.equals( message ) ){
                //如果姓名相同
                text = contact.name + " 电话：" + contact.phoneNumber + " 邮箱：" + contact.eMail;
                //将姓名相同的联系人信息添加在面板上
                pane.getChildren().add(new Text(text));
            }else{
                //如果邮箱相同
                text = contact.name + " 电话：" + contact.phoneNumber + " 邮箱：" + contact.eMail;
                //将姓名相同的联系人信息添加在面板上
                pane.getChildren().add(new Text(text));
            }
        }

        return pane;
    }

    //判断字符串是否位数字
    private boolean isNumber(String str){
        for( int i = 0; i < str.length(); i++){
            if( !Character.isDigit(str.charAt(i)) ){
                return false;
            }
        }

        return true;
    }

    //判断是否全为汉字
    private boolean isChinese(String str){
        int n = 0;
        for( int i = 0; i < str.length(); i++){
            n = (int)str.charAt(i);
            if ( !(19968 <= n && n <40869)) {
                return false;
            }
        }

        return true;
    }

    //获取名字首字母下标
    private int getIndex(String chinese){
        String firstLetter = getHeaderOfCN.getFirstLetter( chinese );

        return firstLetter.charAt(0) - 'a';
    }

    //添加内容到链表
    private void add(){
        Stage addStage = new Stage();

        //添加面板
        AddOrUpPane addPane = new AddOrUpPane();

        Scene newScene = new Scene(addPane);
        addStage.setTitle("添加联系人");
        addStage.setScene(newScene);
        addStage.show();

        addPane.oKBtn.setOnAction(e1->{
            //姓名
            String contactName = addPane.name.getText();
            //电话
            String contactNum = addPane.phoneNum.getText();
            //邮箱
            String contactEMail = addPane.eMail.getText();

            //获取添加联系人名字首字母下标
            int index = getIndex( String.valueOf(contactName.charAt(0)));

            //添加
            Contact contact = new Contact(contactName, contactNum,contactEMail);
            addressBook.addContact( index, contact );
            addressBook.getContacts()[index].display();

            //在面板中添加
            addOnPane(contact, index);
            length -= 10;

            //点击确定关闭添加界面
            addStage.close();
        });
    }

    //添加内容到面板
    private void addOnPane(Contact contact, int firstIndex){
        //修改按钮
        Button updateBtn = new Button("修改");
        //删除按钮
        Button deleteBtn = new Button("删除");

        String text ="电话：" + contact.phoneNumber + " 邮箱：" + contact.eMail;
        Text nameText = new Text( contact.name );
        HBox hBox = new HBox(nameText, new Text(text), updateBtn, deleteBtn);
        hBox.setSpacing(5);
        //在面板中添加
        headCharPanes[firstIndex].getChildren().add(hBox);

        //设置点击触发事件
        updateBtn.setOnAction(e2->{
            //更新面板
            AddOrUpPane upPane = new AddOrUpPane();

            //获取修改前，字母下，该子面板的下标
            int upIndex = hBox.getParent().getChildrenUnmodifiable().indexOf(hBox);
//                    System.out.println(upIndex);

            //获取修改后信息联系人姓名首字母下标
            int firLetterI = getIndex( String.valueOf( (nameText.getText()).charAt(0) ) );
//                    System.out.println(firLetterI);

            //将要更改的信息显示在页面
            LinkList upLink = addressBook.getContacts()[firLetterI];
            try {
//                        System.out.println( ((Contact)upLink.get(upIndex)).name );
                upPane.name.setText( ((Contact)upLink.get(upIndex)).name );
                upPane.phoneNum.setText( ((Contact)upLink.get(upIndex)).phoneNumber );
                upPane.eMail.setText( ((Contact)upLink.get(upIndex)).eMail );
            } catch (Exception e3) {
                e3.printStackTrace();
            }

            Scene upScene = new Scene(upPane);
            Stage upStage = new Stage();
            upStage.setTitle("修改信息");
            upStage.setScene(upScene);
            upStage.show();

            //设置点击触发事件
            upPane.oKBtn.setOnAction(e3->{
                //获取更新的信息
                String upName = upPane.name.getText();
                String upNumber = upPane.phoneNum.getText();
                String upEMail = upPane.eMail.getText();

                int firstLetter = getIndex( String.valueOf( upName.charAt(0) ) );

                //如果更改后的姓名首字母不变
                if( firstLetter == firLetterI){
                    addressBook.update( firLetterI, upIndex, new Contact(upName, upNumber, upEMail) );

                    nameText.setText(upName);
                }else{//修改后姓名首字母改变
                    //删除链表中节点
                    addressBook.deleteContact(firLetterI, upIndex);
//                    addressBook.getContacts()[firLetterI].display();
                    //面板删除节点
                    headCharPanes[firLetterI].getChildren().remove(upIndex);

                    //链表中添加节点
                    Contact contact1 = new Contact(upName, upNumber, upEMail);
                    addressBook.addContact( firstLetter, contact1);
//                    addressBook.getContacts()[firstLetter].display();
                    //面板上添加节点
                    addOnPane(contact1, firstLetter);
                }

                upStage.close();
            });
        });

        //设置点击触发事件
        deleteBtn.setOnAction(e2->{
            //获取字母下，该子面板的下标
            int deleteIndex = hBox.getParent().getChildrenUnmodifiable().indexOf(hBox);

            //在链表节点中删除
            addressBook.deleteContact(firstIndex, deleteIndex);
            addressBook.getContacts()[firstIndex].display();

            //在面板中删除
            headCharPanes[firstIndex].getChildren().remove(deleteIndex);
            length += 10;
        });

        headCharPanes[firstIndex].getChildren().addAll();
    }

    //初始化数据
    private void initializeData(){

    }
}
