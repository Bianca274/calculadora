import java.util.Scanner;

public class calculadora {

    public static void main(String[] args){

        Scanner teclado = new Scanner(System.in);

        Pilha<Double> numero = new Pilha<Double>();
        Pilha<String> operador = new Pilha<String>();
        String expressao;
        double num1=0, num2=0;
        boolean potencia=false;
        char ch;
        double resultado=0;

        System.out.println("Informe a expressão ");
        expressao = teclado.nextLine();

        for(int i=0; i < expressao.length(); i++) {
            ch = expressao.charAt(i);

            if (ch == '^') {
                if (operador.isEmpty()) {
                    operador.push(String.valueOf(ch));
                } else {
                    while (operador.top().equals("^")) {

                        if (operador.isEmpty()) {
                            break;
                        }
                        num1 = numero.top();
                        numero.pop();

                        num2 = numero.top();
                        numero.pop();

                        operador.pop();
                        resultado = Math.pow(num2, num1);
                        numero.push(resultado);

                    }

                    if (expressao.charAt(i+1) == '(') {
                        potencia = true;
                    }
                    operador.push(String.valueOf(ch));
                }
            } else if (ch == '/' || ch == '*') {

                if (operador.isEmpty()) {
                    operador.push(String.valueOf(ch));
                } else {
                    while (operador.top().equals("*") || operador.top().equals("/") || operador.top().equals("^")) {
                        if (operador.isEmpty()) {
                            break;
                        }
                        num1 = numero.top();
                        numero.pop();

                        num2 = numero.top();
                        numero.pop();

                        if (operador.top().equals("*")) {
                            resultado = num2 * num1;
                            operador.pop();

                        } else if (operador.top().equals("/")) {
                            resultado = num2 / num1;
                            operador.pop();

                        } else if (operador.top().equals("^")) {
                            resultado = Math.pow(num2, num1);
                            operador.pop();
                        }

                        numero.push(resultado);

                    }
                    operador.push(String.valueOf(ch));
                }
            } else if (ch == '+' || ch == '-') {
                if (operador.isEmpty()) {
                    operador.push(String.valueOf(ch));
                } else {
                    while (operador.top().equals("+") || operador.top().equals("-") || operador.top().equals("*") || operador.top().equals("/") || operador.top().equals("^")) {
                        num1 = numero.top();
                        numero.pop();

                        num2 = numero.top();
                        numero.pop();

                        if (operador.isEmpty()) {
                            break;
                        }
                        if (operador.top().equals("*")) {
                            resultado = num2 * num1;
                            operador.pop();

                        } else if (operador.top().equals("/")) {
                            resultado = num2 / num1;
                            operador.pop();

                        } else if (operador.top().equals("^")) {
                            resultado = Math.pow(num2, num1);
                            operador.pop();

                        } else if (operador.top().equals("-")) {
                            resultado = num2 - num1;
                            operador.pop();

                        } else if (operador.top().equals("+")) {
                            resultado = num2 + num1;
                            operador.pop();

                        }

                        numero.push(resultado);

                    }
                    operador.push(String.valueOf(ch));
                }
            } else if (ch == '(') {
                operador.push(String.valueOf(ch));
            } else if (ch == ' ') {
            } else if (ch == ')') {
                while (operador.top().equals("(") == false) {

                    num1 = numero.top();
                    numero.pop();

                    num2 = numero.top();
                    numero.pop();

                    if (operador.isEmpty()) {
                        break;
                    }
                    if (operador.top().equals("*")) {
                        resultado = num2 * num1;
                        operador.pop();

                    } else if (operador.top().equals("/")) {
                        resultado = num2 / num1;
                        operador.pop();

                    } else if (operador.top().equals("^")) {
                        resultado = Math.pow(num2, num1);
                        operador.pop();

                    } else if (operador.top().equals("-")) {
                        resultado = num2 - num1;
                        operador.pop();

                    } else if (operador.top().equals("+")) {
                        resultado = num2 + num1;
                        operador.pop();

                    }

                    numero.push(resultado);

                }
                operador.pop();
                if(potencia == true){
                    num1 = numero.top();
                    numero.pop();

                    num2 = numero.top();
                    numero.pop();
                    resultado = Math.pow(num2, num1);
                    numero.push(resultado);
                    potencia = false;
                }
            } else {
                String valor="";
                for (;i < expressao.length();i++) {
                    if (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.') {
                        valor += Character.toString(expressao.charAt(i));
                    } else {
                        i--;
                        break;
                    }
                }
                numero.push(Double.parseDouble(valor));
            }
        }

        while(operador.isEmpty() == false) {
            num1 = numero.top();
            numero.pop();

            num2 = numero.top();
            numero.pop();

            if (operador.top().equals("*")) {
                resultado = num2 * num1;
                operador.pop();

            } else if (operador.top().equals("/")) {
                resultado = num2 / num1;
                operador.pop();

            } else if (operador.top().equals("^")) {
                resultado = Math.pow(num2, num1);
                operador.pop();

            } else if (operador.top().equals("-")) {
                resultado = num2 - num1;
                operador.pop();

            } else if (operador.top().equals("+")) {
                resultado = num2 + num1;
                operador.pop();

            }
            numero.push(resultado);
        }
        System.out.println(numero.top());
    }
}