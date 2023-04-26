package ListaEnlazada;

public class Lista<T> {

    private Nodo<T> primero;
    private int tamaño;

    private static class Nodo<T> {

        private T data;
        private Nodo<T> sig;

        public Nodo(T data) {
            this.data = data;
            this.sig = null;
        }
    }

    public Lista() {
        this.primero = null;
        this.tamaño = 0;
    }

    public void añadir(T data) {
        Nodo<T> nodoNuevo = new Nodo<>(data);

        if (this.primero == null) {
            this.primero = nodoNuevo;
        } else {
            Nodo<T> currentNode = this.primero;

            while (currentNode.sig != null) {
                currentNode = currentNode.sig;
            }

            currentNode.sig = nodoNuevo;
        }

        this.tamaño++;
    }

    public T obtener(int index) {
        if (index < 0 || index >= this.tamaño) {
            System.out.println("Fuera de rango");
        }

        Nodo<T> nodoActual = this.primero;

        for (int i = 0; i < index; i++) {
            nodoActual = nodoActual.sig;
        }

        return nodoActual.data;
    }

    public boolean eliminar(T data) {
        if (this.primero == null) {
            return false;
        }

        if (this.primero.data.equals(data)) {
            this.primero = this.primero.sig;
            this.tamaño--;
            return true;
        }

        Nodo<T> currentNode = this.primero;

        while (currentNode.sig != null && !currentNode.sig.data.equals(data)) {
            currentNode = currentNode.sig;
        }

        if (currentNode.sig != null) {
            currentNode.sig = currentNode.sig.sig;
            this.tamaño--;
            return true;
        }

        return false;
    }

    public int tamaño() {
        return this.tamaño;
    }


}
