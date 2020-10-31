package com.fileUpload;

import org.junit.Test;

import java.io.File;

/**
 * @Auther: zwy
 * @Date: 2020/7/17
 * @Description: maven库中last update文件后缀删除
 * @version:
 */
public class DealMavenRepository {
    public static final String maven_repository_path = "D:\\eclipse\\maven-3.5.4\\repo";

    public static final String m2 = "C:\\Users\\junode\\.m2";

    @Test
    public void dealLastUpdate(){
//        File file = new File(maven_repository_path);
        File file = new File(m2);
        DealMavenRepository.de(file);
    }

    public static void de(File f){
        File[] files = f.listFiles(); // 获取包含file对象对应的子目录或者文件
        for (int i = 0; i < files.length; i++) {
            if(files[i].isFile()){ // 判断是否为文件
                String fileName = files[i].getName();
                if(fileName.substring(fileName.lastIndexOf(".")+1).equals("lastUpdated")){
                    files[i].delete(); // 删除文件
                }
            }else{
                de(files[i]);
            }
        }
    }
}
