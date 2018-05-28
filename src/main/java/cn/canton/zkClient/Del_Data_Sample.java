package cn.canton.zkClient;

import org.I0Itec.zkclient.ZkClient;

//ZkClient删除节点数据
public class Del_Data_Sample {
	public static void main(String[] args) throws Exception {
		String path = "/zk-book";
    	ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 2000);
        zkClient.createPersistent(path, "");
        zkClient.createPersistent(path+"/c1", "");
        zkClient.deleteRecursive(path);
    }
}