package com.beagle.java.projects.starfucks.collection;

public class StarFucksList<T> {
    private Node head;
    private Node tail;
    private int size = 0;
    private class Node {
        private T data;
        private Node next;
        private Node previous;
        public Node (T input) {
            this.data = input;
            this.previous = null;
            this.next = null;
        }
        public String toString() {
            return String.valueOf(this.data);
        }
    }


    public void addFirst (T input) {
        Node newNode = new Node (input);
        newNode.next = head;
        if (head != null) {
            head.previous = newNode;
        }
        head = newNode;
        size++;
        if (head.next == null) {
            tail = head;
        }

    }

    public void addLast (T input) {
        if (size == 0) {
            addFirst(input);
        } else {
            Node newNode = new Node (input);
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            size ++;
        }
    }

    Node node (int index) {
        if ((size() / 2) > index) {
            Node findNode = head;
            for (int i = 0; i < index; i++) {
                findNode = findNode.next;
            }
            return findNode;
        } else {
            Node findNode = tail;
            for (int i = size()-1; i > index; i--) {
                findNode = findNode.previous;
            }
            return findNode;
        }
    }

    public void add (int index, T input) {
        if (index == 0) {
            addFirst(input);
        } else {
            Node temp1 = node(index-1);
            Node temp2 = temp1.next;
            Node newNode = new Node(input);
            newNode.next = temp2;
            temp1.next = newNode;
            if (temp1 != null) {
                temp2.previous = newNode;
            }
            newNode.previous = temp1;
            size++;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
    }

    public T removeFirst () {
        Node temp = head;
        head = temp.next;
        T returnData = temp.data;
        temp = null;
        if (head != null) {
            head.previous = null;
        }
        size--;
        return returnData;
    }

    public T remove(int index) {
        if (index == 0) {
            return removeFirst();
        }
        Node temp1 = node(index -1);
        Node todoDeleted = temp1.next;
        temp1.next = temp1.next.next;
        if (temp1.next != null) {
            temp1.next.previous = temp1;
        }
        T returnData = todoDeleted.data;
        if (todoDeleted == tail) {
            tail = temp1;
        }
        todoDeleted = null;
        size--;
        return returnData;
    }

    public T removeLast() {
        return remove(size-1);
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        Node temp = node(index);
        if (temp == null) {
            return null;
        } else {
            return temp.data;
        }
    }

    public int indexOf(T data) {
        Node temp = head;
        int index = 0;
        while (!temp.data.equals(data)) {
            temp = temp.next;
            index++;
            if (temp == null) {
                return -1;
            }
        }
        return index;
    }

    public StarFucksList<T> reverse(StarFucksList<T> input) {
        StarFucksList<T> output = new StarFucksList<T>();
        if (input.size() == 0) {
            return input;
        } else {
            for (int i = 0; i < input.size(); i++) {
                output.addFirst(input.get(i));
            }
            return output;
        }
    }
}
