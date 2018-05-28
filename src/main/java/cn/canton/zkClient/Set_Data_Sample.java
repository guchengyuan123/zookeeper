package cn.canton.zkClient;

import org.I0Itec.zkclient.ZkClient;

//ZkClient更新节点数据
public class Set_Data_Sample {

    public static void main(String[] args) throws Exception {
    	String path = "/zk-book";
    	ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 2000);
        zkClient.createEphemeral(path, new Integer(1));
        zkClient.writeData(path, new Integer(1));
    }
}