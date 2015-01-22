/*******************************************************************************
 * This application simulates turn-based games hosted on a server.
 *     Copyright (C) 2014 
 *     Initiators : Fabien Delecroix and Yoann Dufresne
 *     Developpers : Raphael Bauduin and Celia Cacciatore
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package clients.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import clients.Client;

/**
 * A client on the network playing.
 * 
 * @author Cacciatore Celia - Bauduin Raphael
 */
public class NetworkClient implements Client  {
	
	final static int port = 2121;
	private final Socket client ;
	private ServerSocket serverSocket;
	
	public NetworkClient() throws IOException{
		serverSocket = new ServerSocket(port);
		client = serverSocket.accept();
		client.setReuseAddress(true);
		serverSocket.close();		
	}

	@Override
	public String sendMessageWithAnswer(String receivedMessage) {
		String requete = "";
		try{
			OutputStream outputStream = this.client.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeBytes(receivedMessage+"\r\n");
			dataOutputStream.flush();
			InputStream inputStream = this.client.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			requete = bufferedReader.readLine();
		} catch(IOException e){}
		return requete;
	}

	@Override
	public void sendMessage(String message) {
		try{
			OutputStream outputStream = this.client.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeBytes(message+"\r\n");
			dataOutputStream.flush();
		} catch(IOException e){}
	}

	@Override
	public void closeConnection() {
		try {
			client.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}