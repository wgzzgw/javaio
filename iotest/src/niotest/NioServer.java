package niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class NioServer {
	private final int port = 8787;//�˿ں�
	private final int BLOCK_SIZE = 4096;//���С
	/*
	 * Selector��һ������������ע�ᵽ�ܶ��Channel�ϣ���������Channel�Ϸ������¼���
	 * �����ܹ������¼��������Channel��д��������ͨ��һ���̹߳�����Channel���Ϳ��Դ���������������ˡ�
	 */
	private Selector selector;
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK_SIZE);//���ջ�����
    private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK_SIZE); //���ͻ�����
    //���캯��  
    public NioServer() throws IOException {  
        //�򿪷������׽���ͨ��   
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();  
        //����������Ϊ������ģʽ�����첽IOģʽ  
        serverSocketChannel.configureBlocking(false);  
        //��ȡ��ͨ�������� ServerSocket����  
        ServerSocket serverSocket = serverSocketChannel.socket();  
        //�󶨶˿�  
        serverSocket.bind(new InetSocketAddress(port));  
        //��һ��ѡ����  
        selector = Selector.open();  
        //ע�ᵽselector�ϣ��ȴ�����  
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  
        System.out.println("Server:init successfuly."+System.currentTimeMillis());  
    }  
    /** 
     * �����˿� 
     */  
    private void linstenr() throws Exception{  
          
        while (true) {  
            //ѡ��һ���  ������ѯselector 
            selector.select();  
            //���ػ�ȡѡ��ļ���  
            Set<SelectionKey> selectionKeys = selector.selectedKeys();  
            if(selectionKeys.isEmpty()){  
                continue;  
            }  
            //������ѭ����������ļ���  
            Iterator<SelectionKey> iterator =  selectionKeys.iterator();  
            while (iterator.hasNext()) {  
                SelectionKey selectionKey = (SelectionKey) iterator.next();  
                iterator.remove();  
                handlerKey(selectionKey);  
            }  
              
            Thread.sleep(4000);  
        }  
          
    }  
    /** 
     * �����Ӧ��  SelectionKey 
     * @param selectionKey 
     */  
    private void handlerKey(SelectionKey selectionKey) throws IOException{  
          
        ServerSocketChannel server;  
        SocketChannel client;  //�ͻ���Channel
          
        // ���Դ˼���ͨ���Ƿ���׼���ý����µ��׽�������  
        if(selectionKey.isAcceptable()){  
            //�˼���Ӧ�Ĺ���ͨ��  
            server = (ServerSocketChannel)selectionKey.channel();  
            //���ܵ���ͨ���׽��ֵ�����  
            client = server.accept();  
            //����Ϊ������  
            client.configureBlocking(false);  
            //ע�ᵽselector �ȴ�����  
            client.register(selector, SelectionKey.OP_READ);  
            System.out.println("server accept"+System.currentTimeMillis());
        }  
          
        else if (selectionKey.isReadable()) {  
            client = (SocketChannel)selectionKey.channel();  
            //����������գ������ȡ  
            receiveBuffer.clear();  
            //���ͻ��˷����������ݶ�ȡ�� buffer��  
            int count = client.read(receiveBuffer);  
            if(count >0){  
                String receiveMessage = new String(receiveBuffer.array(),0,count);  
                System.out.println("Server:���ܿͻ��˵�����:" + receiveMessage+System.currentTimeMillis());  
                client.register(selector, SelectionKey.OP_WRITE);  
            }  
        }  
          
        else if (selectionKey.isWritable()) {  
            //������Ϣbuffer ���  
            sendBuffer.clear();  
            //���ظü���Ӧ��ͨ��  
            client = (SocketChannel)selectionKey.channel();  
            String sendMessage = "Send form Server...Hello... "+new Random().nextInt(100)+" .";  
            //�򻺳�����д������  
            sendBuffer.put(sendMessage.getBytes());  
            //put�����ݣ���־λ���ı�  
            sendBuffer.flip();  
            //���������ͨ��  
            client.write(sendBuffer);  
            System.out.println("Server:��������ͻ��˷�������:" + sendMessage+System.currentTimeMillis());  
            client.register(selector, SelectionKey.OP_READ);  
        }     
    }  

	public static void main(String[] args) {

		try {

			NioServer nioServer = new NioServer();
			nioServer.linstenr();

		} catch (Exception e) {

			e.printStackTrace();
		}

	} 
}
