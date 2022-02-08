<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>笔记页面</title>
</head>
<style type="text/css">

</style>
<body>
<h2>Hello World!</h2>
<h3>welcome to the new world</h3>
<div>
    <span>页面按钮跳转</span> <br />
    <button><a href="login.html" target="_self">登录页面</a></button>
    <button><a href="welcome.html" target="_blank">欢迎页面</a> </button>
</div>

<div>
    <form method="post" action="uploadFile01">
        <label>请输入姓名</label>
        <input type="text" name="username" autocomplete="off" placeholder="input your name"/>
        <button disabled="disabled" type="submit" value="提交" >提交</button>
        <button type="reset" name="reset" value="重置">重置</button>
        <button type="button" value="haha" name="demo" hidden="hidden" title="haha" draggable="true">哈哈</button>
        <button type="button" title="haha" draggable="true">hello world</button>
    </form>
</div>
</body>
<script type="text/javascript">

</script>
</html>
