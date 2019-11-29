package com.beagle.java.projects.starfucks;

public class StarFucksList<T> {
    private Node head;
    private Node tail;
    private int size = 0;
    private class Node {
        private T data;
        private Node next;
        public Node (T input) {
            this.data = input;
            this.next = null;
        }
        public String toString() {
            return String.valueOf(this.data);
        }
    }


    public void addFirst (T input) {
        Node newNode = new Node (input);
        newNode.next = head;
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
            tail = newNode;
            size ++;
        }
    }

    Node node (int index) {
        Node findNode = head;
        for (int i = 0; i < index; i++) {
            findNode = findNode.next;
        }
        return findNode;
    }

    public void add (int index, T input) {
        if (index == 0) {
            addFirst(input);
        } else {
            Node temp1 = node(index-1);
            Node temp2 = temp1.next;
            Node newNode = new Node(input);
            temp1.next = newNode;
            newNode.next = temp2;
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
        T returnData = todoDeleted.data;
        if (todoDeleted == tail ) {
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
        return temp.data;
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



    public ListIterator listIterator() {
        return new ListIterator();
    }


    public class ListIterator {
        private Node lastReturned;
        private Node next;
        private int nextIndex;


        ListIterator() {
            next = head;
            nextIndex = 0;
        }

        public T next() {
            lastReturned = next;
            next = next.next;
            nextIndex ++;
            return lastReturned.data;
        }

        public boolean hasNext() {
            return nextIndex < size();
        }

        public void add(T input) {
            Node newNode = new Node(input);
            if (lastReturned == null) {
                head = newNode;
                newNode.next = next;
            } else {
                lastReturned.next = newNode;
                newNode.next = next;
            }
            lastReturned = newNode;
            nextIndex ++;
            size ++;
        }

        public void remove() {
            if (nextIndex == 0) {
                throw new IllegalStateException();
            }
            StarFucksList.this.remove(nextIndex - 1);
            nextIndex--;
        }


    }
}
