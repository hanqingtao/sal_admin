<%@ page contentType="text/html;charset=UTF-8" %>
<dl class="bottomstep">
    <dt><a href="${ctxFront}" id="footerIndex" class="on">首页</a>|
        <a href="javascript:;" onclick="footerSwitch('footer_agent',this)" id="footer_agent">机构名录</a>|
        <a href="javascript:;" onclick="footerSwitch('footer_project',this)" id="footer_project">投资项目</a>|
        <a href="javascript:;" onclick="footerSwitch('footer_supervise',this)" id="footer_supervise">日常监督</a>|
        <a href="javascript:;" onclick="footerSwitch('footer_blacklist',this)" id="footer_blacklist">黑名单</a>|
        <a href="javascript:;" onclick="footerSwitch('footer_creditRecord',this)" id="footer_creditRecord">信用记录</a></dt>
    <dd>
        版权所有： 中国机电设备招标中心          技术支持： 中国机电设备招标中心<br />
        中央投资项目招标服务与管理平台   京ICP备080000464号-2
        <br/>推荐浏览器版本：IE9及IE9+
    </dd>
</dl>
<div style="width:239px;height:91px;display:none;margin-left:-120px;margin-top:-45px;position:fixed;top:50%;left:50%;background:url(${ctxStatic}/images/save.png);" id="Mode" >
          
   </div>

