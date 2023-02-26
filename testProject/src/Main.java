import java.util.Random;
import java.util.Scanner;

// enum с римскими числами
enum RomanNum {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10), XI(11), XII(12), XIII(13), XIV(14), XV(15), XVI(16), XVII(17), XVIII(18), XIX(19), XX(20),
    XXI(21), XXII(22), XXIII(23), XXIV(24), XXV(25), XXVI(26), XXVII(27), XXVIII(28), XXIX(29), XXX(30), XXXI(31), XXXII(32), XXXIII(33), XXXIV(34), XXXV(35), XXXVI(36), XXXVII(37),
    XXXVIII(38), XXXIX(39), XL(40), XLI(41), XLII(42), XLIII(43), XLIV(44), XLV(45), XLVI(46), XLVII(47), XLVIII(48), XLIX(49), L(50), LI(51), LII(52), LIII(53), LIV(54), LV(55),
    LVI(56), LVII(57), LVIII(58), LIX(59), LX(60), LXI(61), LXII(62), LXIII(63), LXIV(64), LXV(65), LXVI(66), LXVII(67), LXVIII(68), LXIX(69), LXX(70), LXXI(71), LXXII(72), LXXIII(73),
    LXXIV(74), LXXV(75), LXXVI(76), LXXVII(77), LXXVIII(78), LXXIX(79), LXXX(80), LXXXI(81), LXXXII(82), LXXXIII(83), LXXXIV(84), LXXXV(85), LXXXVI(86), LXXXVII(87), LXXXVIII(88),
    LXXXIX(89), XC(90), XCI(91), XCII(92), XCIII(93), XCIV(94), XCV(95), XCVI(96), XCVII(97), XCVIII(98), XCIX(99), C(100);

    private int num;
    RomanNum(int num){ this.num = num; }
    public int getValue() { return num; }
    public static RomanNum getByValue(int res) {
        for (RomanNum rn : RomanNum.values()) {
            if (rn.getValue() == res)
                return rn;
        }
        throw new IllegalArgumentException("No RomanNumber with value " + res);
    }

    String strIfElse;
    public static String ifElse(String strIfElse) {
        strIfElse = strIfElse;
        return strIfElse;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String string = in.nextLine(); // строка ввода пользователя

        System.out.println(calc(string));
    }

    // метод калькулятора и проверки
    public static String calc(String input) throws Exception {
        String[] arrayString = input.split(" "); // разделение строки

        // проверка на одно значение в строке
        if (arrayString.length == 1)
            throw new Exception("Строка не является математической операцией.");

        String strNum1 = arrayString[0]; // первое число
        String strNum2 = arrayString[2]; // второе число
        String strH = arrayString[1]; // знак
        String result; // результат

        // проверка на неправильный формат строки
        if (arrayString.length == 5)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *).");

        //условие для расчета арабских или римских значений
        if (isNumeric(strNum1) == false && isNumeric(strNum2) == false)
            return result = Rom(strNum1, strNum2, strH);
        else if (isNumeric(strNum1) == true && isNumeric(strNum2) == true)
            return result = Arab(strNum1, strNum2, strH);
        else
            throw new Exception("Используются одновременно разные системы счисления.");
    }
    //(RomanNum.ifElse(strNum1) == strNum1) && (RomanNum.ifElse(strNum2) == strNum2)
    // для проверки того, что числа арабские
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // метод арабских чисел
    public static String Arab(String strNum1, String strNum2, String strH) throws Exception {
        int num1 = Integer.parseInt(strNum1); // преобразуем первое число в int
        int num2 = Integer.parseInt(strNum2); // преобразуем второе число в int
        int res = 0;

        //арабские
        if ((num1 > 0 && num1 < 11) && (num2 > 0 && num2 < 11)) {
            if (strH.equals("+")) {
                res = num1 + num2;
            } else if (strH.equals("-")) {
                res = num1 - num2;
            } else if (strH.equals("*")) {
                res = num1 * num2;
            } else if (strH.equals("/")) {
                res = num1 / num2;
            }
            return Integer.toString(res); // возвращаем ответ
        }
        else
            throw new Exception("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
    }

    // метод римских чисел
    public static String Rom(String strNum1, String strNum2, String strH) throws Exception {
        // римские
        RomanNum romanNum1 = RomanNum.valueOf(strNum1); // находим первое число в enum
        RomanNum romanNum2 = RomanNum.valueOf(strNum2); // находим второе число в enum
        int rNum1 = romanNum1.getValue(); // преобразуем первое число в int
        int rNum2 = romanNum2.getValue(); // преобразуем второе число в int
        int res = 0;

        //римские
        if (strH.equals("+")) {
            res = rNum1 + rNum2;
        } else if (strH.equals("-")) {
            if (rNum2 > rNum1)
                throw new Exception("В римской системе нет отрицательных чисел.");
            res = rNum1 - rNum2;
        } else if (strH.equals("*")) {
            res = rNum1 * rNum2;
        } else if (strH.equals("/")) {
            res = rNum1 / rNum2;
        }
        RomanNum result = RomanNum.getByValue(res); // находит римскую цифру
        return result.name(); // возвращаем ответ
    }
}
