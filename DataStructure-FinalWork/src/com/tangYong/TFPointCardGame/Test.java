package com.tangYong.TFPointCardGame;

public class Test {
    public static void main(String[] args) {
        Card card = new Card();

        //随机产生四张纸牌
        int[] cards = card.generateRandomly();
        for(int i = 0; i < cards.length; i++){
            System.out.print(cards[i] + " ");
        }
        System.out.println();

        System.out.println(card.numberCalculate(card.convertToPostfix("13+12-11*11")));
//        Node temp = card.getExpressions(card.withoutBracket());
//        while(temp.next != null){
//            System.out.println(temp.data);
//        }
//        System.out.println("请输入表达式：");
//        Scanner input = new Scanner(System.in);
//        String expression = input.nextLine();
//
//        //验证输入操作数是否合法
//        if(!card.checkNumbers(expression)){
//            System.out.println("输入操作数不合法。");
//        }else {
//            boolean result = card.judgeExpression(expression);
//
//            if(result){
//                System.out.println("表达式计算结果为24");
//            }else{
//                System.out.println("表达式计算结果不为24");
//            }
//        }


    }
}
