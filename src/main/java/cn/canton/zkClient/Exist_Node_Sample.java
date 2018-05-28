package cn.canton.zkClient;

import org.I0Itec.zkclient.ZkClient;

//ZkClient检测节点是否存在
public class Exist_Node_Sample {
    public static void main(String[] args) throws Exception {
    	String path = "/zk-book";
    	ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 2000);
        System.out.println("Node " + path + " exists " + zkClient.exists(path));
    }
}