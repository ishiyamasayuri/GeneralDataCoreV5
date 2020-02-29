package com.ksptooi.udc.entity.node;

import java.util.ArrayList;

import com.ksptooi.udc.parser.NodeParser;
import com.ksptooi.udc.parser.Parser;

public class Node {

	
	private String name = null;
	
	//缓存的附加数据
	private ArrayList<String> content = null;
	
	//缓存的节点
	private ArrayList<Node> nodeList = null;
	
	
	public Node(String name,ArrayList<String> content) {
		
		this.name = name;
		
		this.content = content;
		
		//创建子节点
		String data = "";
		
		for(String s:content) {
			data = data + s;
		}
		
		nodeList = NodeParser.parserNodeList(data);
		
		//过滤文本
		this.content = NodeParser.clearNodeData(this.content);
		
	}
	
	
	/**
	 * 获取指定的key的索引位置
	 * @param key
	 * @return 索引位置,没有找到key时返回-1
	 */
	public int getIndex(String key) {
		
		String currentKey = null;
		
		
		for(int i=0;i<content.size();i++) {
			
			//不包含分隔符则直接跳过
			if( ! content.get(i).contains("=")) {
				continue;
			}
			
			currentKey = Parser.toKey(content.get(i));
			
			if(currentKey.equals(key)){
				return i;
			}
			
		}
		
		return -1;
	}
	
	
	/**
	 * 获取指定key的value
	 * @param key
	 * @return
	 */
	public String get(String key) {
		
		int index = this.getIndex(key);
		
		if(index == -1) {
			return null;
		}
		
		return Parser.toValue(content.get(index));
		
	}
	
	/**
	 * 获取指定int类型的value
	 * @param key
	 * @return 
	 * 当key未找到时返回-1
	 * 当key已存在但无法转换为int时返回-2
	 */
	public int getInt(String key) {
		
		String result = this.get(key);
		
		if(result == null) {
			return -1;
		}
		
		try {
			return Integer.valueOf(result);
		}catch(Exception e) {
			return -2;
		}
		
	}
	
	/**
	 * 获取指定Double类型的value
	 * @param key
	 * @return 
	 * 当key未找到时返回-1
	 * 当key已存在但无法转换为Double时返回-2
	 */
	public double getDouble(String key) {
		
		String result = this.get(key);
		
		if(result == null) {
			return -1;
		}
		
		try {
			return Double.valueOf(result);
		}catch(Exception e) {
			return -2;
		}
		
	}
	
	/**
	 * 获取指定Boolean类型的value
	 * @param key
	 * @return 
	 * 当key未找到时返回false
	 * 当key已存在但无法转换为Boolean时返回false
	 */
	public boolean getBoolean(String key) {
		
		String result = this.get(key);
		
		if(result == null) {
			return false;
		}
		
		try {
			return Boolean.valueOf(result);
		}catch(Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 设置指定key的value
	 * @param key
	 * @param value
	 */
	public void set(String key,String value) {
		
		String newLine = key +"="+ value;
		
		int index = this.getIndex(key);
		
		if(index == -1) {
			return;
		}
		
		content.set(index, newLine);
		
	}
	
	
	
	public String getName() {
		return name;
	}


	public ArrayList<String> getContent() {
		return content;
	}


	public ArrayList<Node> getNodeList() {
		return nodeList;
	}
	
	
}
