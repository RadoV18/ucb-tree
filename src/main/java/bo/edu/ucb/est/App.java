package bo.edu.ucb.est;

import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {
        Tree<Integer> tree = new Tree<Integer>();
        tree.add(12);
        tree.add(15);
        tree.add(14);
        tree.add(18);
        Tree.printInOrder(tree.getRoot());
        System.out.println("FIN");

        // Stack de Strings
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Alfa");
        stringStack.push("Beta");
        stringStack.push("Bravo");
        stringStack.push("Gama");

        System.out.println("Contenido de la pila: " + stringStack);
        System.out.println("Primer pop: " + stringStack.pop());
        System.out.println("Segundo pop: " + stringStack.pop());
        System.out.println("Primer peek: " + stringStack.peek());
        System.out.println("Primer peek: " + stringStack.peek());

        System.out.println("Print in order non recursive");
        Tree.printInOrderNonRecursive(tree.getRoot());

        System.out.println("Remove");
        tree.remove(tree.getRoot(), 1000);
        System.out.println("Print");
        Tree.printInOrder(tree.getRoot());

    }
}
