"use strict";(self["webpackChunksupermarket_manager"]=self["webpackChunksupermarket_manager"]||[]).push([[718],{6718:function(t,e,o){o.r(e),o.d(e,{default:function(){return m}});var i=function(){var t=this,e=t._self._c;return e("el-container",[e("el-header",{staticStyle:{height:"80px"}},[e("el-row",[e("el-col",{staticStyle:{"margin-top":"20px"},attrs:{span:12}},[e("i",{staticClass:"iconfont icon-r-building",staticStyle:{color:"white","font-size":"32px"}},[e("b",{staticStyle:{"font-size":"26px"}},[t._v(" 超市管理系统")])])]),e("el-col",{staticStyle:{"text-align":"right","margin-top":"15px",cursor:"pointer"},attrs:{span:12}},[e("el-dropdown",[e("el-avatar",{attrs:{size:50,shape:"square",src:t.BaseApi+t.circleUrl}}),e("b",{staticStyle:{"font-size":"24px",color:"white","margin-top":"-10px"}},[t._v(" "+t._s(t.isAdmin?"管理员 ":"用户 ")+" "+t._s(t.loginName))]),e("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[e("el-dropdown-item",{nativeOn:{click:function(e){return t.informationBtn.apply(null,arguments)}}},[t._v("个人资料完善")]),e("el-dropdown-item",{nativeOn:{click:function(e){return t.empExit.apply(null,arguments)}}},[t._v("退出")]),e("el-dropdown-item",{nativeOn:{click:function(e){t.logoutVisable=!0}}},[t._v("注销")])],1)],1)],1)],1)],1),e("el-dialog",{attrs:{title:"注销账户",visible:t.logoutVisable,width:"70%"},on:{"update:visible":function(e){t.logoutVisable=e}}},[e("el-form",{ref:"logoutform",staticClass:"demo-ruleForm",attrs:{model:t.logoutform,rules:t.rules,"label-width":"100px"}},[e("el-form-item",{attrs:{label:"内容",prop:"content"}},[e("el-input",{attrs:{placeholder:"请填写“本人确定注销”"},model:{value:t.logoutform.content,callback:function(e){t.$set(t.logoutform,"content",e)},expression:"logoutform.content"}})],1),e("el-form-item",[e("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.logoutSubmit("logoutform")}}},[t._v("确 定 ")]),e("el-button",{on:{click:function(e){return t.logoutCel("logoutform")}}},[t._v("取 消 ")])],1)],1)],1),e("el-container",[e("el-aside",{staticStyle:{"overflow-y":"hidden","min-height":"900px"},attrs:{width:"200px"}},[e("el-menu",{attrs:{"background-color":"#000000","text-color":"white",router:!0,"unique-opened":!0,"active-text-color":"#FFDEAD"}},t._l(t.menu_catalogs,(function(o){return e("el-submenu",{key:o.id,attrs:{index:o.id+""}},[e("template",{slot:"title"},[e("i",{class:o.icon,staticStyle:{"font-size":"26px",color:"white"}},[e("b",{staticStyle:{"font-size":"18px"}},[t._v(" "+t._s(o.label))])])]),e("el-menu-item-group",t._l(o.children,(function(o){return e("el-menu-item",{key:o.id,attrs:{index:o.purl}},[e("i",{class:o.icon,staticStyle:{"font-size":"24px"}}),e("b",{staticStyle:{"font-size":"16px"}},[t._v(" "+t._s(o.label))])])})),1)],2)})),1)],1),e("el-main",[e("router-view")],1)],1)],1)},l=[],n=(o(4114),o(2399)),r=o(4464),s=(o(8987),{data(){return{BaseApi:this.$store.state.BaseApi,logoutVisable:!1,loginName:"",isAdmin:"",logoutform:{},circleUrl:(0,n.kD)().headImg,menu_catalogs:[],rules:{content:[{required:!0,message:"内容不能为空",trigger:"blur"}]}}},methods:{init(){this.isAdmin=(0,n.kD)().isAdmin,this.loginName=(0,n.kD)().nickName,(0,r.hW)("/empMenu",{}).then((t=>{t=t.data,200==t.code&&(this.menu_catalogs=t.data)}))},informationBtn(){this.$router.push("/personal/information"),(0,r.lY)("请完善个人的资料")},empExit(){this.$confirm("确定要退出系统?","警示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{(0,r.hW)("/exit",this.pwdForm).then((t=>{t=t.data,200==t.code?((0,r.lY)("成功退出系统..."),(0,n.o7)("employee"),(0,n.o7)("token"),this.$router.push("/")):(0,r.lY)(t.msg,"error")}))})).catch((()=>{this.$message({type:"info",message:"已取消操作"})}))},logoutCel(t){this.$refs[t].resetFields(),this.logoutform={},this.logoutVisable=!1},logoutSubmit(t){this.$refs[t].validate((t=>{t&&(0,r.PP)("/logout",{content:this.logoutform.content}).then((t=>{t=t.data,200==t.code?((0,r.lY)("注销成功"),(0,n.o7)("employee"),(0,n.o7)("token"),this.logoutVisable=!1,this.$router.push("/")):(0,r.lY)(t.msg,"error")}))}))}},mounted(){this.init()}}),a=s,u=o(1656),c=(0,u.A)(a,i,l,!1,null,null,null),m=c.exports}}]);
//# sourceMappingURL=718.9adced8b.js.map