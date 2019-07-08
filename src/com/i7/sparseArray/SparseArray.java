package com.i7.sparseArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SparseArray {

    //创建一个原始的二维数组
    public int[][] creatOriginalArray(int i, int j) {
        return new int[i][j];
    }

    //将二维数组转换为稀疏数组
    public int[][] toSparseArray(int[][] chessArray) {
        //1.遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[0].length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        //初始化稀疏数组
        int[][] sparseArray = new int[sum+1][3];
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = sum;
        //遍历原始数组，将非0的值存入sparseArray
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[0].length; j ++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }
        return sparseArray;
    }

    //格式化打印数组
    public void printArray(int[][] array) {
        for (int[] row : array) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    //将稀疏数组保存到文件array.txt
    public void writeToFile(int[][] array) {
        FileWriter fileWriter = null;
        File file = new File("Z:/英雄时刻/array.txt");
        try {
            fileWriter = new FileWriter(file);
            for (int i = 0; i < array.length; i ++) {
                for (int j = 0; j<array[0].length; j++) {
                    fileWriter.write(array[i][j]+"\t");
                }
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取文件array.txt并保存入二维数组
    public int[][] readToArray(String path) {
        BufferedReader bufferedReader = null;
        int[][] arry = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path)));
            List<int[]> list = new ArrayList<int[]>();
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String[] sArr = s.split("\t");
                int[] iArr = new int[sArr.length];
                for (int i = 0; i < sArr.length; i++) {
                    iArr[i] = Integer.parseInt(sArr[i]);
                }
                list.add(iArr);
            }
//            System.out.println(list);
//            for (int[] a : list) {
//                for (int i : a) {
//                    System.out.print(i + "\t");
//                }
//                System.out.println();
//            }
            //获取二维数组的最大列数
            int maxColum = 0;
            for (int i = 0 ; i < list.size(); i ++) {
                if (maxColum < list.get(i).length) {
                    maxColum = list.get(i).length;
                }
            }
            //初始化二维数组，并将list中的值赋给数组
            arry = new int[list.size()][maxColum];
            for (int i = 0 ; i < list.size(); i ++ ) {
                for (int j = 0; j < maxColum; j++) {
                    arry[i][j] = list.get(i)[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arry;
    }

    public static void main(String[] args) {

        SparseArray sparseArray = new SparseArray();
//        创建一个二维数组并赋值
//        int[][] originalArray = sparseArray.creatOriginalArray(11,11);
//        originalArray[1][2] = 1;
//        originalArray[2][3] = 2;
//        originalArray[3][5] = 1;
//        System.out.println("原始数组：");
//        sparseArray.printArray(originalArray);
//        int[][] array = sparseArray.toSparseArray(originalArray);
//        System.out.println("稀疏数组：");
//        sparseArray.printArray(array);
//        sparseArray.writeToFile(array);
//        sparseArray.printArray(sparseArray.readToArray("Z:/英雄时刻/array.txt"));
    }
}

