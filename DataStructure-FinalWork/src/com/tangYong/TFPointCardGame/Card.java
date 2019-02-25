package com.tangYong.TFPointCardGame;


public class Card {
    //随机抽取的纸牌
    private int[] fourCards;

    //操作符
    private char[] operators;
    //左右括弧
    private char[] parentheses;
    //存放拼接字符串
    private String result;
    //
    private Node root;

    //初始化成员变量
    public Card(){
        //初始化纸牌，-1
        fourCards = new int[4];
        for(int i = 0; i < fourCards.length; i++){
            fourCards[i] = -1;
        }

        //0-3 分别表示 + - * /
        operators = new char[4];
        for(int i = 0; i < operators.length; i++){
            if( i == 0){
                operators[i] = '+';
            }else if( i == 1){
                operators[i] = '-';
            }else if( i == 2){
                operators[i] = '*';
            }else{
                operators[i] = '/';
            }
        }

        //0-1 分别表示 ( )
        parentheses = new char[2];
        for(int i = 0; i < parentheses.length; i++){
            if( i == 0){
                parentheses[i] = '(';
            }else{
                parentheses[i] = ')';
            }
        }

        root = new Node();
    }

    //随机产生4张纸牌
    public int[] generateRandomly(){
        int quotient = 0;//商
        int remainder = 0;//余数

        for(int i = 0; i < fourCards.length; i++){
            //从1-52中随机
            //按A - K排布，每种四张
            fourCards[i] = (int)(51*Math.random() + 1);

            //将数字转换为1-13，映射到A - K
            quotient = fourCards[i] / 4;
            remainder = fourCards[i] % 4;

            //如果余数为0，商减一
            if(remainder == 0){
                fourCards[i] = quotient;
            }else{
                fourCards[i] = quotient + 1;
            }
        }

        return fourCards;
    }

    //判断表达式
    public boolean judgeExpression(String expression){
        String postfix = convertToPostfix(expression);
        Double result;
        result = numberCalculate(postfix);

        return result.equals(24.0);
    }

    //将算术表达式转换为后缀表达式
    public String convertToPostfix(String expression){
        LinkStack linkStack = new LinkStack();//运算符栈
        String postfix = "";//后缀表达式
        char c;//表达式字符
        char ac;//出栈字符

        for(int i = 0; i < expression.length(); i++){
            //获取表达式字符
            c = expression.charAt(i);

            //如果不是空格
            if(c != ' '){
                //如果是左括弧
                if(isOpenBracket(c)){
                    linkStack.push(c);
                }else if(isCloseBracket(c)){//如果是右括弧
                    ac = (char)linkStack.pop();
                    //循环直到取到左括弧
                    while(!isOpenBracket(ac)){
                        //将操作符送入后缀表达式
                        postfix =  postfix.concat(String.valueOf(ac));

                        //出栈
                        ac = (char)linkStack.pop();
                    }
                }else if(isOperator(c)){//为操作符
                    //如果栈不为空
                    if(!linkStack.isEmpty()){
                        //如果栈顶元素的优先级大于等于
                        while(linkStack.peek() != null && priority((char)linkStack.peek()) >= priority(c) ){
                            ac = (char)linkStack.pop();
                            postfix = postfix.concat(String.valueOf(ac));
                        }
                    }
                    linkStack.push(c);
                }else{//为操作数
                    postfix = postfix.concat("|");
                    postfix = postfix.concat(String.valueOf(c));

                    if(i + 1 < expression.length()){
                        c = expression.charAt(i + 1);
                    }

                    while( (i + 1) < expression.length() && c != ' ' && !isOperator(c) && !isCloseBracket(c) ){
                        postfix = postfix.concat(String.valueOf(expression.charAt(++i)));

                        if( i + 1 >= expression.length()){
                            break;
                        }else {
                            c = expression.charAt(i + 1);
                        }
                    }

                    postfix = postfix.concat("|");
                }
            }
        }

        //将栈中所有剩余的操作符送往后缀表达式
        while(!linkStack.isEmpty()){
            postfix = postfix.concat(String.valueOf(((char)linkStack.pop())));
        }

        return postfix;
    }

    //对后缀表达式进行求值计算
    public double numberCalculate(String postfix) {
        LinkStack linkStack = new LinkStack();
        char c;

        //入栈操作数
        String number;

        //要进行计算的操作数
        double number1;
        double number2;
        double result;

        for(int i = 0; i < postfix.length(); i++){
            //字符串中取出的字符
            c = postfix.charAt(i);

            //如果是运算符
            if(isOperator(c)){
                //注意出栈顺序赋值
                number2 = (double)linkStack.pop();
                number1 = (double)linkStack.pop();

                if(c == '*'){
                    result = number1 * number2;
                }else if(c == '/'){
                    result = number1 / number2;
                }else if(c == '+'){
                    result = number1 + number2;
                }else{
                    result = number1 - number2;
                }

                linkStack.push(result);
            }else{//为操作数
                number = "";
                while((i + 1) < postfix.length() && postfix.charAt(++i) != '|'){
                    number = number.concat(String.valueOf(postfix.charAt(i)));
                }

                //字符串非空的情况下，将数入栈
                if (!number.equals("")) {
                    linkStack.push(Double.valueOf(number));
                }
            }
        }

        return (double)linkStack.pop();
    }

