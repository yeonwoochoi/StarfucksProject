package com.beagle.java.projects.starfucks;

import com.beagle.java.projects.starfucks.collection.StarFucksList;


import java.util.InputMismatchException;
import java.util.Scanner;

import static com.beagle.java.projects.starfucks.utils.Utils.intToString;
import static com.beagle.java.projects.starfucks.utils.Utils.stringToInt;

public class Main {
    static Manager manager = new Manager();
    

    public static void main(String[] args) {
        manager.startProgram();
        while (true) {
            System.out.println(printMenu());
            int value = getInput();
            if (value != -1) {
                doProcess(value);
            }
        }

    }

    /**
     * input data by using Scanner
     * @param value input data of int type
     */
    private static void doProcess(int value) {
        int input = 0;
        switch (value) {
            case 1:
                System.out.println("== 주문하기 ==\n");
                StarFucksList<String> foodIndexList = new StarFucksList<>();
                StarFucksList<String> foodCountList = new StarFucksList<>();
                boolean run = true;
                while (run) {
                    System.out.println(manager.showMenu());
                    System.out.println("음식 >> \n");
                    String  inputData1 = getInputStr();
                    System.out.println("수량 >> \n");
                    String  inputData2 = getInputStr();

                    if (manager.checkInputFoodNumber(stringToInt(inputData1), stringToInt(inputData2))) {
                        foodIndexList.addLast(inputData1);
                        foodCountList.addLast(inputData2);
                    } else {
                        System.out.println("잘못된 입력입니다. 다시 입력해 주십시오.");
                    }

                    System.out.println("1. 계속 주문하기\n2. 그만하기");
                    int inputData3 = getInput();
                    if (inputData3 == 2) {
                        run = false;
                    }
                }
                manager.getOrders(foodIndexList, foodCountList);
                break;
            case 2:
                System.out.println("== 회원 정보 관리 ==\n\n1.회원 가입하기\n2.회원 정보 검색\n3.회원 정보 업데이트\n4.회원 탈퇴\n\n입력 >> ");
                int inputData4 = getInput();
                switch(inputData4) {
                    case 1:
                        System.out.println("-- 회원 가입하기 --\n id >>");
                        String signUpId = getInputStr();
                        System.out.println("name >>");
                        String signUpName = getInputStr();
                        System.out.println("phone number >>");
                        String signUpPhoneNumber = getInputStr();
                        System.out.println("email >>");
                        String signUpEmail = getInputStr();
                        System.out.println(manager.signUpCustomer(signUpId, signUpName, signUpPhoneNumber, signUpEmail));
                        break;
                    case 2:
                        System.out.println("-- 회원 정보 겁색 --\n id >>");
                        String readId = getInputStr();
                        System.out.println(manager.viewCustomerData(readId));
                        break;
                    case 3:
                        System.out.println("-- 회원 정보 업데이트 --\n 업데이트할 id >>");
                        String inputId2 = getInputStr();
                        String exist = manager.viewCustomerData(inputId2);
                        if (exist.equals("존재하지 않는 아이디입니다")) {
                            System.out.println(exist);
                        } else {
                            System.out.println("-- 기존 정보 -- \n"+exist);
                            System.out.println("name >>");
                            String inputName1 = getInputStr();
                            System.out.println("phone number >>");
                            String inputPhoneNumber1 = getInputStr();
                            System.out.println("email >>");
                            String inputEmail1 = getInputStr();
                            System.out.println(manager.editCustomerData(inputId2, inputName1, inputPhoneNumber1, inputEmail1));
                        }
                        break;
                    case 4:
                        System.out.println("-- 회원 탈퇴 --\n 탈퇴할 id >>");
                        String inputId3 = getInputStr();
                        System.out.println(manager.withdraw(inputId3));
                        break;
                    default:
                        System.out.println("1,2,3,4 중 하나만 입력해주십시오.");
                }
                break;
            case 3:
                System.out.println("== 메뉴 보기 ==\n");
                System.out.println(manager.showMenu());
                break;
            case 4:
                System.out.println("== 주문 목록 보기 ==\n");
                System.out.println(manager.showOrderList());
                break;
            case 5:
                System.out.println("== 바리스타 상황 보기 == \n" + manager.checkBarista());
                break;
            case 6:
                System.out.println("== 카페 나가기 ==\n");
                int inputData = getInput();
                System.out.println(manager.leavingCafe(intToString(inputData)));
                break;
            case 7:
                manager.endOfProgram();
                System.exit(0);
            default:
                System.out.println("1,2,3,4,5,6,7 중 하나만 입력해주십시오.");
        }
    }


    /**
     * Method used to input data directly by using scanner
     * @return (int) input data
     */
    private static int getInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("입력값은 정수 값이어야 합니다. ");
            return -1;
        }
    }

    private static String getInputStr() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.next();
        } catch (InputMismatchException e) {
            System.out.println("입력값은 string 값이어야 합니다.");
            return "";
        }
    }

    /**
     * Method that shows what the int type data received mean
     * @return (String) menu
     */
    public static String printMenu() {
        String str;
        str = "== Welcome to StarFucks ==\n" +
                "1. 주문하기\n" +
                "2. 회원 정보 관리\n" +
                "3. 메뉴 보기\n" +
                "4. 주문 목록 보기\n" +
                "5. 바리스타 상황 보기\n" +
                "6. 카페 나가기\n" +
                "7. 프로그램 종료\n" +
                "---------------------------------\n" +
                "입력 >> ";

        return str;
    }
}
