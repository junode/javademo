package spring.cloud.learn.annotation.document;

///**
// * @description: 练习@Document注解
// * @author: junode
// * @create: 2020-12-21 23:15
// **/

/**
 * 这是用来测试生成java docx 文章的。
 * 关于idea中生成java doc 菜单所在 ：Tools | Generate JavaDoc...
 */
@MyDocument
public class PracDocument {

    /**
     * overrides toString method,and test how is the docx file
     * @return
     */
    @MyDocument(key = "finance",value = "600333")
    public String toString(){
        return "learn annotation with document";
    }
}
