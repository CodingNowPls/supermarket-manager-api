"use strict";(self["webpackChunksupermarket_manager"]=self["webpackChunksupermarket_manager"]||[]).push([[90],{4090:function(e,t,r){r.r(t),r.d(t,{default:function(){return d}});var n=function(){var e=this,t=e._self._c;return t("div",[t("el-breadcrumb",{attrs:{separator:"/"}},[t("el-breadcrumb-item",[e._v("首页")]),t("el-breadcrumb-item",[e._v("库存管理 ")]),t("el-breadcrumb-item",[e._v("仓库管理")])],1),t("br"),t("el-row",[t("el-col",{staticStyle:{"text-align":"left","padding-right":"10px"},attrs:{span:8}},[t("el-input",{attrs:{placeholder:"仓库名称"},model:{value:e.searchForm.name,callback:function(t){e.$set(e.searchForm,"name",t)},expression:"searchForm.name"}})],1),t("el-col",{attrs:{span:8}},[t("el-select",{attrs:{placeholder:"请选择状态",clearable:""},model:{value:e.searchForm.state,callback:function(t){e.$set(e.searchForm,"state",t)},expression:"searchForm.state"}},[t("el-option",{attrs:{label:"正常",value:"0"}}),t("el-option",{attrs:{label:"停用",value:"-1"}})],1)],1)],1),t("br"),t("el-row",[t("el-col",{staticStyle:{"text-align":"left"},attrs:{span:24}},[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:e.subSearchForm}},[t("i",{staticClass:"iconfont icon-r-find",staticStyle:{"font-size":"18px"}}),e._v(" 搜索")]),t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"success"},on:{click:function(t){e.newVisable=!0}}},[t("i",{staticClass:"iconfont icon-r-add",staticStyle:{"font-size":"18px"}}),e._v(" 创建仓库 ")])],1)],1),t("br"),t("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData}},[t("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),t("el-table-column",{attrs:{prop:"name",label:"名称",width:"180"}}),t("el-table-column",{attrs:{prop:"address",label:"地址",width:"180"}}),t("el-table-column",{attrs:{prop:"info","show-overflow-tooltip":!0,label:"描述"}}),t("el-table-column",{attrs:{prop:"state",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(r){return["0"==r.row.state?t("el-tag",{attrs:{type:"success"}},[e._v("正常")]):t("el-tag",{attrs:{type:"danger"}},[e._v("停用")])]}}])}),t("el-table-column",{attrs:{width:"240",fixed:"right",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(r){return[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"success"},on:{click:function(t){return e.editStore(r.row)}}},[t("i",{staticClass:"iconfont icon-r-edit",staticStyle:{"font-size":"18px"}}),e._v(" 修改 ")]),t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"danger"},on:{click:function(t){return e.forbidden(r.row.id)}}},[t("i",{staticClass:"iconfont icon-r-no",staticStyle:{"font-size":"18px"}}),e._v(" 停用 ")])]}}])})],1),t("el-dialog",{attrs:{title:"仓库信息修改",visible:e.dialogVisible,width:"50%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("el-form",{staticClass:"demo-ruleForm",attrs:{"label-width":"100px"},model:{value:e.editForm,callback:function(t){e.editForm=t},expression:"editForm"}},[t("el-form-item",{attrs:{label:"名称："}},[t("el-input",{model:{value:e.editForm.name,callback:function(t){e.$set(e.editForm,"name",t)},expression:"editForm.name"}})],1),t("el-form-item",{attrs:{label:"地址："}},[t("el-input",{attrs:{type:"text"},model:{value:e.editForm.address,callback:function(t){e.$set(e.editForm,"address",t)},expression:"editForm.address"}})],1),t("el-form-item",{attrs:{label:"描述："}},[t("el-input",{attrs:{type:"text"},model:{value:e.editForm.info,callback:function(t){e.$set(e.editForm,"info",t)},expression:"editForm.info"}})],1),t("el-form-item",{attrs:{label:"状态："}},[t("el-select",{attrs:{clearable:"",placeholder:"请选择状态"},on:{change:function(t){return e.$forceUpdate()}},model:{value:e.editForm.state,callback:function(t){e.$set(e.editForm,"state",t)},expression:"editForm.state"}},e._l(e.options,(function(e){return t("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),t("el-form-item",[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:e.submitEditForm}},[t("i",{staticClass:"iconfont icon-r-yes",staticStyle:{"font-size":"18px"}}),e._v(" 提交 ")]),t("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.editCancel}},[e._v("取消")])],1)],1)],1),t("el-dialog",{attrs:{title:"创建仓库",visible:e.newVisable,width:"50%"},on:{"update:visible":function(t){e.newVisable=t}}},[t("el-form",{ref:"newForm",staticClass:"demo-ruleForm",attrs:{model:e.newForm,rules:e.rules,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"名称：",prop:"name"}},[t("el-input",{model:{value:e.newForm.name,callback:function(t){e.$set(e.newForm,"name",t)},expression:"newForm.name"}})],1),t("el-form-item",{attrs:{label:"地址：",prop:"address"}},[t("el-input",{attrs:{type:"text"},model:{value:e.newForm.address,callback:function(t){e.$set(e.newForm,"address",t)},expression:"newForm.address"}})],1),t("el-form-item",{attrs:{label:"描述："}},[t("el-input",{attrs:{type:"text"},model:{value:e.newForm.info,callback:function(t){e.$set(e.newForm,"info",t)},expression:"newForm.info"}})],1),t("el-form-item",[t("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:function(t){return e.submitNewForm("newForm")}}},[t("i",{staticClass:"iconfont icon-r-yes",staticStyle:{"font-size":"18px"}}),e._v(" 提交")]),t("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.saveCancel}},[e._v("取消")])],1)],1)],1)],1)},o=[],i=r(9934),a=r(4464),s={data(){return{tableData:[],newStoreVisable:!1,dialogVisible:!1,editForm:{},searchForm:{},newVisable:!1,newForm:{},options:[{label:"正常",value:"0"},{label:"停用",value:"-1"}],rules:{name:[{required:!0,message:"名称不能为空",trigger:"blur"}],address:[{required:!0,message:"地名不能为空",trigger:"blur"}]}}},methods:{subSearchForm(){this.init()},init(){(0,i.CZ)(this.searchForm).then((e=>{200==e.data.code?this.tableData=e.data.data:(0,a.lY)(e.data.msg,"error")}))},editStore(e){this.editForm={...e},this.dialogVisible=!0},forbidden(e){(0,i.LU)(e).then((e=>{e=e.data,200==e.code?(0,a.lY)("操作成功"):(0,a.lY)(e.msg,"error"),this.init()}))},submitEditForm(){(0,i.yo)(this.editForm).then((e=>{e=e.data,200==e.code?((0,a.lY)("操作成功"),this.init(),this.dialogVisible=!1):(0,a.lY)(e.msg,"error")}))},editCancel(){this.dialogVisible=!1,this.editForm={},(0,a.lY)("已取消操作","info")},submitNewForm(e){this.$refs[e].validate((e=>{e&&(0,i.UN)(this.newForm).then((e=>{e=e.data,200==e.code?((0,a.lY)("操作成功"),this.newForm={},this.newVisable=!1,this.init()):(0,a.lY)(e.msg,"error")}))}))},saveCancel(){this.newVisable=!1,this.$refs["newForm"].resetFields()}},mounted(){this.init()}},l=s,c=r(1656),u=(0,c.A)(l,n,o,!1,null,null,null),d=u.exports},9934:function(e,t,r){r.d(t,{CZ:function(){return i},LU:function(){return l},UN:function(){return a},yo:function(){return s}});var n=r(4464);const o="/inventory_management/store";function i(e){return(0,n.PP)(o+"/list",e)}function a(e){return(0,n.PP)(o+"/save",e)}function s(e){return(0,n.PP)(o+"/update",e)}function l(e){return(0,n.PP)(o+"/deactivate",{sid:e})}},2399:function(e,t,r){r.d(t,{eF:function(){return a},gf:function(){return i},kD:function(){return s},o7:function(){return l}});var n=r(8987);const o="token";function i(){return n.A.get(o)}function a(){return n.A.remove(o)}function s(){let e=n.A.get("employee");return JSON.parse(e)}function l(e){n.A.remove(e)}},4464:function(e,t,r){r.d(t,{NY:function(){return m},PP:function(){return d},hW:function(){return f},lY:function(){return p}});var n=r(2399),o=r(4335),i=r(1052),a=r(8987),s=r(5373),l=r.n(s);const c="http://sun.ipyingshe.com";function u(){var e=(0,n.gf)();return e&&(0,o.A)({url:c+"/checkedToken",method:"GET",params:{token:e}}).then((e=>{e=e.data,200==e.code&&(a.A.set("token",e.data.token,{expires:1/48}),a.A.set("employee",JSON.stringify(e.data.employee),{expires:1/48}))})).catch((e=>{console.log(e)})),e}function d(e,t){return(0,o.A)({url:c+e,method:"POST",headers:{token:u()},data:l().stringify(t)}).catch((e=>{console.log(e),void 0==e.response||void 0==e.response.data?p(e,"error"):p(e.response.data,"error")}))}function m(e,t){return(0,o.A)({url:c+e,method:"POST",headers:{"Content-Type":"application/json",token:u()},data:t}).catch((e=>{console.log(e),void 0==e.response||void 0==e.response.data?p(e,"error"):p(e.response.data,"error")}))}function f(e,t){return(0,o.A)({url:c+e,method:"GET",headers:{token:u()},params:t}).catch((e=>{console.log(e),void 0==e.response||void 0==e.response.data?p(e,"error"):p(e.response.data,"error")}))}function p(e,t){switch(t){case"warning":i.Message.warning(e);break;case"error":i.Message.error({message:e,duration:5e3,showClose:!0});break;case"info":i.Message.info(e);break;default:i.Message.success(e)}}},8987:function(e,t,r){
/*! js-cookie v3.0.5 | MIT */
function n(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)e[n]=r[n]}return e}r.d(t,{A:function(){return a}});var o={read:function(e){return'"'===e[0]&&(e=e.slice(1,-1)),e.replace(/(%[\dA-F]{2})+/gi,decodeURIComponent)},write:function(e){return encodeURIComponent(e).replace(/%(2[346BF]|3[AC-F]|40|5[BDE]|60|7[BCD])/g,decodeURIComponent)}};function i(e,t){function r(r,o,i){if("undefined"!==typeof document){i=n({},t,i),"number"===typeof i.expires&&(i.expires=new Date(Date.now()+864e5*i.expires)),i.expires&&(i.expires=i.expires.toUTCString()),r=encodeURIComponent(r).replace(/%(2[346B]|5E|60|7C)/g,decodeURIComponent).replace(/[()]/g,escape);var a="";for(var s in i)i[s]&&(a+="; "+s,!0!==i[s]&&(a+="="+i[s].split(";")[0]));return document.cookie=r+"="+e.write(o,r)+a}}function o(t){if("undefined"!==typeof document&&(!arguments.length||t)){for(var r=document.cookie?document.cookie.split("; "):[],n={},o=0;o<r.length;o++){var i=r[o].split("="),a=i.slice(1).join("=");try{var s=decodeURIComponent(i[0]);if(n[s]=e.read(a,s),t===s)break}catch(l){}}return t?n[t]:n}}return Object.create({set:r,get:o,remove:function(e,t){r(e,"",n({},t,{expires:-1}))},withAttributes:function(e){return i(this.converter,n({},this.attributes,e))},withConverter:function(e){return i(n({},this.converter,e),this.attributes)}},{attributes:{value:Object.freeze(t)},converter:{value:Object.freeze(e)}})}var a=i(o,{path:"/"})}}]);
//# sourceMappingURL=90.054b0c2b.js.map