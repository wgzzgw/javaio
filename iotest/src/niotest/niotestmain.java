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
		//创建一个文件流
		FileInputStream fin = new FileInputStream( "test.txt" );
		//获取通道
		FileChannel fc = fin.getChannel();  
		//创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate( 1024 );
		//将数据从通道读取到buffer
		fc.read( buffer );
		buffer.flip();
		Charset charset=Charset.forName("GBK");
		System.out.println(charset.decode(buffer).toString());
		buffer.clear();
		
		//创建文件写入流
		FileOutputStream fout = new FileOutputStream( "test.txt",true );
		//获取通道
		FileChannel fcc = fout.getChannel();
		//写入文件前先把数据写入bufferr
		ByteBuffer bufferr = ByteBuffer.allocate( 1024 );
		byte[] message="dasf南山大佛".getBytes();
		for (int i=0; i<message.length; ++i) {
		 buffer.put( message[i] );
		}
		buffer.flip();
		//写入文件
		fcc.write( buffer );
		copyFileUseNIO("D:/javaio/iotest/a.txt","D:/javaio/iotest/b.txt");
	}
	/**
	 * 用java NIO api拷贝文件
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
	public static void copyFileUseNIO(String src,String dst) throws IOException{
		// 声明源文件和目标文件
		FileInputStream fi = new FileInputStream(new File(src));//文件读取流
		FileOutputStream fo = new FileOutputStream(new File(dst));//文件写入流
		// 获得传输通道channel
		FileChannel inChannel = fi.getChannel();
		FileChannel outChannel = fo.getChannel();
		// 获得容器buffer
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (true) {
			// 判断是否读完文件
			int eof = inChannel.read(buffer);
			//当没有更多的数据时，拷贝就算完成，此时 read() 方法会返回 -1 ，我们可以根据这个方法判断是否读完
			if (eof == -1) {
				break;
			}
			// 重设一下buffer的position=0，limit=position
			buffer.flip();
			// 开始写
			outChannel.write(buffer);
			// 写完要重置buffer，重设position=0,limit=capacity
			buffer.clear();
		}
		inChannel.close();
		outChannel.close();
		fi.close();
		fo.close();
	}     
}
