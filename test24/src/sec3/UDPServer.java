package sec3;
//컴퓨터 한대에서 구현할 수 없는 기능이다.
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		int port =7000;

		try {
			socket = new DatagramSocket(port);
			while(true) {
				byte[] buffer = new byte[512];

				//수신
				packet = new DatagramPacket(buffer, buffer.length);
				System.out.println("ready");
				socket.receive(packet); //실린 수신 데이터 패킷 싣기
				String rData = new String(packet.getData());// 패킷(byte) 데이터=> String으로 형변환
				System.out.println("수신데이터 :"+rData);
				
				InetAddress ip = packet.getAddress();
				port = packet.getPort();
				System.out.println();
				System.out.println("[Client IP Address]:"+ip);	
				System.out.println("[Client port]:"+port);	

				//송신
				packet = new DatagramPacket(packet.getData(), packet.getData().length, ip, port);
				socket.send(packet); 	//송신 데이터 패킷 실어 보내기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
