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
	final PipedOutputStream output = new PipedOutputStream();//�ܵ������
    final PipedInputStream  input  = new PipedInputStream(output);//�ܵ�������
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
	   // ����һ��RandomAccessFile����
    RandomAccessFile file = new RandomAccessFile( "d:/�������.txt", "rw");
 // ͨ��seek�������ƶ���дλ�õ�ָ��
    file.seek(8);
    // ��ȡ��ǰָ��
    long pointerBegin = file.getFilePointer();
    System.out.println("��ǰ�ļ��Ŀ�ʼָ�룺"+pointerBegin);
 // �ӵ�ǰָ�뿪ʼ��
    byte[] contents = new byte[1024];
    file.read( contents);
    long pointerEnd = file.getFilePointer();
   System. out.println( "pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" + new String(contents));
    file.close(); 
}
public static void randomAccessFileWrite() throws IOException {
    // ����һ��RandomAccessFile����
   RandomAccessFile file = new RandomAccessFile( "d:/io.txt", "rw");
    // ͨ��seek�������ƶ���дλ�õ�ָ��
    file.seek(10);
    // ��ȡ��ǰָ��
    long pointerBegin = file.getFilePointer();
    // �ӵ�ǰָ��λ�ÿ�ʼд
    file.write( "HELLO WORD".getBytes());
    long pointerEnd = file.getFilePointer();
   System. out.println( "pointerBegin:" + pointerBegin + "\n" + "pointerEnd:" + pointerEnd + "\n" );
    file.close();
}
public static void readByBufferedInputStream() throws IOException {
    File file = new File( "d:/io.txt");
     byte[] byteArray = new byte[( int) file.length()];
     //�����ڹ�������д���buffer��С
    InputStream is = new BufferedInputStream( new FileInputStream(file),2*1024);
     int size = is.read( byteArray);
    System. out.println( "��С:" + size + ";����:" + new String(byteArray)+"�ļ���С"+file.length());
     is.close();
}
public static void writeByBufferedOutputStream() throws IOException {
    File file = new File( "d:/io.txt");
     byte[] byteArray = "dsaf".getBytes();
     //�����ڹ�������д���buffer��С
    OutputStream is = new BufferedOutputStream( new FileOutputStream(file),2*1024);
     is.write(byteArray);
     is.close();
}
public static void readByBufferedReader() throws IOException {
    File file = new File( "d:/test3.txt");
     // ���ַ�����������buffer����װ��Ҳ����ָ��buffer�Ĵ�С
    Reader reader = new BufferedReader( new FileReader(file),2*1024);
     char[] byteArray = new char[( int) file.length()];
     int size = reader.read( byteArray);
    System. out.println( "��С:" + size + ";����:" + new String(byteArray));
     reader.close();
}
}