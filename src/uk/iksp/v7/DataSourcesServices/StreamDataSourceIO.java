package uk.iksp.v7.DataSourcesServices;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.ksptooi.v3.Entity.GeneralDataEntity;
import uk.isp.v7.main.DataCore;

public class StreamDataSourceIO {
	
	
	//从流获取GDC实体
	public GeneralDataEntity readAsGeneralDataEntity(InputStream is,String charSet){
		
		ArrayList<String> List=new ArrayList<String>();
		
		try{
			
			BufferedReader br=new BufferedReader(new InputStreamReader(is,charSet));
			
			String line=null;
			
			while((line=br.readLine()) != null){
				
				List.add(line);
				
			}
			br.close();
			
			return new GeneralDataEntity(List);
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("读取输入流时出错! at readAsGeneralDataEntity");
		}
		
		return new GeneralDataEntity(List);
	}
	
	//从流获取GDC实体 预设编码 UTF-8
	public GeneralDataEntity readAsGeneralDataEntity(InputStream is){		
		return this.readAsGeneralDataEntity(is, "UTF-8");		
	}
	
	
	
	//从流写入GDC实体
	public void writeGeneralDataEntity(OutputStream os,GeneralDataEntity entity){
		
		//获取打印流
		PrintWriter pw=new PrintWriter(os);
		
		//重置光标
		entity.reset();
		
		try{
			
			String content="";
			
			content = entity.getFirst();
			
			while(entity.next()){
				
				content=content+"\r\n"+entity.get();
				
			}
			
			pw.println(content);				
			pw.flush();
			pw.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("写入输出流时出错! at writeGeneralDataEntity");
		}	
		
	}
	
	
	
	
	//从流写入文件
	public void writeFile(OutputStream os,String str){
		
		
		try{
			
			PrintWriter pw=new PrintWriter(os);	
			
			pw.println(str);		
			
			pw.flush();
			pw.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
			DataCore.LogManager.logError("写入输出流时出错! at writeFile");
		}	
		
		
	}
	
	
	
	
	
	
	

}
