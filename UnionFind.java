package com.company;
import java.util.*;

public class UnionFind {
    public int[] id; //array of number ids
    public ArrayList<Set<Integer>> walls = new ArrayList<>();//Arraylist of Walls
    public int count;


    public UnionFind(int x, int y) {
        id = new int[x * y]; //initialize Maze
        for (int i = 0; i < x * y; i++) {
            id[i] = i;
            count++;
        }
        int w = 0;
        while (w < x * y) {
            if (w % y != (y - 1) && w < y * (x - 1)) {
                Integer[] arr = {w, w + 1};
                walls.add(new HashSet<>(Arrays.asList(arr)));
                Integer[] arr1 = {w, w + y};
                walls.add(new HashSet<>(Arrays.asList(arr1)));//generate east and south walls for cells not on southern or eastern edge
            } else if (w % y == (y - 1) && w != (x * y - 1)) {
                Integer[] arr1 = {w, w + y};
                walls.add(new HashSet<>(Arrays.asList(arr1)));//Southern walls for cells on eastern edge
            } else if (w >= y * (x - 1) && w != (x * y - 1)) {
                Integer[] arr = {w, w + 1};
                walls.add(new HashSet<>(Arrays.asList(arr)));//Eastern walls for cell on southern edge
            }
            w++;
        }
    }

    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }//test connection

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {//if joined, terminate function
            return;
        }

        for (int i = 0; i < id.length; i++) {//if not joined, knockdown corresponding wall
            if (id[i] == pID) {
                id[i] = qID;
                Integer[] arr2 = {p, q};
                Integer[] arr3 = {q, p};
                HashSet<Integer> arr = new HashSet<>(Arrays.asList(arr2));
                HashSet<Integer> arr1 = new HashSet<>(Arrays.asList(arr3));
                if (walls.contains(arr)) {
                    walls.remove(arr);
                } else {
                    walls.remove(arr1);
                }
            }
        }
        count--;
    }


    public static void main(String[] args) {
        UnionFind hello = new UnionFind(10, 10);
        while (!(hello.connected(0, 19))) {
            int[] arr = new int[2];
            for(int i=0;i<2;i++){
                arr[i] = -1;
            }
            Random rand = new Random();
            int z = rand.nextInt(hello.walls.size());
            Set<Integer> temp = hello.walls.get(z);
            for (Integer i : temp) {
                if (arr[0] == -1){
                    arr[0] = i;
                }
                else {
                    arr[1] = i;
                }
            }
            hello.union(arr[0],arr[1]);
        }
        for (int i = 0; i < hello.walls.size();i++){
        System.out.println(hello.walls.get(i));//return walls
        }
    }
}
//OUTPUT: /Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=58468:/Applications/IntelliJ IDEA CE.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Users/rohandatta/IdeaProjects/Question3.1/out/production/Question3.1 com.company.UnionFind
//[0, 1]
//[1, 2]
//[3, 13]
//[5, 15]
//[7, 8]
//[17, 7]
//[8, 9]
//[19, 9]
//[21, 11]
//[12, 13]
//[22, 12]
//[13, 14]
//[14, 15]
//[25, 15]
//[16, 26]
//[18, 19]
//[18, 28]
//[20, 21]
//[21, 31]
//[32, 22]
//[33, 23]
//[24, 25]
//[37, 27]
//[39, 29]
//[40, 30]
//[41, 31]
//[33, 34]
//[33, 43]
//[34, 35]
//[35, 36]
//[35, 45]
//[36, 37]
//[37, 38]
//[38, 39]
//[42, 43]
//[43, 44]
//[54, 44]
//[45, 46]
//[55, 45]
//[46, 47]
//[56, 46]
//[57, 47]
//[48, 58]
//[50, 51]
//[50, 60]
//[51, 52]
//[51, 61]
//[53, 63]
//[54, 55]
//[64, 54]
//[55, 56]
//[67, 57]
//[58, 59]
//[68, 58]
//[69, 59]
//[61, 62]
//[62, 63]
//[64, 65]
//[65, 75]
//[66, 67]
//[68, 69]
//[68, 78]
//[70, 71]
//[80, 70]
//[81, 71]
//[82, 72]
//[73, 74]
//[74, 75]
//[84, 74]
//[75, 76]
//[77, 78]
//[81, 82]
//[81, 91]
//[82, 92]
//[83, 84]
//[86, 87]
//[97, 87]
//[88, 89]
//[98, 88]
//[94, 95]
//[96, 95]
//[96, 97]
//[98, 99]
//
//Process finished with exit code 0