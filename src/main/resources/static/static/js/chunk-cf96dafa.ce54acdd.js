(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-cf96dafa"],{"04d1":function(e,t,o){var i=o("342f"),n=i.match(/firefox\/(\d+)/i);e.exports=!!n&&+n[1]},"083a":function(e,t,o){"use strict";var i=o("0d51"),n=TypeError;e.exports=function(e,t){if(!delete e[t])throw n("Cannot delete property "+i(t)+" of "+i(e))}},"1c9d":function(e,t,o){"use strict";o.r(t);var i=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"role_list"}},[o("el-breadcrumb",{attrs:{separator:"/"}},[o("el-breadcrumb-item",[e._v("首页")]),o("el-breadcrumb-item",[e._v("系统管理")]),o("el-breadcrumb-item",[e._v("角色管理")])],1),o("br"),o("el-row",[o("el-col",{staticStyle:{"text-align":"left","padding-right":"10px"},attrs:{span:8}},[o("el-input",{attrs:{placeholder:"角色名称"},model:{value:e.roleSearchForm.name,callback:function(t){e.$set(e.roleSearchForm,"name",t)},expression:"roleSearchForm.name"}})],1),o("el-col",{attrs:{span:8}},[o("el-select",{attrs:{placeholder:"状态",clearable:""},on:{change:e.$forceUpdate},model:{value:e.roleSearchForm.state,callback:function(t){e.$set(e.roleSearchForm,"state",t)},expression:"roleSearchForm.state"}},[o("el-option",{attrs:{label:"正常",value:"0"}}),o("el-option",{attrs:{label:"停用",value:"-1"}})],1)],1)],1),o("br"),o("el-row",[o("el-col",{staticStyle:{"text-align":"left"},attrs:{span:24}},[o("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:function(t){return e.subSearchForm("ruleForm")}}},[o("i",{staticClass:"iconfont icon-r-find",staticStyle:{"font-size":"18px"}}),e._v(" 搜索 ")]),o("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"success"},on:{click:function(t){e.newRoleVisable=!0}}},[o("i",{staticClass:"iconfont icon-r-add",staticStyle:{"font-size":"18px"}}),e._v(" 创建角色 ")])],1)],1),o("br"),o("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData}},[o("el-table-column",{attrs:{type:"index",label:"序号",width:"180"}}),o("el-table-column",{attrs:{prop:"name",label:"名称",width:"180"}}),o("el-table-column",{attrs:{prop:"info",label:"描述"}}),o("el-table-column",{attrs:{prop:"state",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return["0"==t.row.state?o("el-tag",{attrs:{type:"success"}},[e._v("正常 ")]):o("el-tag",{attrs:{type:"danger"}},[e._v("停用")])]}}])}),o("el-table-column",{attrs:{label:"操作",width:"360",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"success"},on:{click:function(o){return e.editRole(t.row)}}},[o("i",{staticClass:"iconfont icon-r-edit",staticStyle:{"font-size":"18px"}}),e._v(" 修改 ")]),"0"==t.row.state?o("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"danger"},on:{click:function(o){return e.forbidden(t.row.id)}}},[o("i",{staticClass:"iconfont icon-r-no",staticStyle:{"font-size":"18px"}}),e._v(" 停用 ")]):e._e(),o("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"warning"},on:{click:function(o){return e.checkPermissions(t.row.id)}}},[o("i",{staticClass:"iconfont icon-r-setting",staticStyle:{"font-size":"18px"}}),e._v(" 授权 ")])]}}])})],1),o("el-dialog",{attrs:{title:"角色信息修改",visible:e.dialogVisible,width:"50%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[o("el-form",{staticClass:"demo-ruleForm",attrs:{"label-width":"100px"},model:{value:e.editRoleForm,callback:function(t){e.editRoleForm=t},expression:"editRoleForm"}},[o("el-form-item",{attrs:{label:"名称："}},[o("el-input",{attrs:{disabled:""},model:{value:e.editRoleForm.name,callback:function(t){e.$set(e.editRoleForm,"name",t)},expression:"editRoleForm.name"}})],1),o("el-form-item",{attrs:{label:"描述："}},[o("el-input",{attrs:{type:"text"},model:{value:e.editRoleForm.info,callback:function(t){e.$set(e.editRoleForm,"info",t)},expression:"editRoleForm.info"}})],1),o("el-form-item",{attrs:{label:"状态："}},[o("el-select",{attrs:{clearable:"",placeholder:"请选择状态"},on:{change:function(t){return e.$forceUpdate()}},model:{value:e.editRoleForm.state,callback:function(t){e.$set(e.editRoleForm,"state",t)},expression:"editRoleForm.state"}},e._l(e.options,(function(e){return o("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),o("el-form-item",[o("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:e.submitEditForm}},[o("i",{staticClass:"iconfont icon-r-yes",staticStyle:{"font-size":"18px"}}),e._v("提交 ")]),o("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.resetEditForm}},[o("i",{staticClass:"iconfont icon-r-refresh",staticStyle:{"font-size":"18px"}}),e._v("重置 ")])],1)],1)],1),o("el-dialog",{attrs:{title:"创建角色",visible:e.newRoleVisable,width:"50%"},on:{"update:visible":function(t){e.newRoleVisable=t}}},[o("el-form",{ref:"newRoleForm",staticClass:"demo-ruleForm",attrs:{model:e.newRoleForm,rules:e.rules,"label-width":"100px"}},[o("el-form-item",{attrs:{label:"名称：",prop:"name"}},[o("el-input",{model:{value:e.newRoleForm.name,callback:function(t){e.$set(e.newRoleForm,"name",t)},expression:"newRoleForm.name"}})],1),o("el-form-item",{attrs:{label:"描述："}},[o("el-input",{attrs:{type:"text"},model:{value:e.newRoleForm.info,callback:function(t){e.$set(e.newRoleForm,"info",t)},expression:"newRoleForm.info"}})],1),o("el-form-item",[o("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:function(t){return e.submitNewForm("newRoleForm")}}},[o("i",{staticClass:"iconfont icon-r-add",staticStyle:{"font-size":"18px"}}),e._v(" 创建角色 ")]),o("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.resetNewForm}},[o("i",{staticClass:"iconfont icon-r-refresh",staticStyle:{"font-size":"18px"}}),e._v("重置 ")])],1)],1)],1),o("el-dialog",{attrs:{title:"权限分配",visible:e.distributionFulVisable,width:"50%"},on:{"update:visible":function(t){e.distributionFulVisable=t}}},[o("el-form",{staticClass:"demo-ruleForm",attrs:{model:e.distributionFulForm,"label-width":"100px"}},[o("el-form-item",[o("el-input",{attrs:{placeholder:"输入关键字进行过滤"},model:{value:e.filterText,callback:function(t){e.filterText=t},expression:"filterText"}}),o("el-tree",{ref:"tree",staticClass:"filter-tree",attrs:{data:e.fuloptions,"show-checkbox":"","node-key":"value","default-checked-keys":e.default_checked_mid,"default-expand-all":"","filter-node-method":e.filterNode,props:e.props}})],1),o("el-form-item",[o("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:e.submitDistributionFulForm}},[o("i",{staticClass:"iconfont icon-r-add",staticStyle:{"font-size":"18px"}}),e._v("提交 ")]),o("el-button",{staticStyle:{"font-size":"18px"},on:{click:e.celDistributionFul}},[e._v("取消 ")])],1)],1)],1)],1)},n=[],r=o("53ca"),a=o("5530"),s=(o("4de4"),o("4e82"),o("e9c4"),o("d3b7"),o("ac1f"),o("5319"),o("48cf")),l=o("e2a7"),c={data:function(){return{tableData:[],editRoleForm:{},editRoleRow:{},dialogVisible:!1,newRoleVisable:!1,distributionFulVisable:!1,newRoleForm:{},roleSearchForm:{},distributionFulForm:{},options:[{label:"正常",value:"0"},{label:"停用",value:"-1"}],props:{multiple:!0},fuloptions:[],default_checked_mid:[],filterText:"",rules:{name:[{required:!0,message:"名称不能为空",trigger:"blur"}]}}},watch:{filterText:function(e){this.$refs.tree.filter(e)}},methods:{filterNode:function(e,t){return!e||-1!==t.label.indexOf(e)},init:function(){var e=this;Object(s["f"])(this.roleSearchForm).then((function(t){(t.code=200)?e.tableData=t.data:Object(l["a"])(t.msg,"error")}))},forbidden:function(e){var t=this;Object(s["c"])(e).then((function(e){200===e.code?(t.roleSearchForm.state="0",t.init(),Object(l["a"])("操作成功")):Object(l["a"])(e.msg,"error")}))},editRole:function(e){this.editRoleForm=Object(a["a"])({},e),this.editRoleRow=Object(a["a"])({},e),this.dialogVisible=!0},subSearchForm:function(){this.init()},submitEditForm:function(){var e=this;this.editRoleForm.info!=this.editRoleRow.info||this.editRoleForm.state!=this.editRoleRow.state?Object(s["b"])(this.editRoleForm).then((function(t){200===t.code&&(Object(l["a"])("操作成功"),e.editRoleForm={},e.editRoleRow={},e.dialogVisible=!1,e.roleSearchForm.state="0",e.init())})):Object(l["a"])("您没有更改内容","warning")},resetEditForm:function(){this.editRoleForm=Object(a["a"])({},this.editRoleRow)},submitNewForm:function(e){var t=this;this.$refs[e].validate((function(e){e&&(console.log(t.newRoleForm),Object(s["g"])(t.newRoleForm).then((function(e){200===e.code&&(Object(l["a"])("操作成功"),t.newRoleForm={},t.newRoleVisable=!1,t.init())})))}))},resetNewForm:function(){this.newRoleForm={}},checkPermissions:function(e){var t=this;this.distributionFulForm.rid=e,this.default_checked_mid=[],Object(s["a"])(e).then((function(e){200===e.code&&(t.fuloptions=e.data.menus,t.default_checked_mid=e.data.menuIds.sort((function(e,t){return e-t})),console.log(Object(r["a"])(t.default_checked_mid)),console.log(t.default_checked_mid))})),this.distributionFulVisable=!0},submitDistributionFulForm:function(){var e=this,t=this.$refs.tree.getCheckedKeys(),o=JSON.stringify(t).replace("[","").replace("]","");this.distributionFulForm.menuIds=o,console.log(this.distributionFulForm.menuIds),Object(s["i"])(this.distributionFulForm).then((function(t){200===t.code&&(Object(l["a"])("操作成功"),e.distributionFulVisable=!1,e.distributionFulForm={},e.default_checked_mid=[],e.fuloptions=[])})),this.distributionFulVisable=!1},celDistributionFul:function(){this.default_checked_mid=[],this.fuloptions=[],this.distributionFulForm={},this.distributionFulVisable=!1}},created:function(){this.init()}},u=c,d=(o("6b05"),o("2877")),f=Object(d["a"])(u,i,n,!1,null,null,null);t["default"]=f.exports},"48cf":function(e,t,o){"use strict";o.d(t,"f",(function(){return r})),o.d(t,"c",(function(){return a})),o.d(t,"b",(function(){return s})),o.d(t,"g",(function(){return l})),o.d(t,"a",(function(){return c})),o.d(t,"i",(function(){return u})),o.d(t,"d",(function(){return d})),o.d(t,"h",(function(){return f})),o.d(t,"e",(function(){return m}));var i=o("b775"),n="/system/role";function r(e){return Object(i["a"])({url:n+"/list",method:"post",data:e})}function a(e){return Object(i["a"])({url:n+"/forbiddenRole",method:"post",data:{rid:e}})}function s(e){return Object(i["a"])({url:n+"/edit_role",method:"post",data:e})}function l(e){return Object(i["a"])({url:n+"/save",method:"post",data:e})}function c(e){return Object(i["a"])({url:n+"/checkPermissions",method:"get",params:{rid:e}})}function u(e){return Object(i["a"])({url:n+"/saveRolePermissions",method:"post",data:e})}function d(){return Object(i["a"])({url:n+"/all",method:"get"})}function f(e){return Object(i["a"])({url:n+"/saveRoleEmp",method:"post",data:e})}function m(e){return Object(i["a"])({url:n+"/queryRoleIdsByEid",method:"get",params:{eid:e}})}},"4e82":function(e,t,o){"use strict";var i=o("23e7"),n=o("e330"),r=o("59ed"),a=o("7b0b"),s=o("07fa"),l=o("083a"),c=o("577e"),u=o("d039"),d=o("addb"),f=o("a640"),m=o("04d1"),b=o("d998"),p=o("2d00"),h=o("512ce"),v=[],g=n(v.sort),F=n(v.push),w=u((function(){v.sort(void 0)})),y=u((function(){v.sort(null)})),S=f("sort"),R=!u((function(){if(p)return p<70;if(!(m&&m>3)){if(b)return!0;if(h)return h<603;var e,t,o,i,n="";for(e=65;e<76;e++){switch(t=String.fromCharCode(e),e){case 66:case 69:case 70:case 72:o=3;break;case 68:case 71:o=4;break;default:o=2}for(i=0;i<47;i++)v.push({k:t+i,v:o})}for(v.sort((function(e,t){return t.v-e.v})),i=0;i<v.length;i++)t=v[i].k.charAt(0),n.charAt(n.length-1)!==t&&(n+=t);return"DGBEFHACIJK"!==n}})),x=w||!y||!S||!R,k=function(e){return function(t,o){return void 0===o?-1:void 0===t?1:void 0!==e?+e(t,o)||0:c(t)>c(o)?1:-1}};i({target:"Array",proto:!0,forced:x},{sort:function(e){void 0!==e&&r(e);var t=a(this);if(R)return void 0===e?g(t):g(t,e);var o,i,n=[],c=s(t);for(i=0;i<c;i++)i in t&&F(n,t[i]);d(n,k(e)),o=s(n),i=0;while(i<o)t[i]=n[i++];while(i<c)l(t,i++);return t}})},"512ce":function(e,t,o){var i=o("342f"),n=i.match(/AppleWebKit\/(\d+)\./);e.exports=!!n&&+n[1]},"5f87":function(e,t,o){"use strict";o.d(t,"b",(function(){return a})),o.d(t,"d",(function(){return s})),o.d(t,"c",(function(){return l})),o.d(t,"a",(function(){return c}));o("b64b");var i=o("852e"),n=o.n(i),r="token";function a(){return n.a.get(r)}function s(){return n.a.remove(r)}function l(){var e=n.a.get("employee");return JSON.parse(e)}function c(e){n.a.remove(e)}},"6b05":function(e,t,o){"use strict";o("6e3f")},"6e3f":function(e,t,o){},addb:function(e,t,o){var i=o("4dae"),n=Math.floor,r=function(e,t){var o=e.length,l=n(o/2);return o<8?a(e,t):s(e,r(i(e,0,l),t),r(i(e,l),t),t)},a=function(e,t){var o,i,n=e.length,r=1;while(r<n){i=r,o=e[r];while(i&&t(e[i-1],o)>0)e[i]=e[--i];i!==r++&&(e[i]=o)}return e},s=function(e,t,o,i){var n=t.length,r=o.length,a=0,s=0;while(a<n||s<r)e[a+s]=a<n&&s<r?i(t[a],o[s])<=0?t[a++]:o[s++]:a<n?t[a++]:o[s++];return e};e.exports=r},b775:function(e,t,o){"use strict";o("c7eb"),o("1da1"),o("5530");var i=o("53ca"),n=(o("d9e2"),o("caad"),o("fb6a"),o("e9c4"),o("b64b"),o("d3b7"),o("2532"),o("bc3a")),r=o.n(n),a=o("5c96"),s=o("4360"),l=o("5f87"),c={401:"认证失败，无法访问系统资源",403:"当前操作没有权限",404:"访问资源不存在",default:"系统未知错误，请反馈给管理员"};o("b85c"),o("a15b"),o("14d9"),o("4d63"),o("c607"),o("ac1f"),o("2c3e"),o("00b4"),o("25f0"),o("5319"),o("1276");function u(e){for(var t="",o=0,n=Object.keys(e);o<n.length;o++){var r=n[o],a=e[r],s=encodeURIComponent(r)+"=";if(null!==a&&""!==a&&"undefined"!==typeof a)if("object"===Object(i["a"])(a))for(var l=0,c=Object.keys(a);l<c.length;l++){var u=c[l];if(null!==a[u]&&""!==a[u]&&"undefined"!==typeof a[u]){var d=r+"["+u+"]",f=encodeURIComponent(d)+"=";t+=f+encodeURIComponent(a[u])+"&"}}else t+=s+encodeURIComponent(a)+"&"}return t}var d={set:function(e,t){sessionStorage&&null!=e&&null!=t&&sessionStorage.setItem(e,t)},get:function(e){return sessionStorage?null==e?null:sessionStorage.getItem(e):null},setJSON:function(e,t){null!=t&&this.set(e,JSON.stringify(t))},getJSON:function(e){var t=this.get(e);if(null!=t)return JSON.parse(t)},remove:function(e){sessionStorage.removeItem(e)}},f={set:function(e,t){localStorage&&null!=e&&null!=t&&localStorage.setItem(e,t)},get:function(e){return localStorage?null==e?null:localStorage.getItem(e):null},setJSON:function(e,t){null!=t&&this.set(e,JSON.stringify(t))},getJSON:function(e){var t=this.get(e);if(null!=t)return JSON.parse(t)},remove:function(e){localStorage.removeItem(e)}},m={session:d,local:f},b=(o("21a6"),{show:!1});r.a.defaults.headers["Content-Type"]="application/json;charset=utf-8";var p=r.a.create({baseURL:"/",timeout:1e4});p.interceptors.request.use((function(e){var t=!1===(e.headers||{}).isToken,o=!1===(e.headers||{}).repeatSubmit;if(Object(l["b"])()&&!t&&(e.headers["token"]=Object(l["b"])()),"get"===e.method&&e.params){var n=e.url+"?"+u(e.params);n=n.slice(0,-1),e.params={},e.url=n}if(!o&&("post"===e.method||"put"===e.method)){var r={url:e.url,data:"object"===Object(i["a"])(e.data)?JSON.stringify(e.data):e.data,time:(new Date).getTime()},a=m.session.getJSON("sessionObj");if(void 0===a||null===a||""===a)m.session.setJSON("sessionObj",r);else{var s=a.url,c=a.data,d=a.time,f=1e3;if(c===r.data&&r.time-d<f&&s===r.url){var b="数据正在处理，请勿重复提交";return console.warn("[".concat(s,"]: ")+b),Promise.reject(new Error(b))}m.session.setJSON("sessionObj",r)}}return e}),(function(e){console.log(e),Promise.reject(e)})),p.interceptors.response.use((function(e){var t=e.data.code||200,o=e.data.msg,i=c[t]||c["default"];return"blob"===e.request.responseType||"arraybuffer"===e.request.responseType?e.data:401===t?(b.show||(b.show=!0,a["MessageBox"].confirm("登录状态已过期，您可以继续留在该页面，或者重新登录","系统提示",{confirmButtonText:"重新登录",cancelButtonText:"取消",type:"warning"}).then((function(){b.show=!1,s["a"].dispatch("LogOut").then((function(){location.href="/index"}))})).catch((function(){b.show=!1}))),Promise.reject("无效的会话，或者会话已过期，请重新登录。")):500===t?(Object(a["Message"])({message:i,type:"error"}),Promise.reject(new Error(i))):601===t?(Object(a["Message"])({message:i,type:"warning"}),Promise.reject("error")):200!==t?(a["Notification"].error({title:o}),Promise.reject("error")):e.data}),(function(e){console.log("err"+e);var t=e.message;return"Network Error"==t?t="后端接口连接异常":t.includes("timeout")?t="系统接口请求超时":t.includes("Request failed with status code")&&(t="系统接口"+t.substr(t.length-3)+"异常"),Object(a["Message"])({message:t,type:"error",duration:5e3}),Promise.reject(e)}));t["a"]=p},d998:function(e,t,o){var i=o("342f");e.exports=/MSIE|Trident/.test(i)},e2a7:function(e,t,o){"use strict";o.d(t,"a",(function(){return n}));var i=o("5c96");function n(e,t){switch(t){case"warning":i["Message"].warning(e);break;case"error":i["Message"].error({message:e,duration:5e3,showClose:!0});break;case"info":i["Message"].info(e);break;default:i["Message"].success(e)}}}}]);