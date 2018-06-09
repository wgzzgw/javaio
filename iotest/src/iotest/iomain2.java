package iotest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;

public class iomain2 {
public static void main(String[] args) throws IOException{
	/*randomAccessFileRead();
	randomAccessFileWrite();*/
	final PipedOutputStream output = new PipedOutputStream();//管道输出流
    final PipedInputStream  input  = new PipedInputStream(output);//管道输入流
    Thread thread1 = new Thread( new Runnable() {
        @Override
        public void run() {
            try {
                output.write( "Hello world, pipe!".getBytes());
            } catch (IOException e) {
            }
        }
    });
    Thread thread2 = new Thread( new Runnable() {
        @Override
        public void run() {
            try {
                int data = input.read();
                while( data != -1){
                    System. out.print(( char) data);
                    data = input.read();
                }
            } catch (IOException e) {
            } finally{
               try {
                                 input.close();
                          } catch (IOException e) {
                                 e.printStackTrace();
                          }
            }
        }
    });
    thread1.start();
    thread2.start();
    readByBufferedInputStream();
    writeByBufferedOutputStream();
    readByBufferedReader();
}
public static void randomAccessFileRead() throws IOException{
	   // 创建一个RandomAccessFile对象
    RandomAccessFile file = new RandomAccessFile( "d:/百内题库.txt", "rw");
 // 通过seek方法来移动读写位置的指针
    file.seek(8);
    // 获取当前指针
    long pointerBegin = file.getFilePointer();
    System.out.println("当前文件的开始指针："+pointerBegin);
 // 从当前指针开始读
    byte[] contents = new byte[1024];
    file.read( contents);
    long pointerEnd = file.getFilePointer();
   System. out.println( "pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" + new String(contents));
    file.close(); 
}
public static void randomAccessFileWrite() throws IOException {
    // 创建一个RandomAccessFile对象
   RandomAccessFile file = new RandomAccessFile( "d:/io.txt", "rw");
    // 通过seek方法来移动读写位置的指针
    file.seek(10);
    // 获取当前指针
    long pointerBegin = file.getFilePointer();
    // 从当前指针位置开始写
    file.write( "HELLO WORD".getBytes());
    long pointerEnd = file.getFilePointer();
   System. out.println( "pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" );
    file.close();
}
public static void readByBufferedInputStream() throws IOException {
    File file = new File( "d:/io.txt");
     byte[] byteArray = new byte[( int) file.length()];
     //可以在构造参数中传入buffer大小
    InputStream is = new BufferedInputStream( new FileInputStream(file),2*1024);
     int size = is.read( byteArray);
    System. out.println( "大小:" + size + ";内容:" + new String(byteArray)+"文件大小"+file.length());
     is.close();
}
public static void writeByBufferedOutputStream() throws IOException {
    File file = new File( "d:/io.txt");
     byte[] byteArray = "dsaf".getBytes();
     //可以在构造参数中传入buffer大小
    OutputStream is = new BufferedOutputStream( new FileOutputStream(file),2*1024);
     is.write(byteArray);
     is.close();
}
public static void readByBufferedReader() throws IOException {
    File file = new File( "d:/test3.txt");
     // 在字符流基础上用buffer流包装，也可以指定buffer的大小
    Reader reader = new BufferedReader( new FileReader(file),2*1024);
     char[] byteArray = new char[( int) file.length()];
     int size = reader.read( byteArray);
    System. out.println( "大小:" + size + ";内容:" + new String(byteArray));
     reader.close();
}
}
