package com.beagle.java.projects.starfucks;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.beagle.java.projects.starfucks.utils.Utils.intToString;

public class Main {
    static Manager manager = new Manager();
    

    public static void main(String[] args) {
        while (true) {
            System.out.println(printMenu());
            int value = getInput();
            if (value != -1) {
                doProcess(value);
            }
        }

    }

    private static void doProcess(int value) {
        int input = 0;
        switch (value) {
            case 1:
                System.out.println("== 주문하기 ==\n");
                String foodIndex = "";
                String foodCount = "";
                boolean run = true;
                while (run) {
                    System.out.println(manager.showMenu());
                    System.out.println("음식 >> \n");
                    int inputData1 = getInput();
                    System.out.println("수량 >> \n");
                    int inputData2 = getInput();

                    if (manager.checkInputFoodNumber(inputData1, inputData2)) {
                        foodIndex += inputData1 + "/";
                        foodCount += inputData2 + "/";
                    } else {
                        System.out.println("잘못된 입력입니다. 다시 입력해 주십시오.");
                    }

                    System.out.println("1. 계속 주문하기\n2. 그만하기");
                    int inputData3 = getInput();
                    if (inputData3 == 2) {
                        run = false;
                    }
                }
                manager.getOrders(foodIndex.split("/"), foodCount.split("/"));
                break;
            case 2:
                System.out.println("== 메뉴 보기 ==\n");
                System.out.println(manager.showMenu());
                break;
            case 3:
                System.out.println("== 주문 목록 보기 ==\n");
                System.out.println(manager.showOrderList());
                break;
            case 4:
                System.out.println("== 카페 나가기 ==\n");
                int inputData = getInput();
                System.out.println(manager.leavingCafe(intToString(inputData)));
                break;
            case 5:
                System.exit(0);
        }
    }



    private static int getInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("입력값은 정수 값이어야 합니다. ");
            return -1;
        }
    }

    public static String printMenu() {
        String str;
        str = "== Welcome to StarFucks ==\n" +
                "1. 주문하기\n" +
                "2. 메뉴 보기\n" +
                "3. 주문 목록 보기\n" +
                "4. 카페 나가기\n" +
                "5. 프로그램 종료\n" +
                "---------------------------------\n" +
                "입력 >> ";

        return str;
    }
}
