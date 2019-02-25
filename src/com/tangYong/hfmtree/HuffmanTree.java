package com.tangYong.hfmtree;

public class HuffmanTree {
    private HuffmanNode root;
    private int nodeNumber;

    public HuffmanTree(){
        root = null;
        nodeNumber = 0;
    }

    //构建哈夫曼树，返回哈夫曼编码
    public int[][] createHuffmanTree(HuffmanNode[] huffmanNodes){
        //叶结点的数
        int n = huffmanNodes.length;
        //哈夫曼树的结点数
        int m = 2*n - 1;
        nodeNumber = m;

        HuffmanNode[] HN = new HuffmanNode[m];

        int i;
        //n个有权值和字母的结点
        for(i = 0; i < n; i++){
            HN[i] = huffmanNodes[i];
        }

        //返回下标
        int index;
        //构建哈夫曼树
        for(i = n; i < m; i++){
            //在HN[0, i - 1]选择不在哈夫曼树中而且权值最小的两个结点
            index = selectMin(HN, i - 1);
            HuffmanNode min1 = HN[index];
            HN[index].setFlag(1);

            index = selectMin(HN, i -1);
            HuffmanNode min2 = HN[index];
            HN[index].setFlag(1);

            //构造min1和min2的父结点，并修改其父节点的权值
            HN[i] = new HuffmanNode();
            min1.setParent(HN[i]);//设置父节点
            min2.setParent(HN[i]);//设置父节点
            HN[i].setlChild(min1);//设置左孩子
            HN[i].setrChild(min2);//设置右孩子
            HN[i].setWeight(min1.getWeight() + min2.getWeight());//权值
        }

        //根据叶结点找到头结点
        HuffmanNode head = huffmanNodes[0];
        while(head.getParent() != null){
            head = head.getParent();
        }

        //设置头结点
        this.root = head;

        //从叶结点到根逆向求每个字符的哈夫曼编码
        int[][] huffCode = new int[n][n];
        int start;
        for(int j = 0; j < n; j++){
            //从编码的开始位置，初始化为数组的结尾
            start = n - 1;
            for(HuffmanNode c = HN[j], p = c.getParent(); p != null; c = p, p = p.getParent()){
                if(p.getlChild().equals(c)){
                    huffCode[j][start--] = 0;
                }else{
                    huffCode[j][start--] = 1;
                }
            }
            //编码的开始设置为-1，编码是-1之后的01序列
            huffCode[j][start] = HN[j].getLetter();
        }

        return huffCode;
    }

    //找出0-end权值最小结点
    private int selectMin(HuffmanNode[] HN, int end){
        //记录不在哈夫曼树中且权值最小的结点下标
        int index = -1;
        HuffmanNode min = new HuffmanNode();

        //寻找没有哈夫曼树中的结点
        for(int i = 0; i <= end; i++){
            if(HN[i].getFlag() == 0){
                min = HN[i];
                index = i;
                break;
            }
        }

        for(int i = 0; i <= end; i++){
            HuffmanNode huffmanNode = HN[i];
            //不在哈夫曼树中且权值最小
            if(huffmanNode.getFlag() == 0 && huffmanNode.getWeight() < min.getWeight()){
                min = huffmanNode;
                index = i;
            }
        }

        return index;
    }

    //通过将哈夫曼树转化为数组储存
    public HuffmanNode[] createArray(){
        if(root == null || nodeNumber == 0){
            System.out.println("没有哈夫曼树，请构造哈夫曼树。");
            return new HuffmanNode[0];
        }

        //创建储存数组
        HuffmanNode[] huffmanNodes = new HuffmanNode[nodeNumber];
        HuffmanNode t = root;
        int i = 0;
        //层次遍历
        LinkQueue linkQueue = new LinkQueue();
        linkQueue.offer(t);
        while(!linkQueue.isEmpty()){
            t = (HuffmanNode)linkQueue.poll();
            huffmanNodes[i++] = t;

            if(t.getlChild() != null){
                linkQueue.offer(t.getlChild());
            }
            if(t.getrChild() != null){
                linkQueue.offer(t.getrChild());
            }
        }

        return huffmanNodes;
    }

    //译码
    public String decoding(String code, String huffmanTree){
        //将哈夫曼树字符串转换为数组
        String[] hfmNodes = new String[nodeNumber];
        //记录哈夫曼树String下标
        int hfmIndex = 0;
        for(int i = 0; i < nodeNumber; i++){
            hfmNodes[i] = "";
            while(huffmanTree.charAt(hfmIndex) != ' '){
                hfmNodes[i] += huffmanTree.charAt(hfmIndex++) + "";
            }
            hfmIndex++;
        }

        //存放0或1
        char c;
        int length = code.length();

        //译码字母的下标
        int index = 0;
        //译码内容
        String decodeText = "";

        //循环找到哈夫曼树中的字母
        for(int i = 0; i < length; i++){
            c = code.charAt(i);

            //0为左子树,否则为右子树
            if(c == '0'){
                index = index*2 + 1;
            }else {
                index = index*2 + 2;
            }

            //如果当前下标的内容为字母，重置译码字母下标，寻找下一个
            if( 'a'<= hfmNodes[index].charAt(0) && hfmNodes[index].charAt(0) <= 'z'){
                decodeText += (hfmNodes[index].charAt(0) + "");
                index = 0;
            }
        }

        return decodeText;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanNode root) {
        this.root = root;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }
}
