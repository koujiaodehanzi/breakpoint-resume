package com.wyk;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App {

    private static int position = -1;

    public static void main(String[] args) {
        // 源文件与目标文件
        File sourceFile = new File("D:/", "test.txt");
        File targetFile = new File("E:/", "test.txt");
        breakpointResume(sourceFile, targetFile, 5);
    }

    /**
     * 全量传输
     * @param source
     * @param target
     */
    private static void fullTransport(File source,File target){

        FileInputStream fis = null;
        FileOutputStream fos = null;

        byte[] buf = new byte[1];

        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            while (fis.read(buf) != -1) {
                fos.write(buf);
            }
        }catch (FileNotFoundException e) {
            System.out.println("指定文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭输入输出流
                if (fis != null){
                    fis.close();
                }
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 断点续传
     * @param source
     * @param target
     * @param position
     */
    private static void breakpointResume(File source,File target, int position) {
        try {
            RandomAccessFile readFile = new RandomAccessFile(source, "rw");
            RandomAccessFile writeFile = new RandomAccessFile(target, "rw");
            // 文件指针偏移量
            readFile.seek(position);
            writeFile.seek(position);

            byte[] buf = new byte[1];
            while (readFile.read(buf) != -1) {
                writeFile.write(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
