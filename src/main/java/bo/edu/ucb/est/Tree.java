package bo.edu.ucb.est;

import java.util.Stack;

public class Tree<D extends Comparable<D>> {
    private Node<D> root;

    public Tree() {
    }

    public Node<D> getRoot() {
        return root;
    }

    public void setRoot(Node<D> root) {
        this.root = root;
    }

    public void add(D data) {
        Node<D> newNode = new Node<>(data);
        if ( root == null) { // arbol vacio
            root = newNode;
        } else {
            Node<D> current = root;
            while(current != null ) {
                if (current.getData().compareTo(data) > 0) {
                    if (current.getLeft() == null) { // Tengo el espacio vacio para agregar el nodo
                        current.setLeft(newNode);
                        break; // Terminamos el recorrido
                    } else { // continuo bajando por la rama izquierda
                        current = current.getLeft();
                    }
                } else if (current.getData().compareTo(data) < 0) {
                    if (current.getRight() == null) { // Tengo el espacio vacio para agregar el nodo
                        current.setRight(newNode);
                        break; // Terminamos el recorrido
                    } else { // continuo bajando por la rama derecha
                        current = current.getRight();
                    }
                } else { // igual a cero
                    // Entonces el elemento ya existe.
                    throw new RuntimeException("No se puede agregar elementos duplicados a un ABB");
                }

            }
        }
    }

    public void addRecursive(D data) {
        Node<D> newNode = new Node<>(data);
        if(root == null) {
            root = newNode;
        } else {
            addDataToNode(root, newNode);
        }

    }

    private void addDataToNode(Node<D> current, Node<D> newNode) {
        if(current != null) {
            if (current.getData().compareTo(newNode.getData()) > 0) {
                if(current.getLeft() == null) {
                    current.setLeft(newNode);
                } else {
                    addDataToNode(current.getLeft(), newNode);
                }
            } else if(current.getData().compareTo(newNode.getData()) < 0) {
                if(current.getRight() == null) {
                    current.setRight(newNode);
                } else {
                    addDataToNode(current.getRight(), newNode);
                }
            } else {
                throw new RuntimeException("No se puede agregar elementos duplicados a un ABB");
            }
        }

    }

    public static void printInOrder(Node<?> root) {
//        Hasta que todos los nodos hayan sido atravesados −
//        Paso 1 − Recorre recursivamente el subarbol izquierdo.
//        Paso 2 − Visitamos el nodo raíz.
//        Paso 3 − Recorre recursivamente el subarbol derecho.
        if(root == null) {
            return;
        }
        printInOrder(root.getLeft());
        System.out.println(root.getData());
        printInOrder(root.getRight());
    }

    public static void printInOrderNonRecursive(Node<?> root) {
        Stack<Node> nodeStack = new Stack<>(); // stack vacio
        Node current = root; // inicializar current como root
        boolean flag = true; // flag para saber si se termino de recorrer
        do {
            while(current != null) { // hasta que el actual sea NULL
                nodeStack.push(current); // insertar el nodo actual
                current = current.getLeft(); //asignar current 
            }
            if(!nodeStack.isEmpty()) { // si current es null y el stack no esta vacio
                Node popNode = nodeStack.pop(); // pop del stack
                System.out.println(popNode.getData()); // imprimir contenido
                current = popNode.getRight(); // asignar el nodo derecho y continuar
            } else {
                flag = false; // se termino de recorrer el arbol
            }
        } while(flag);
    }

