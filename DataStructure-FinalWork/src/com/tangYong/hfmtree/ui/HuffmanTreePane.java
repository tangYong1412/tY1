package com.tangYong.hfmtree.ui;

import com.tangYong.hfmtree.HuffmanNode;
import com.tangYong.hfmtree.HuffmanTree;
import com.tangYong.hfmtree.LinkQueue;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HuffmanTreePane extends Pane{
    private HuffmanTree huffmanTree;

    public HuffmanTreePane(HuffmanTree huffmanTree){
        this.huffmanTree = huffmanTree;
        double angle = 10;
        double radius = 20;

        //先序遍历设置每个节点的坐标
        setCoordinate(this.huffmanTree.getRoot(), 100, angle, radius);

        //层次遍历在面板上添加节点
        HuffmanNode t = this.huffmanTree.getRoot();
        if(t != null){
            LinkQueue L = new LinkQueue();
            L.offer(t);
            while(!L.isEmpty()){
                t = (HuffmanNode)L.poll();

                //设置节点位置
                Circle circle = new Circle(t.getCenter()[0], t.getCenter()[1], radius);
                circle.setFill(Color.WHITE);
                circle.setStroke(Color.BLACK);
                getChildren().add(circle);

                //设置节点权值
                Text text = new Text(String.valueOf(t.getWeight()));
                text.setTextAlignment(TextAlignment.LEFT);
                text.setX(t.getCenter()[0] - 5);
                text.setY(t.getCenter()[1] + 5);
                getChildren().add(text);

                if(t.getlChild() != null && t.getrChild() != null){
                    double radians = Math.toRadians(angle);

                    //获取半径x值
                    double radiusX = radius * Math.cos(radians);
                    //获取半径y值
                    double radiusY = radius * Math.sin(radians);

                    //左树干
                    double startX = t.getCenter()[0] - radiusX;
                    double startY = t.getCenter()[1] + radiusY;
                    double endX = t.getlChild().getCenter()[0] + radiusX;
                    double endY = t.getlChild().getCenter()[1] - radiusY;

                    Text text1 = new Text("0");
                    text1.setX( startX + (endX - startX) / 2 - 5);
                    text1.setY( startY + (endY - startY) / 2);
                    Line lineLeft = new Line(startX, startY, endX, endY);

                    //右树干
                    startX = t.getCenter()[0] + radiusX;
                    endX = t.getrChild().getCenter()[0] - radiusX;

                    Text text2 = new Text("1");
                    text2.setX( startX + (endX - startX) / 2);
                    text2.setY( startY + (endY - startY) / 2);
                    Line lineRight = new Line(startX, startY, endX, endY);

                    getChildren().addAll(lineLeft, lineRight, text1, text2);

                    angle += 5;
                }

                //如果为叶节点，显示字母
                if(t.getLetter() != '*'){
                    Text text3 = new Text();
                    text3.setText(t.getLetter() + "");
                    text3.setTextAlignment(TextAlignment.CENTER);
                    text3.setX(t.getCenter()[0] - 5);
                    text3.setY(t.getCenter()[1] + radius + 10);
                    getChildren().add(text3);
                }

                if(t.getlChild() != null){
                    L.offer(t.getlChild());
                }

                if(t.getrChild() != null){
                    L.offer(t.getrChild());
                }
            }
        }
    }

    //先序遍历设置每个节点的坐标
    public void setCoordinate(HuffmanNode huffmanNode, double maxLength, double angle, double radius){
        if(huffmanNode != null){
            //如果是头节点
            if(huffmanNode == huffmanTree.getRoot()){
                double[] center = {400, 50};
                huffmanNode.setCenter(center);
            }

            if(huffmanNode.getlChild() != null && huffmanNode.getrChild() != null){
                //角度转换为弧度
                double radians = Math.toRadians(angle);

                //层级越深，树干越短
                maxLength -= 10;

                //计算节点坐标
                double x = huffmanNode.getCenter()[0] - (2 * radius + maxLength) * Math.cos(radians);
                double y = huffmanNode.getCenter()[1] + (2 * radius + maxLength) * Math.sin(radians);

                //左节点坐标
                double[] lChildCenter = {x, y};

                x = huffmanNode.getCenter()[0] + (2 * radius + maxLength) * Math.cos(radians);

                //右节点坐标
                double[] rChildCenter = {x, y};

                huffmanNode.getlChild().setCenter(lChildCenter);
                huffmanNode.getrChild().setCenter(rChildCenter);
            }

            setCoordinate(huffmanNode.getlChild(), maxLength, angle + 20, radius);
            setCoordinate(huffmanNode.getrChild(), maxLength, angle + 20, radius);
        }
    }

    public HuffmanTree getHuffmanTree() {
        return huffmanTree;
    }

    public void setHuffmanTree(HuffmanTree huffmanTree) {
        this.huffmanTree = huffmanTree;
    }
}
