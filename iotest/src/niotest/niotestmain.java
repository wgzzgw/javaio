package niotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;


public class niotestmain {

	public static void main(String[] args) throws IOException {
		//����һ���ļ���
		FileInputStream fin = new FileInputStream( "test.txt" );
		//��ȡͨ��
		FileChannel fc = fin.getChannel();  
		//����������
		ByteBuffer buffer = ByteBuffer.allocate( 1024 );
		//�����ݴ�ͨ����ȡ��buffer
		fc.read( buffer );
		buffer.flip();
		Charset charset=Charset.forName("GBK");
		System.out.println(charset.decode(buffer).toString());
		buffer.clear();
		
		//�����ļ�д����
		FileOutputStream fout = new FileOutputStream( "test.txt",true );
		//��ȡͨ��
		FileChannel fcc = fout.getChannel();
		//д���ļ�ǰ�Ȱ�����д��bufferr
		ByteBuffer bufferr = ByteBuffer.allocate( 1024 );
		byte[] message="dasf��ɽ���".getBytes();
		for (int i=0; i<message.length; ++i) {
		 buffer.put( message[i] );
		}
		buffer.flip();
		//д���ļ�
		fcc.write( buffer );
		copyFileUseNIO("D:/javaio/iotest/a.txt","D:/javaio/iotest/b.txt");
	}
	/**
	 * ��java NIO api�����ļ�
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
	public static void copyFileUseNIO(String src,String dst) throws IOException{
		// ����Դ�ļ���Ŀ���ļ�
		FileInputStream fi = new FileInputStream(new File(src));//�ļ���ȡ��
		FileOutputStream fo = new FileOutputStream(new File(dst));//�ļ�д����
		// ��ô���ͨ��channel
		FileChannel inChannel = fi.getChannel();
		FileChannel outChannel = fo.getChannel();
		// �������buffer
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (true) {
			// �ж��Ƿ�����ļ�
			int eof = inChannel.read(buffer);
			//��û�и��������ʱ������������ɣ���ʱ read() �����᷵�� -1 �����ǿ��Ը�����������ж��Ƿ����
			if (eof == -1) {
				break;
			}
			// ����һ��buffer��position=0��limit=position
			buffer.flip();
			// ��ʼд
			outChannel.write(buffer);
			// д��Ҫ����buffer������position=0,limit=capacity
			buffer.clear();
		}
		inChannel.close();
		outChannel.close();
		fi.close();
		fo.close();
	}     
}