package com.file.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * xml 파서
 * xml에서 DAO 추출
 * @author user
 *
 */
public class DOMParser {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		// XML 문서 파싱
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		
		//xml 파일경로 변경 필수
		Document document = documentBuilder.parse("");//mybatis.xml경로
		
		// root 구하기
		Element root = document.getDocumentElement();
		int  cnt = 0;
		// root의 속성
		//System.out.println("class name: " + root.getAttribute("name"));
		
		NodeList childeren = root.getChildNodes(); // 자식 노드 목록 get
		for(int i = 0; i < childeren.getLength(); i++){
			Node node = childeren.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){ // 해당 노드의 종류 판정(Element일 때)
				Element ele = (Element)node;
				String nodeName = ele.getNodeName();
				//System.out.println("node name: " + nodeName);
				//System.out.println("node attribute: " + ele.getAttribute("id"));
				//System.out.println("param attribute: " + ele.getAttribute("parameterType"));
				//System.out.println("return attribute: " + ele.getAttribute("resultType"));
				
				String resultType ="";
				StringBuilder returnSb = new StringBuilder();
				returnSb.append("return this.mybatisSupport.");
				if("insert".equals(nodeName) || "update".equals(nodeName) || "delete".equals(nodeName)) {
					resultType = "int";
					returnSb.append(nodeName);
					
				}else if("select".equals(nodeName)) {
				
					resultType = ele.getAttribute("resultType");
					returnSb.append("selectList");
					
					if(!"".equals(resultType) && null != resultType) {
						
						if(resultType.indexOf(".") >= 0) {
							String[] resultArr = resultType.split("\\.");					
							resultType = "List<"+resultArr[(resultArr.length-1)]+">";
						}else {
							if("MAP".equals(resultType.toUpperCase())){
								resultType = "List<Map<String,Object>>";
							}else if("HASHMAP".equals(resultType.toUpperCase())){
								resultType = "List<HashMap<String,Object>>";
							}
						}
					}
				}else {
					continue;
				}
				cnt++;
				returnSb.append("(this.getClass().getName() + ");
				returnSb.append("\"."+ele.getAttribute("id")+"\", param);");
				
				String parameterType = ele.getAttribute("parameterType");
				
				if(!"".equals(parameterType) && null != parameterType) {
					
					if(parameterType.indexOf(".") >= 0) {
						String[] paramArr = parameterType.split("\\.");					
						parameterType = paramArr[(paramArr.length-1)] + " param";
					}else {
						
						if(!"".equals(parameterType) && null != parameterType ) {
							if("HASHMAP".equals(parameterType.toUpperCase())){
								parameterType = "HashMap<String,Object> param";
							}else if("MAP".equals(parameterType.toUpperCase())){
								parameterType = "Map<String,Object> param";
							}

						}
					}
				}
				StringBuilder sb = new StringBuilder();
				sb.append("public ");
				sb.append(resultType);
				sb.append(" "+ele.getAttribute("id"));
				sb.append("("+parameterType +"){\n");
				
				sb.append("  "+returnSb+"\n");
				sb.append("}");
				
				System.out.println(sb);
				System.out.println();
				//String fileName = File.
				
				/*if(nodeName.equals("teacher")){
					System.out.println("node attribute: " + ele.getAttribute("name"));
				}
				else if(nodeName.equals("student")){
					// 이름이 student인 노드는 자식노드가 더 존재함
					NodeList childeren2 = ele.getChildNodes();
					for(int a = 0; a < childeren2.getLength(); a++){
						Node node2 = childeren2.item(a);
						if(node2.getNodeType() == Node.ELEMENT_NODE){
							Element ele2 = (Element)node2;
							String nodeName2 = ele2.getNodeName();
							System.out.println("node name2: " + nodeName2);
							System.out.println("node attribute2: " + ele2.getAttribute("num"));
						}
					}
				}*/
			}
		}
		
		System.out.println("갯수 " + cnt);
	}
}