    //判断是否为运算符
    private boolean isOperator(char operator){
        if( operator == '*' || operator == '/' || operator == '+' || operator == '-'){
            return true;
        }else {
            return false;
        }
    }

    //为开括弧
    private boolean isOpenBracket(char c){
        if(c == '('){
            return true;
        }else {
            return false;
        }
    }

    private boolean isCloseBracket(char c){
        if(c == ')'){
            return true;
        }else {
            return false;
        }
    }

    //判断运算符优先级
    private int priority(char operator){
        if(operator == '*' || operator == '/'){
            return 2;
        }else if( operator == '+' || operator == '-'){
            return 1;
        }else{
            //非法操作符
            return 0;
        }
    }

    //验证操作数是否合法
    public boolean checkNumbers(String expression){
        String numberStr = "";
        Double number = 0.0;
        char c;

        for(int i = 0; i < expression.length(); i++){
            c = expression.charAt(i);

            if(c != ' ') {
                //为操作数
                if (!isOperator(c) && c != '(' && c != ')') {
                    numberStr = "";

                    while (!isOperator(c) && c != '(' && c != ')') {
                        numberStr = numberStr.concat(String.valueOf(c));

                        ++i;

                        if (i < expression.length()) {
                            c = expression.charAt(i);
                        } else {
                            break;
                        }
                    }

                    //如果字符串不为空
                    if (!numberStr.equals("")) {
                        number = Double.valueOf(numberStr);

                        if (!isLegal(number)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    //验证操作数是否合法
    private boolean isLegal(Double number){
        for(int card : fourCards){
            //与四张牌的一张符合
            if( number.equals(Double.valueOf(String.valueOf(card))) ){
                return true;
            }
        }

        //都不符合
        return false;
    }

    //获取不含括号的合法算术表达式
    public LinkList withoutBracket(){
        LinkList linkList = new LinkList();
        //已经被选择的的纸牌的下标
        int[] indexFir = new int[1];;

        int endIndex;

        for(int i = 0; i < fourCards.length; i++){
            //已被选中的纸牌的下标
            indexFir[0] = i;

            //获取第二个操作符和操作符
            String[] resultsFir = operandOperator(remainCard(indexFir));

            //拼接第一个和第二个
            for(int j = 0; j < resultsFir.length; j++){
                //如果不是空字符串
                if( !resultsFir[j].equals("")){
                    //提取被选纸牌下标
                    int indexCurrent = Integer.valueOf(resultsFir[j].charAt(resultsFir[j].length() - 1) + "");
                    //更新被选择的卡牌
                    int[] indexScd = {indexFir[0], indexCurrent};

                    //拼接第一个操作数和第二个操作数
                    if(fourCards[i] < 10){
                        result = " ".concat(String.valueOf(fourCards[i]));
                    }else{
                        result = String.valueOf(fourCards[i]);
                    }

                    //拼接
                   String[] resultsScd = spliceString(resultsFir[j].substring(0, resultsFir[j].length() - 1), remainCard(indexScd));

                    //拼接第三个
                    for(int k = 0; k < resultsScd.length; k++){
                        //如果不是空字符串
                        if( !resultsScd[k].equals("") ){
                            //提取被选纸牌下标
                            indexCurrent = Integer.valueOf(resultsScd[k].charAt(resultsScd[k].length() - 1) + "");
                            //更新被选纸牌
                            int[] indexThird = {indexScd[0], indexScd[1], indexCurrent};

                            //拼接
                            String[] resultsThird = spliceString(resultsScd[k].substring(0, resultsScd[k].length() - 1), remainCard(indexThird));

                            for(int l = 0; l < resultsThird.length; l++){
                                if(!resultsThird[l].equals("")){
                                    result = result.concat(resultsThird[l].substring(0, resultsThird[l].length() - 1));

                                    try {
                                        linkList.insert(linkList.getSize(), result);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    goToPrevious(resultsThird[l]);
                                }
                            }

                            goToPrevious(resultsScd[k]);
                        }
                    }
                }
            }

        }


        return linkList;
    }

    //将拼接结果返还到上一级
    private void goToPrevious(String currentResult){
        int endIndex = result.length() - (currentResult.length() - 1);
        result = result.substring(0, endIndex);
    }

    //拼接
    private String[] spliceString(String splicedString, int[] remainCards){
        result = result.concat(splicedString);
        return  operandOperator(remainCards);
    }

    //剩余没有被选择的纸牌
    private int[] remainCard(int[] index){
        int[] remain = new int[fourCards.length - index.length];
        boolean flag = true;

        //循环判断没有被选择的纸牌
        for(int j = 0, i = 0; j < fourCards.length; j++){
            for(int k = 0; k < index.length; k++){
                //已经被选择
                if( j == index[k] ){
                    flag = false;
                    break;
                }
            }

            if(flag){
                remain[i] = j;
                i += 1;
            }

            flag = true;
        }

        //返回没有被选中的纸牌的下标
        return remain;
    }

    //操作数+操作符+纸牌下标
    private String[] operandOperator(int[] operandsIndex){
        //操作数 * 四种操作符
        String[] results = new String[operandsIndex.length * 4];
        String result;

        int i = 0;
        //遍历四种操作符
        for(int j = 0; j < operators.length; j++){
            for(int k = 0; k < operandsIndex.length; k ++){
                //如果是除号
                if(operators[j] == '/'){
                    //如果操作数为0，字符串为空
                    if(fourCards[operandsIndex[k]] == 0){
                        results[i++] = "";
                    }else {
                        //操作符+操作数+被选择数下标
                        result = String.valueOf(operators[j]);
                        //如果不是两位数，添加标记符号
                        if(fourCards[operandsIndex[k]] < 10){
                            result = result.concat(" ");
                        }
                        results[i++] = result.concat(String.valueOf(fourCards[operandsIndex[k]])).concat(String.valueOf(operandsIndex[k]));
                    }
                }else {//不是除号，直接拼接
                    //操作符+操作数+被选择数下标
                    result = String.valueOf(operators[j]);
                    //如果不是两位数，添加标记符号
                    if(fourCards[operandsIndex[k]] < 10){
                        result = result.concat(" ");
                    }
                    results[i++] = result.concat(String.valueOf(fourCards[operandsIndex[k]])).concat(String.valueOf(operandsIndex[k]));
                }
            }
        }

        return results;
    }

    //检验是否生成纸牌
    public boolean verifyFCards(){
        for(int card : fourCards){
            if ( card == -1 ) {
                return false;
            }
        }

        return true;
    }

    //对无括弧表达式插入括弧，并计算所有的结果为24的表达式
    public void getExpressions(LinkList linkList){
        //带有括号的链表
        LinkList newLinkList = new LinkList();

        //若没有生成纸牌
        if( !verifyFCards() ){
            return;
        }

        //数组存放括弧可能出现的位置
        int[][] positionOfBrackets= {
                {0, 6}, {3, 9}, {6, 12}, {0, 9}, {3, 12},
                {0, 1, 7, 11}, {0, 4, 10, 11}, {3, 4, 10, 14},
                {3, 7, 13, 14}, {0, 6, 8, 14}
        };

        Node temp = linkList.getHead();
//        int length = linkList.getSize();
        String data;
        String tempData;
        String before;
        String next;

        while( temp.next != null){
            try {
                //获取下一个节点
                temp = temp.next;

                //获取节点数据
                data = (String)temp.data;

                if( !judgeExpression( removeFlags(data)) ){
                    try {
                        linkList.remove(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                for(int j = 0; j < positionOfBrackets.length; j++){
                    tempData = data;
                    for(int k = 0; k < positionOfBrackets[j].length; k++){
                        if(positionOfBrackets[j][k] == 0){//开始位置
                            tempData = "(".concat(tempData);
                        }else if(positionOfBrackets[j][k] == tempData.length()){//末尾位置
                            tempData = tempData.concat(")");
                        }else {//中间位置
                            before = tempData.substring(0, positionOfBrackets[j][k]);
                            next = tempData.substring(positionOfBrackets[j][k], tempData.length());

                            //如果是一对括弧或者两队不同的括弧
                            if(j < positionOfBrackets.length - 1){
                                //如果当前下标小于长度除以2，为开括弧
                                if(k < positionOfBrackets[j].length/2){
                                    tempData = before.concat("(").concat(next);
                                }else{//为闭括弧
                                    tempData = before.concat(")").concat(next);
                                }
                            }else{//为两对相同的括弧
                                if(positionOfBrackets[j][k] == 6){
                                    tempData = before.concat(")").concat(next);
                                }else{
                                    tempData = before.concat("(").concat(next);

                                }
                            }
                        }
                    }

                    tempData = removeFlags(tempData);
                    //如果结果是24，添加新的数据
                    if(judgeExpression(tempData)){
                        newLinkList.insert(newLinkList.getSize(), tempData);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        temp = newLinkList.getHead();

        while(temp.next != null){
            temp = temp.next;
            try {
                linkList.insert(linkList.getSize(), temp.data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        root = linkList.getHead();
    }

    //去除标记符号
    private String removeFlags(String str){
        String before;
        String next;

        for(int i = 0; i< str.length(); i++){
            if(str.charAt(i) == ' '){
                before = str.substring(0, i);
                next = str.substring(i + 1, str.length());
                str = before.concat(next);
                i--;
            }
        }

        return str;
    }

    public int[] getFourCards() {
        return fourCards;
    }

    public void setFourCards(int[] fourCards) {
        this.fourCards = fourCards;
    }

    public char[] getOperators() {
        return operators;
    }

    public void setOperators(char[] operators) {
        this.operators = operators;
    }

    public char[] getParentheses() {
        return parentheses;
    }

    public void setParentheses(char[] parentheses) {
        this.parentheses = parentheses;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
