package niotest;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClient {
	private static final int BLOCK_SIZE = 4096;  
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK_SIZE);    
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK_SIZE);    
    private static final  InetSocketAddress SERVER_ADDRESS
    = new InetSocketAddress("127.0.0.1",8787);  //����˵�ַ
    public static void main(String[] args) {  
        
        try {  
            //��socketͨ��  
            SocketChannel socketChannel = SocketChannel.open();  
            //����Ϊ������ģʽ �����첽IOģʽ 
            socketChannel.configureBlocking(false);  
            //��ѡ����  
            Selector selector = Selector.open();  
            //��selector ѡ����ע���ͨ��  
            socketChannel.register(selector, SelectionKey.OP_CONNECT);  
            //���� ����� 
            socketChannel.connect(SERVER_ADDRESS);  
              
            SocketChannel client;  
            while (true) {  
                //ѡ��һ���  
                selector.select();  
                //���ش�ѡ��������ѡ�����  
                Set<SelectionKey> selectionKeys = selector.selectedKeys();  
                Iterator<SelectionKey> iterator = selectionKeys.iterator();  
                //������Ӧ�� SelectionKey ����  
                while (iterator.hasNext()) {  
                    SelectionKey selectionKey = (SelectionKey) iterator.next();  
                    //�жϴ˼���ͨ���Ƿ���������׽������Ӳ���  
                    if (selectionKey.isConnectable()) {  
                        System.out.println("Client:  already connected."+System.currentTimeMillis());  
                        client = (SocketChannel)selectionKey.channel();  
                        //�жϸ�ͨ���Ƿ�������ӹ��̡�������ӹ���  
                        if(client.isConnectionPending()){  
                            client.finishConnect();  
                              
                            sendBuffer.clear();  
                            sendBuffer.put("hello nio server".getBytes());  
                            sendBuffer.flip();  
                              
                            client.write(sendBuffer); //������д���ͨ��  
                            client.register(selector, SelectionKey.OP_READ);  
                            System.out.println("Client:  first send."+System.currentTimeMillis()); 
                        }  
                    }  
                    else if(selectionKey.isReadable()){  
                        //��ȡ�ü��ж�Ӧ��ͨ��  
                        client = (SocketChannel)selectionKey.channel();  
                          
                        receiveBuffer.clear();  
                        int count = client.read(receiveBuffer);  
                        if(count > 0){  
                            String receiveMessage = new String(receiveBuffer.array(),0,count);  
                            System.out.println("Client�����յ�����Server����Ϣ��" + receiveMessage+System.currentTimeMillis());  
                            client.register(selector, SelectionKey.OP_WRITE);  
                        }  
                    }  
                    else if(selectionKey.isWritable()){  
                        sendBuffer.clear();    
                        client = (SocketChannel) selectionKey.channel();    
                        String sendText = "hello server,key..";  
                        sendBuffer.put(sendText.getBytes());    
                         //������������־��λ,��Ϊ������put�����ݱ�־���ı�Ҫ����ж�ȡ���ݷ��������,��Ҫ��λ    
                        sendBuffer.flip();    
                        client.write(sendBuffer);    
                        System.out.println("Client:�ͻ�����������˷�������--��"+sendText+System.currentTimeMillis());    
                        client.register(selector, SelectionKey.OP_READ);  
                          
                    }  
                }  
                selectionKeys.clear();  
                Thread.sleep(3000);  
            }      
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
          
    }  
      
}