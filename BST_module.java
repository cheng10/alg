//Copyright Cheng Chen 2016
//reference:
//http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/

import java.io.*;

class Node{
    int data;
    Node left;
    Node right;
    public Node(int data){
        this.data = data;
        left = null;
        right = null;
    }
}

class Interval{
    int lower;
    int upper;
    Interval(int lower, int upper){
        this.lower = lower;
        this.upper = upper;
    }
}

class BST {
    public static Node root;

    public BST() {
        this.root = null;
    }

    public boolean find(int num) {
        Node current = root;
        while (current != null) {
            if (current.data == num) {
                return true;
            } else if (current.data >= num) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public int sum(Node root, int lower, int upper) {
        Node current = root;
        int sum = 0;
        while (current != null) {
            if (current.data >= lower & current.data <= upper) {
                sum += current.data;
                return sum + sum(current.left, lower, upper) + sum(current.right, lower, upper);
            } else if (current.data < lower) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return sum;
    }

    public boolean delete(int num) {
        return true;
        //to be inplemented
    }

    public void insert(int num) {
        Node newNode = new Node(num);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (num <= current.data) {
                //always store duplicated value in the left node
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }

        }
    }

    public void display(Node root) {
        if (root != null) {
            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);
        }
    }
}

public class BST_module{
    //http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }

    public static int[] readData(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int lineCount = countLines(filename);
       // System.out.println("linecount:"+lineCount);
        int dataH[] = new int[lineCount];
        int linenumber=0;
        while ((line = br.readLine()) != null) {
            // process the line.
                String trimed = line.trim();
                int data = Integer.parseInt(trimed);
                dataH[linenumber] = data;
                linenumber++;
                //System.out.println("line:"+linenumber);
        }
        return dataH;
    }

    public static Interval[] readRange(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int lineCount = countLines(filename);
        //System.out.println("linecount:"+lineCount);
        Interval rangeH[] = new Interval[lineCount];
        int linenumber = 0;
        while ((line = br.readLine()) != null)  {
            // process the line.
                String trimed = line.trim();
                //http://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
                String[] splited = trimed.split("\\s+");
                int lower = Integer.parseInt(splited[0]);
                int upper = Integer.parseInt(splited[1]);
                rangeH[linenumber] = new Interval(lower, upper);
                linenumber++;
                //System.out.println("line:"+linenumber);

        }
        return rangeH;
    }

    public static void main(String arg[]) throws IOException {
        String datafile = "dataH.txt";
        String rangefile = "rangeH.txt";
        BST bst = new BST();
        //Interval[] rangeX = {new Interval(-3, 4), new Interval(0, 5)};
        //int[] dataX = {3, -2, 2, 5, 2, -6, 1};
        int[] dataX = readData(datafile);
        Interval[] rangeX = readRange(rangefile);

        System.out.println("dataX length:"+dataX.length);
        System.out.println("rangeX length:"+rangeX.length);

        for (int i=0; i<dataX.length; i++){
            bst.insert(dataX[i]);
        }

        System.out.println("Binary Search Tree:");
        bst.display(bst.root);
        System.out.println();
        System.out.println("RangeX:");
        for (int i=0; i<rangeX.length; i++){
            //System.out.println(i+": "+rangeX[i].lower+", "+rangeX[i].upper);
        }
        System.out.println("Sum:");

        long startTime = System.currentTimeMillis();

        for (int i=0; i<rangeX.length; i++){
            int sum = bst.sum(bst.root,rangeX[i].lower,rangeX[i].upper);
            //System.out.println(i+": "+sum);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime -startTime;

        System.out.println("Total Time:"+totalTime);

    }
}