    public void remove(Node<D> current, D data) {
        Node<D> newNode = new Node(data);
        boolean encontrado = false;
        boolean nodoIzq = false;
        if(current.getLeft() != null && current.getLeft().getData().equals(newNode.getData())) {
            encontrado = true;
            nodoIzq = true;
            /*// verificar 3 posibles casos
            Node<D> removeNode = current.getLeft();
            if(removeNode.getLeft() == null && removeNode.getRight() == null) { // nodo leaf
                current.setLeft(null); // eliminar el nodo
            } else {
                if(removeNode.getLeft() == null) { // nodo hijo a la derecha
                    current.setLeft(removeNode.getRight()); // saltar el nodo a eliminar
                } else if(removeNode.getRight() == null) { // nodo hijo a la izquierda
                    current.setLeft(removeNode.getLeft());
                } else { // 2 nodos hijos
                    Node<D> minNode = findMinNode(removeNode.getRight()); // encontrar el nodo minimo del subarbol derecho
                    remove(root, minNode.getData()); // eliminar el nodo hoja
                    removeNode.setData(minNode.getData()); // cambiar el valor del nodo a eliminar
                }
            }*/
        } else if(current.getRight() != null && current.getRight().getData().equals(newNode.getData())) {
            encontrado = true;
            nodoIzq = false;
            /*// verificar 3 posibles casos
            Node<D> removeNode = current.getRight();
            if(removeNode.getLeft() == null && removeNode.getRight() == null) { // nodo leaf
                current.setRight(null); // eliminar el nodo
            } else {
                if(removeNode.getLeft() == null) { // nodo hijo a la derecha
                    current.setRight(removeNode.getRight()); // saltar el nodo a eliminar
                } else if(removeNode.getRight() == null) { // nodo hijo a la izquierda
                    current.setRight(removeNode.getLeft());
                } else { // 2 nodos hijos
                    Node<D> minNode = findMinNode(removeNode.getRight()); // nodo minimo del subarbol derecho
                    remove(root, minNode.getData()); // eliminar el nodo hoja
                    removeNode.setData(minNode.getData()); // cambiar el valor del nodo a eliminar
                }
            }*/
        }
        if(encontrado) {
            Node<D> removeNode = nodoIzq ? current.getLeft() : current.getRight();
            if(removeNode.getLeft() == null && removeNode.getRight() == null) { // nodo hoja
                if(nodoIzq) {
                    current.setLeft(null); // eliminar nodo
                } else {
                    current.setRight(null);
                }
            } else {
                if(removeNode.getLeft() == null) { // nodo hijo a la derecha
                    if(nodoIzq) {
                        current.setLeft(removeNode.getRight()); // saltar el nodo a eliminar
                    } else {
                        current.setRight(removeNode.getRight()); // saltar el nodo a eliminar
                    }
                } else if(removeNode.getRight() == null) { // nodo hijo a la izquierda
                    if(nodoIzq) {
                        current.setLeft(removeNode.getLeft()); // saltar el nodo a eliminar
                    } else {
                        current.setRight(removeNode.getLeft()); // saltar el nodo a eliminar
                    }
                } else {
                    Node<D> minNode = findMinNode(removeNode.getRight()); // nodo minimo del subarbol derecho
                    remove(root, minNode.getData()); // eliminar el nodo hoja
                    removeNode.setData(minNode.getData()); // cambiar el valor del nodo a eliminar
                }
            }
        } else {
            // recorrer el arbol hasta encontrar el nodo a eliminar
            if(data.compareTo(current.getData()) > 0) { // ir por la derecha
                remove(current.getRight(), data);
            } else if(data.compareTo(current.getData()) < 0) { // ir por la izquierda
                remove(current.getLeft(), data);
            }
        }
    }

    private Node<D> findMinNode(Node<D> current) {
        if(current.getLeft() == null) {
            return current;
        } else {
            return findMinNode(current.getLeft());
        }
    }

    /**
     * Hasta que todos los nodos hayan sido visitados
     * 1. Visitar root node
     * 2. Traverse left subtree
     * 3. Traverse right subtree
     * @param root
     */
    public static void printPreOrder(Node<?> root) {
        if(root == null) {
            return;
        }
        System.out.println(root.getData());
        printPreOrder(root.getLeft());
        printPreOrder(root.getRight());
    }

    /**
     * Hasta que todos los nodos hayan sido visitados
     * 1. Traverse right subtree
     * 2. Traverse left subtree
     * 3. Visitar root node
     * 
     * @param root
     */
    public static void printPostOrder(Node<?> root) {
        if(root == null) {
            return;
        }
        printPreOrder(root.getRight());
        printPreOrder(root.getLeft());
        System.out.println(root.getData());
    }
}
