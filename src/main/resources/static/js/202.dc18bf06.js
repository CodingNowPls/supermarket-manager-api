"use strict";(self["webpackChunksupermarket_manager"]=self["webpackChunksupermarket_manager"]||[]).push([[202],{1202:function(e,t,o){o.r(t),o.d(t,{default:function(){return d}});var i=function(){var e=this,t=e._self._c;return t("div",{attrs:{id:"role_list"}},[t("el-breadcrumb",{attrs:{separator:"/"}},[t("el-breadcrumb-item",[e._v("首页")]),t("el-breadcrumb-item",[e._v("系统管理")]),t("el-breadcrumb-item",[e._v("角色管理")])],1),t("br"),t("el-row",[t("el-col",{staticStyle:{"text-align":"left","padding-right":"10px"},attrs:{span:8}},[t("el-input",{attrs:{placeholder:"角色名称"},model:{value:e.roleSearchForm.name,callback:function(t){e.$set(e.roleSearchForm,"name",t)},expression:"roleSearchForm.name"}})],1),t("el-col",{attrs:{span:8}},[t("el-select",{attrs:{placeholder:"状态",clearable:""},on:{change:e.$forceUpdate},model:{value:e.roleSearchForm.state,callback:function(t){e.$set(e.roleSearchForm,"state",t)},expression:"roleSearchForm.state"}},[t("el-option",{attrs:{label:"正常",value:"0"}}),t("el-option",{attrs:{label:"停用",value:"-1"}})],1)],1)],1),t("br"),t("el-row",[t("el-col",{staticStyle:{"text-align":"left"},attrs:{span:24}},[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:function(t){return e.subSearchForm("ruleForm")}}},[t("i",{staticClass:"iconfont icon-r-find",staticStyle:{"font-size":"18px"}}),e._v(" 搜索")]),t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"success"},on:{click:function(t){e.newRoleVisable=!0}}},[t("i",{staticClass:"iconfont icon-r-add",staticStyle:{"font-size":"18px"}}),e._v(" 创建角色 ")])],1)],1),t("br"),t("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData}},[t("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),t("el-table-column",{attrs:{prop:"name",label:"名称",width:"180"}}),t("el-table-column",{attrs:{prop:"info",label:"描述"}}),t("el-table-column",{attrs:{prop:"state",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(o){return["0"==o.row.state?t("el-tag",{attrs:{type:"success"}},[e._v("正常")]):t("el-tag",{attrs:{type:"danger"}},[e._v("停用")])]}}])}),t("el-table-column",{attrs:{label:"操作",width:"360",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(o){return[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"success"},on:{click:function(t){return e.editRole(o.row)}}},[t("i",{staticClass:"iconfont icon-r-edit",staticStyle:{"font-size":"18px"}}),e._v(" 修改")]),"0"==o.row.state?t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"danger"},on:{click:function(t){return e.forbidden(o.row.id)}}},[t("i",{staticClass:"iconfont icon-r-no",staticStyle:{"font-size":"18px"}}),e._v(" 停用")]):e._e(),t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"warning"},on:{click:function(t){return e.checkPermissons(o.row.id)}}},[t("i",{staticClass:"iconfont icon-r-setting",staticStyle:{"font-size":"18px"}}),e._v(" 授权")])]}}])})],1),t("el-dialog",{attrs:{title:"角色信息修改",visible:e.dialogVisible,width:"50%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("el-form",{staticClass:"demo-ruleForm",attrs:{"label-width":"100px"},model:{value:e.editRoleForm,callback:function(t){e.editRoleForm=t},expression:"editRoleForm"}},[t("el-form-item",{attrs:{label:"名称："}},[t("el-input",{attrs:{disabled:""},model:{value:e.editRoleForm.name,callback:function(t){e.$set(e.editRoleForm,"name",t)},expression:"editRoleForm.name"}})],1),t("el-form-item",{attrs:{label:"描述："}},[t("el-input",{attrs:{type:"text"},model:{value:e.editRoleForm.info,callback:function(t){e.$set(e.editRoleForm,"info",t)},expression:"editRoleForm.info"}})],1),t("el-form-item",{attrs:{label:"状态："}},[t("el-select",{attrs:{clearable:"",placeholder:"请选择状态"},on:{change:function(t){return e.$forceUpdate()}},model:{value:e.editRoleForm.state,callback:function(t){e.$set(e.editRoleForm,"state",t)},expression:"editRoleForm.state"}},e._l(e.options,(function(e){return t("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),t("el-form-item",[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:e.submitEditForm}},[t("i",{staticClass:"iconfont icon-r-yes",staticStyle:{"font-size":"18px"}}),e._v(" 提交")]),t("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.resetEditForm}},[t("i",{staticClass:"iconfont icon-r-refresh",staticStyle:{"font-size":"18px"}}),e._v(" 重置")])],1)],1)],1),t("el-dialog",{attrs:{title:"创建角色",visible:e.newRoleVisable,width:"50%"},on:{"update:visible":function(t){e.newRoleVisable=t}}},[t("el-form",{ref:"newRoleForm",staticClass:"demo-ruleForm",attrs:{model:e.newRoleForm,rules:e.rules,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"名称：",prop:"name"}},[t("el-input",{model:{value:e.newRoleForm.name,callback:function(t){e.$set(e.newRoleForm,"name",t)},expression:"newRoleForm.name"}})],1),t("el-form-item",{attrs:{label:"描述："}},[t("el-input",{attrs:{type:"text"},model:{value:e.newRoleForm.info,callback:function(t){e.$set(e.newRoleForm,"info",t)},expression:"newRoleForm.info"}})],1),t("el-form-item",[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:function(t){return e.submitNewForm("newRoleForm")}}},[t("i",{staticClass:"iconfont icon-r-add",staticStyle:{"font-size":"18px"}}),e._v(" 创建角色")]),t("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.resetNewForm}},[t("i",{staticClass:"iconfont icon-r-refresh",staticStyle:{"font-size":"18px"}}),e._v("重置")])],1)],1)],1),t("el-dialog",{attrs:{title:"权限分配",visible:e.distributionFulVisable,width:"50%"},on:{"update:visible":function(t){e.distributionFulVisable=t}}},[t("el-form",{staticClass:"demo-ruleForm",attrs:{model:e.distributionFulForm,"label-width":"100px"}},[t("el-form-item",[t("el-input",{attrs:{placeholder:"输入关键字进行过滤"},model:{value:e.filterText,callback:function(t){e.filterText=t},expression:"filterText"}}),t("el-tree",{ref:"tree",staticClass:"filter-tree",attrs:{data:e.fuloptions,"show-checkbox":"","node-key":"value","default-checked-keys":e.default_checked_mid,"default-expand-all":"","filter-node-method":e.filterNode,props:e.props}})],1),t("el-form-item",[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:e.submitDistributionFulForm}},[t("i",{staticClass:"iconfont icon-r-add",staticStyle:{"font-size":"18px"}}),e._v(" 提交")]),t("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.celDistributionFul}},[e._v("取消")])],1)],1)],1)],1)},r=[],n=o(5637),l=o(4464),s={data(){return{tableData:[],editRoleForm:{},editRoleRow:{},dialogVisible:!1,newRoleVisable:!1,distributionFulVisable:!1,newRoleForm:{},roleSearchForm:{},distributionFulForm:{},options:[{label:"正常",value:"0"},{label:"停用",value:"-1"}],props:{multiple:!0},fuloptions:[],default_checked_mid:[],filterText:"",rules:{name:[{required:!0,message:"名称不能为空",trigger:"blur"}]}}},watch:{filterText(e){this.$refs.tree.filter(e)}},methods:{filterNode(e,t){return!e||-1!==t.label.indexOf(e)},init(){(0,n.r9)(this.roleSearchForm).then((e=>{e=e.data,(e.code=200)?this.tableData=e.data:(0,l.lY)(e.msg,"error")}))},forbidden(e){(0,n.wi)(e).then((e=>{e=e.data,200==e.code?(this.roleSearchForm.state="0",this.init(),(0,l.lY)("操作成功")):(0,l.lY)(e.msg,"error")}))},editRole(e){this.editRoleForm={...e},this.editRoleRow={...e},this.dialogVisible=!0},subSearchForm(){this.init()},submitEditForm(){this.editRoleForm.info!=this.editRoleRow.info||this.editRoleForm.state!=this.editRoleRow.state?(0,n.sQ)(this.editRoleForm).then((e=>{e=e.data,200==e.code?((0,l.lY)("操作成功"),this.editRoleForm={},this.editRoleRow={},this.dialogVisible=!1,this.roleSearchForm.state="0",this.init()):(0,l.lY)(e.msg,"error")})):(0,l.lY)("您没有更改内容","warning")},resetEditForm(){this.editRoleForm={...this.editRoleRow}},submitNewForm(e){this.$refs[e].validate((e=>{e&&(console.log(this.newRoleForm),(0,n.tm)(this.newRoleForm).then((e=>{e=e.data,200==e.code?((0,l.lY)("操作成功"),this.newRoleForm={},this.newRoleVisable=!1,this.init()):(0,l.lY)(e.msg,"error")})))}))},resetNewForm(){this.newRoleForm={}},checkPermissons(e){this.distributionFulForm.rid=e,this.default_checked_mid=[],(0,n.WA)(e).then((e=>{e=e.data,200==e.code?(this.fuloptions=e.data.menus,this.default_checked_mid=e.data.menuIds.sort(((e,t)=>e-t)),console.log(typeof this.default_checked_mid),console.log(this.default_checked_mid)):(0,l.lY)(e.msg,"error")})),this.distributionFulVisable=!0},submitDistributionFulForm(){var e=this.$refs.tree.getCheckedKeys(),t=JSON.stringify(e).replace("[","").replace("]","");this.distributionFulForm.menuIds=t,console.log(this.distributionFulForm.menuIds),(0,n.Vf)(this.distributionFulForm).then((e=>{e=e.data,200==e.code?((0,l.lY)("操作成功"),this.distributionFulVisable=!1,this.distributionFulForm={},this.default_checked_mid=[],this.fuloptions=[]):(0,l.lY)(e.msg,"error")})),this.distributionFulVisable=!1},celDistributionFul(){this.default_checked_mid=[],this.fuloptions=[],this.distributionFulForm={},this.distributionFulVisable=!1}},created(){this.init()}},a=s,c=o(1656),u=(0,c.A)(a,i,r,!1,null,null,null),d=u.exports},5637:function(e,t,o){o.d(t,{Ue:function(){return d},Vf:function(){return u},WA:function(){return c},af:function(){return f},qE:function(){return m},r9:function(){return n},sQ:function(){return s},tm:function(){return a},wi:function(){return l}});var i=o(4464);const r="/system/role";function n(e){return(0,i.PP)(r+"/list",e)}function l(e){return(0,i.PP)(r+"/forbiddenRole",{rid:e})}function s(e){return(0,i.PP)(r+"/edit_role",e)}function a(e){return(0,i.PP)(r+"/save",e)}function c(e){return(0,i.hW)(r+"/checkPermissons",{rid:e})}function u(e){return(0,i.PP)(r+"/saveRolePermissons",e)}function d(){return(0,i.hW)(r+"/all",{})}function f(e){return(0,i.PP)(r+"/saveRoleEmp",e)}function m(e){return(0,i.hW)(r+"/queryRoleIdsByEid",{eid:e})}},2399:function(e,t,o){o.d(t,{eF:function(){return l},gf:function(){return n},kD:function(){return s},o7:function(){return a}});var i=o(8987);const r="token";function n(){return i.A.get(r)}function l(){return i.A.remove(r)}function s(){let e=i.A.get("employee");return JSON.parse(e)}function a(e){i.A.remove(e)}},4464:function(e,t,o){o.d(t,{NY:function(){return f},PP:function(){return d},hW:function(){return m},lY:function(){return p}});var i=o(2399),r=o(4335),n=o(1052),l=o(8987),s=o(5373),a=o.n(s);const c="http://sun.ipyingshe.com";function u(){var e=(0,i.gf)();return e&&(0,r.A)({url:c+"/checkedToken",method:"GET",params:{token:e}}).then((e=>{e=e.data,200==e.code&&(l.A.set("token",e.data.token,{expires:1/48}),l.A.set("employee",JSON.stringify(e.data.employee),{expires:1/48}))})).catch((e=>{console.log(e)})),e}function d(e,t){return(0,r.A)({url:c+e,method:"POST",headers:{token:u()},data:a().stringify(t)}).catch((e=>{console.log(e),void 0==e.response||void 0==e.response.data?p(e,"error"):p(e.response.data,"error")}))}function f(e,t){return(0,r.A)({url:c+e,method:"POST",headers:{"Content-Type":"application/json",token:u()},data:t}).catch((e=>{console.log(e),void 0==e.response||void 0==e.response.data?p(e,"error"):p(e.response.data,"error")}))}function m(e,t){return(0,r.A)({url:c+e,method:"GET",headers:{token:u()},params:t}).catch((e=>{console.log(e),void 0==e.response||void 0==e.response.data?p(e,"error"):p(e.response.data,"error")}))}function p(e,t){switch(t){case"warning":n.Message.warning(e);break;case"error":n.Message.error({message:e,duration:5e3,showClose:!0});break;case"info":n.Message.info(e);break;default:n.Message.success(e)}}},8987:function(e,t,o){
/*! js-cookie v3.0.5 | MIT */
function i(e){for(var t=1;t<arguments.length;t++){var o=arguments[t];for(var i in o)e[i]=o[i]}return e}o.d(t,{A:function(){return l}});var r={read:function(e){return'"'===e[0]&&(e=e.slice(1,-1)),e.replace(/(%[\dA-F]{2})+/gi,decodeURIComponent)},write:function(e){return encodeURIComponent(e).replace(/%(2[346BF]|3[AC-F]|40|5[BDE]|60|7[BCD])/g,decodeURIComponent)}};function n(e,t){function o(o,r,n){if("undefined"!==typeof document){n=i({},t,n),"number"===typeof n.expires&&(n.expires=new Date(Date.now()+864e5*n.expires)),n.expires&&(n.expires=n.expires.toUTCString()),o=encodeURIComponent(o).replace(/%(2[346B]|5E|60|7C)/g,decodeURIComponent).replace(/[()]/g,escape);var l="";for(var s in n)n[s]&&(l+="; "+s,!0!==n[s]&&(l+="="+n[s].split(";")[0]));return document.cookie=o+"="+e.write(r,o)+l}}function r(t){if("undefined"!==typeof document&&(!arguments.length||t)){for(var o=document.cookie?document.cookie.split("; "):[],i={},r=0;r<o.length;r++){var n=o[r].split("="),l=n.slice(1).join("=");try{var s=decodeURIComponent(n[0]);if(i[s]=e.read(l,s),t===s)break}catch(a){}}return t?i[t]:i}}return Object.create({set:o,get:r,remove:function(e,t){o(e,"",i({},t,{expires:-1}))},withAttributes:function(e){return n(this.converter,i({},this.attributes,e))},withConverter:function(e){return n(i({},this.converter,e),this.attributes)}},{attributes:{value:Object.freeze(t)},converter:{value:Object.freeze(e)}})}var l=n(r,{path:"/"})}}]);
//# sourceMappingURL=202.dc18bf06.js.map