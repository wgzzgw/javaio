package iotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

public class iomain {
	public static void main(String[] args) throws IOException {
		/*writeCharToFile();*/
		/*readCharFromFile();*/
		/*convertByteToChar();*/
		 //����ļ��Ƿ����
        File file = new File( "d:/test.txt");
         boolean fileExists = file.exists();
        System. out.println( fileExists);
        //�����ļ�Ŀ¼,����Ŀ¼�������򷵻�false
        File file2 = new File( "d:/fatherDir/subDir");
         boolean dirCreated = file2.mkdir();
        System. out.println( dirCreated);
        //�����ļ�Ŀ¼,����Ŀ¼��������ͬ��Ŀ¼һ�𴴽�
        File file3 = new File( "d:/fatherDir/subDir2");
         boolean dirCreated2 = file3.mkdirs();
        System. out.println( dirCreated2);
        File file4= new File( "d:/test2.txt");
        //�жϳ���
        long length = file4.length();
        //�������ļ�
        boolean isRenamed = file4.renameTo( new File("d:/test2.txt"));
        System.out.println(isRenamed);
        //ɾ���ļ�
        boolean isDeleted = file4.delete();
        System.out.println(isDeleted);
        File file5= new File( "d:/Data");
        //�Ƿ���Ŀ¼
        boolean isDirectory = file5.isDirectory();
        System.out.println("Ŀ¼"+isDirectory);
        //�г��ļ���
       String[] fileNames = file5.list();
       for(int i=0;i<fileNames.length;i++){
    	   System.out.println(fileNames[i]);
       }
        //�г�Ŀ¼
       File[]   files = file4.listFiles();
       if(files!=null)
       for(int i=0;i<fileNames.length;i++){
    	   System.out.println(files[i]);
       }
	}
	public static void writeCharToFile() throws IOException{
        String hello= new String( "hello word!");
        File file= new File( "d:/test.txt");
         //��Ϊ�����ַ�������ý�飬���Զ�Ӧ����Writer������Ϊý��������ļ��������õ�������FileWriter
        Writer os= new FileWriter( file);
         os.write( hello);
         os.close();
  }
	public static void readCharFromFile() throws IOException{
        File file= new File( "d:/test.txt");
         //��Ϊ�����ַ�������ý�飬���Զ�Ӧ����Reader
         //����Ϊý��������ļ��������õ�������FileReader
        Reader reader= new FileReader( file);
         char [] byteArray= new char[( int) file.length()];
         int size= reader.read( byteArray);
        System. out.println( "��С:"+size +";����:" +new String(byteArray));
         reader.close();
  }
	public static void convertByteToChar() throws IOException{
        File file= new File( "d:/test.txt");
         //���һ���ֽ���
        InputStream is= new FileInputStream( file);
         //���ֽ���ת��Ϊ�ַ�������ʵ���ǰ��ַ������ֽ�����ϵĽ����
        Reader reader= new InputStreamReader( is);
         char [] byteArray= new char[( int) file.length()];
         int size= reader.read( byteArray);
        System. out.println( "��С:"+size +";����:" +new String(byteArray));
         is.close();
         reader.close();
  }
}