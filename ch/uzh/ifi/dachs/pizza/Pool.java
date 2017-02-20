package ch.uzh.ifi.dachs.pizza;

import java.util.LinkedList;

/**
 * Created by Patrick on 20.02.17.
 */
public class Pool {

	LinkedList<Server> servers = new LinkedList<>();

	public void addServer(Server server){
		this.servers.add(server);
	}

	public boolean hasServer(Server server){
		return this.servers.contains(server);
	}

	public boolean removeServer(Server server){
		if(this.hasServer(server)){
			this.servers.remove(server);
			return true;
		}
		return false;
	}

	public int worst_case_capacity(){

		LinkedList<Integer> has_servers_in_rows = new LinkedList<>();

		for(Server server: this.servers){
			has_servers_in_rows.add(server.getRow());
		}

		int min = ~(1<<31);
		for(int row : has_servers_in_rows){
			int min_cur = 0;
			for(Server server: this.servers){
				if(server.getRow()!=row){
					min_cur+=server.getCapacity();
				}
			}
			min = Math.min(min_cur,min);
		}
		return min;

	}



}
