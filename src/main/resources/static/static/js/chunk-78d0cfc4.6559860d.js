(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-78d0cfc4"],{3962:function(e,t,n){"use strict";n.d(t,"d",(function(){return o})),n.d(t,"e",(function(){return i})),n.d(t,"a",(function(){return s})),n.d(t,"c",(function(){return l})),n.d(t,"b",(function(){return c}));var r=n("b775"),a="/goods/category";function o(e){return Object(r["a"])({url:a+"/save",method:"post",data:e})}function i(e){return Object(r["a"])({url:a+"/update",method:"post",data:e})}function s(e){return Object(r["a"])({url:a+"/deactivate",method:"post",data:{cid:e}})}function l(e){return Object(r["a"])({url:a+"/queryPageByQo",method:"post",data:e})}function c(){return Object(r["a"])({url:a+"/normalCategoryAll",method:"get"})}},"5a77":function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("el-breadcrumb",{attrs:{separator:"/"}},[n("el-breadcrumb-item",[e._v("首页")]),n("el-breadcrumb-item",[e._v("商品管理")]),n("el-breadcrumb-item",[e._v("分类管理")])],1),n("br"),n("el-row",[n("el-col",{staticStyle:{"text-align":"left","padding-right":"10px"},attrs:{span:8}},[n("el-input",{attrs:{placeholder:"分类名称"},model:{value:e.searchForm.name,callback:function(t){e.$set(e.searchForm,"name",t)},expression:"searchForm.name"}})],1),n("el-col",{attrs:{span:8}},[n("el-select",{attrs:{placeholder:"状态",clearable:""},model:{value:e.searchForm.state,callback:function(t){e.$set(e.searchForm,"state",t)},expression:"searchForm.state"}},[n("el-option",{attrs:{label:"正常",value:"0"}}),n("el-option",{attrs:{label:"停用",value:"-1"}})],1)],1)],1),n("br"),n("el-row",[n("el-col",{staticStyle:{"text-align":"left"},attrs:{span:24}},[n("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:e.submitSearchForm}},[n("i",{staticClass:"iconfont icon-r-find",staticStyle:{"font-size":"18px"}}),e._v(" 搜索 ")]),n("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"success"},on:{click:function(t){e.newVisable=!0}}},[n("i",{staticClass:"iconfont icon-r-add",staticStyle:{"font-size":"18px"}}),e._v(" 创建分类 ")])],1)],1),n("br"),n("div",{staticClass:"table"},[n("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,size:"medium"}},[n("el-table-column",{attrs:{type:"index","label-width":"100px",label:"序号"}}),n("el-table-column",{attrs:{prop:"name","label-width":"100px",label:"名称"}}),n("el-table-column",{attrs:{prop:"info","label-width":"100px","show-overflow-tooltip":!0,label:"描述"}}),n("el-table-column",{attrs:{prop:"state","label-width":"100px",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return["0"==t.row.state?n("el-tag",{attrs:{type:"success"}},[e._v("正常 ")]):n("el-tag",{attrs:{type:"danger"}},[e._v("停用")])]}}])}),n("el-table-column",{attrs:{label:"操作",width:"240",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"success"},on:{click:function(n){return e.editbtn(t.row)}}},[n("i",{staticClass:"iconfont icon-r-edit",staticStyle:{"font-size":"18px"}}),e._v(" 修改 ")]),n("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"danger"},on:{click:function(n){return e.forbidden(t.row.id)}}},[n("i",{staticClass:"iconfont icon-r-no",staticStyle:{"font-size":"18px"}}),e._v(" 停用 ")])]}}])})],1),n("div",{staticStyle:{margin:"10px 0 15px 0"}},[n("el-pagination",{attrs:{"current-page":e.searchForm.currentPage,"page-sizes":[5,10,20,50],"page-size":e.searchForm.pageSize,layout:"total,sizes, prev, pager, next,jumper",total:e.searchForm.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange,"update:currentPage":function(t){return e.$set(e.searchForm,"currentPage",t)},"update:current-page":function(t){return e.$set(e.searchForm,"currentPage",t)}}})],1)],1),n("el-dialog",{attrs:{title:"创建商品分类",visible:e.newVisable,width:"50%"},on:{"update:visible":function(t){e.newVisable=t}}},[n("el-form",{ref:"newForm",staticClass:"demo-ruleForm",attrs:{model:e.newForm,rules:e.rules,"label-width":"100px"}},[n("el-form-item",{attrs:{label:"名称：",prop:"name"}},[n("el-input",{model:{value:e.newForm.name,callback:function(t){e.$set(e.newForm,"name",t)},expression:"newForm.name"}})],1),n("el-form-item",{attrs:{label:"描述："}},[n("el-input",{attrs:{type:"textarea"},model:{value:e.newForm.info,callback:function(t){e.$set(e.newForm,"info",t)},expression:"newForm.info"}})],1),n("el-form-item",[n("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:function(t){return e.submitNewForm("newForm")}}},[n("i",{staticClass:"iconfont icon-r-yes",staticStyle:{"font-size":"18px"}}),e._v(" 提交 ")]),n("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.saveCancel}},[e._v("取消 ")])],1)],1)],1),n("el-dialog",{attrs:{title:"分类信息修改",visible:e.dialogVisible,width:"50%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[n("el-form",{ref:"editForm",staticClass:"demo-ruleForm",attrs:{model:e.editForm,rules:e.rules,"label-width":"100px"}},[n("el-form-item",{attrs:{label:"名称：",prop:"name"}},[n("el-input",{model:{value:e.editForm.name,callback:function(t){e.$set(e.editForm,"name",t)},expression:"editForm.name"}})],1),n("el-form-item",{attrs:{label:"描述："}},[n("el-input",{attrs:{type:"textarea"},model:{value:e.editForm.info,callback:function(t){e.$set(e.editForm,"info",t)},expression:"editForm.info"}})],1),n("el-form-item",{attrs:{label:"状态：",prop:"state"}},[n("el-select",{attrs:{clearable:"",placeholder:"请选择状态"},on:{change:function(t){return e.$forceUpdate()}},model:{value:e.editForm.state,callback:function(t){e.$set(e.editForm,"state",t)},expression:"editForm.state"}},e._l(e.options,(function(e){return n("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),n("el-form-item",[n("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:function(t){return e.submitEditForm("editForm")}}},[n("i",{staticClass:"iconfont icon-r-yes",staticStyle:{"font-size":"18px"}}),e._v(" 提交 ")]),n("el-button",{staticStyle:{"font-size":"18px"},on:{click:function(t){return e.resetEditForm("editForm")}}},[n("i",{staticClass:"iconfont icon-r-refresh",staticStyle:{"font-size":"18px"}}),e._v(" 重置 ")])],1)],1)],1)],1)},a=[],o=n("5530"),i=n("e2a7"),s=n("3962"),l={data:function(){return{newVisable:!1,dialogVisible:!1,options:[{label:"正常",value:"0"},{label:"停用",value:"-1"}],tableData:[],searchForm:{total:0,currentPage:1,pageSize:5},editForm:{},newForm:{},rules:{name:[{required:!0,message:"名称不能为空",trigger:"blur"}],state:[{required:!0,message:"状态不能为空",trigger:"blur"}]}}},methods:{init:function(){var e=this;Object(s["c"])(this.searchForm).then((function(t){200===t.code?(console.log(t.data),e.tableData=t.data.records,e.searchForm.total=t.data.total,e.searchForm.pageSize=t.data.size,e.searchForm.currentPage=t.data.current):Object(i["a"])(t.msg,"error")}))},submitSearchForm:function(){this.init()},handleSizeChange:function(e){this.searchForm.pageSize=e,this.init(),console.log("每页 ".concat(e," 条"))},handleCurrentChange:function(e){this.searchForm.currentPage=e,this.init(),console.log("当前页: ".concat(e))},submitNewForm:function(e){var t=this;this.$refs[e].validate((function(e){e&&Object(s["d"])(t.newForm).then((function(e){200===e.code&&(Object(i["a"])("操作成功"),t.newForm={},t.newVisable=!1,t.init())}))}))},saveCancel:function(){this.newVisable=!1,this.$refs["newForm"].resetFields()},editbtn:function(e){this.editForm=Object(o["a"])({},e),this.dialogVisible=!0},submitEditForm:function(e){var t=this;this.$refs[e].validate((function(e){e&&Object(s["e"])(t.editForm).then((function(e){200===e.code&&(Object(i["a"])("操作成功"),t.editForm={},t.dialogVisible=!1,t.init())}))}))},resetEditForm:function(e){this.$refs[e].resetFields()},forbidden:function(e){var t=this;Object(s["a"])(e).then((function(e){200===e.code&&(Object(i["a"])("操作成功"),t.searchForm.currentPage=1),t.init()}))}},mounted:function(){this.init()}},c=l,u=(n("c127"),n("2877")),f=Object(u["a"])(c,r,a,!1,null,null,null);t["default"]=f.exports},"5f87":function(e,t,n){"use strict";n.d(t,"b",(function(){return i})),n.d(t,"d",(function(){return s})),n.d(t,"c",(function(){return l})),n.d(t,"a",(function(){return c}));n("b64b");var r=n("852e"),a=n.n(r),o="token";function i(){return a.a.get(o)}function s(){return a.a.remove(o)}function l(){var e=a.a.get("employee");return JSON.parse(e)}function c(e){a.a.remove(e)}},b775:function(e,t,n){"use strict";n("c7eb"),n("1da1"),n("5530");var r=n("53ca"),a=(n("d9e2"),n("caad"),n("fb6a"),n("e9c4"),n("b64b"),n("d3b7"),n("2532"),n("bc3a")),o=n.n(a),i=n("5c96"),s=n("4360"),l=n("5f87"),c={401:"认证失败，无法访问系统资源",403:"当前操作没有权限",404:"访问资源不存在",default:"系统未知错误，请反馈给管理员"};n("b85c"),n("a15b"),n("14d9"),n("4d63"),n("c607"),n("ac1f"),n("2c3e"),n("00b4"),n("25f0"),n("5319"),n("1276");function u(e){for(var t="",n=0,a=Object.keys(e);n<a.length;n++){var o=a[n],i=e[o],s=encodeURIComponent(o)+"=";if(null!==i&&""!==i&&"undefined"!==typeof i)if("object"===Object(r["a"])(i))for(var l=0,c=Object.keys(i);l<c.length;l++){var u=c[l];if(null!==i[u]&&""!==i[u]&&"undefined"!==typeof i[u]){var f=o+"["+u+"]",d=encodeURIComponent(f)+"=";t+=d+encodeURIComponent(i[u])+"&"}}else t+=s+encodeURIComponent(i)+"&"}return t}var f={set:function(e,t){sessionStorage&&null!=e&&null!=t&&sessionStorage.setItem(e,t)},get:function(e){return sessionStorage?null==e?null:sessionStorage.getItem(e):null},setJSON:function(e,t){null!=t&&this.set(e,JSON.stringify(t))},getJSON:function(e){var t=this.get(e);if(null!=t)return JSON.parse(t)},remove:function(e){sessionStorage.removeItem(e)}},d={set:function(e,t){localStorage&&null!=e&&null!=t&&localStorage.setItem(e,t)},get:function(e){return localStorage?null==e?null:localStorage.getItem(e):null},setJSON:function(e,t){null!=t&&this.set(e,JSON.stringify(t))},getJSON:function(e){var t=this.get(e);if(null!=t)return JSON.parse(t)},remove:function(e){localStorage.removeItem(e)}},m={session:f,local:d},b=(n("21a6"),{show:!1});o.a.defaults.headers["Content-Type"]="application/json;charset=utf-8";var p=o.a.create({baseURL:"/",timeout:1e4});p.interceptors.request.use((function(e){var t=!1===(e.headers||{}).isToken,n=!1===(e.headers||{}).repeatSubmit;if(Object(l["b"])()&&!t&&(e.headers["token"]=Object(l["b"])()),"get"===e.method&&e.params){var a=e.url+"?"+u(e.params);a=a.slice(0,-1),e.params={},e.url=a}if(!n&&("post"===e.method||"put"===e.method)){var o={url:e.url,data:"object"===Object(r["a"])(e.data)?JSON.stringify(e.data):e.data,time:(new Date).getTime()},i=m.session.getJSON("sessionObj");if(void 0===i||null===i||""===i)m.session.setJSON("sessionObj",o);else{var s=i.url,c=i.data,f=i.time,d=1e3;if(c===o.data&&o.time-f<d&&s===o.url){var b="数据正在处理，请勿重复提交";return console.warn("[".concat(s,"]: ")+b),Promise.reject(new Error(b))}m.session.setJSON("sessionObj",o)}}return e}),(function(e){console.log(e),Promise.reject(e)})),p.interceptors.response.use((function(e){var t=e.data.code||200,n=e.data.msg,r=c[t]||c["default"];return"blob"===e.request.responseType||"arraybuffer"===e.request.responseType?e.data:401===t?(b.show||(b.show=!0,i["MessageBox"].confirm("登录状态已过期，您可以继续留在该页面，或者重新登录","系统提示",{confirmButtonText:"重新登录",cancelButtonText:"取消",type:"warning"}).then((function(){b.show=!1,s["a"].dispatch("LogOut").then((function(){location.href="/index"}))})).catch((function(){b.show=!1}))),Promise.reject("无效的会话，或者会话已过期，请重新登录。")):500===t?(Object(i["Message"])({message:r,type:"error"}),Promise.reject(new Error(r))):601===t?(Object(i["Message"])({message:r,type:"warning"}),Promise.reject("error")):200!==t?(i["Notification"].error({title:n}),Promise.reject("error")):e.data}),(function(e){console.log("err"+e);var t=e.message;return"Network Error"==t?t="后端接口连接异常":t.includes("timeout")?t="系统接口请求超时":t.includes("Request failed with status code")&&(t="系统接口"+t.substr(t.length-3)+"异常"),Object(i["Message"])({message:t,type:"error",duration:5e3}),Promise.reject(e)}));t["a"]=p},c127:function(e,t,n){"use strict";n("cf7f")},cf7f:function(e,t,n){},e2a7:function(e,t,n){"use strict";n.d(t,"a",(function(){return a}));var r=n("5c96");function a(e,t){switch(t){case"warning":r["Message"].warning(e);break;case"error":r["Message"].error({message:e,duration:5e3,showClose:!0});break;case"info":r["Message"].info(e);break;default:r["Message"].success(e)}}}}]);