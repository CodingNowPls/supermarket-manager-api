(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6a1bd60c"],{"16c7":function(e,t,r){"use strict";r.d(t,"b",(function(){return a})),r.d(t,"c",(function(){return i})),r.d(t,"d",(function(){return s})),r.d(t,"a",(function(){return l}));var n=r("b775"),o="/person/dept";function a(e){return Object(n["a"])({url:o+"/list",method:"get",params:e})}function i(e){return Object(n["a"])({url:o+"/save",method:"post",data:e})}function s(e){return Object(n["a"])({url:o+"/update",method:"post",data:e})}function l(e){return Object(n["a"])({url:o+"/deactivate",method:"post",data:{id:e}})}},"5f87":function(e,t,r){"use strict";r.d(t,"b",(function(){return i})),r.d(t,"d",(function(){return s})),r.d(t,"c",(function(){return l})),r.d(t,"a",(function(){return c}));r("b64b");var n=r("852e"),o=r.n(n),a="token";function i(){return o.a.get(a)}function s(){return o.a.remove(a)}function l(){var e=o.a.get("employee");return JSON.parse(e)}function c(e){o.a.remove(e)}},b775:function(e,t,r){"use strict";r("c7eb"),r("1da1"),r("5530");var n=r("53ca"),o=(r("d9e2"),r("caad"),r("fb6a"),r("e9c4"),r("b64b"),r("d3b7"),r("2532"),r("bc3a")),a=r.n(o),i=r("5c96"),s=r("4360"),l=r("5f87"),c={401:"认证失败，无法访问系统资源",403:"当前操作没有权限",404:"访问资源不存在",default:"系统未知错误，请反馈给管理员"};r("b85c"),r("a15b"),r("14d9"),r("4d63"),r("c607"),r("ac1f"),r("2c3e"),r("00b4"),r("25f0"),r("5319"),r("1276");function u(e){for(var t="",r=0,o=Object.keys(e);r<o.length;r++){var a=o[r],i=e[a],s=encodeURIComponent(a)+"=";if(null!==i&&""!==i&&"undefined"!==typeof i)if("object"===Object(n["a"])(i))for(var l=0,c=Object.keys(i);l<c.length;l++){var u=c[l];if(null!==i[u]&&""!==i[u]&&"undefined"!==typeof i[u]){var m=a+"["+u+"]",f=encodeURIComponent(m)+"=";t+=f+encodeURIComponent(i[u])+"&"}}else t+=s+encodeURIComponent(i)+"&"}return t}var m={set:function(e,t){sessionStorage&&null!=e&&null!=t&&sessionStorage.setItem(e,t)},get:function(e){return sessionStorage?null==e?null:sessionStorage.getItem(e):null},setJSON:function(e,t){null!=t&&this.set(e,JSON.stringify(t))},getJSON:function(e){var t=this.get(e);if(null!=t)return JSON.parse(t)},remove:function(e){sessionStorage.removeItem(e)}},f={set:function(e,t){localStorage&&null!=e&&null!=t&&localStorage.setItem(e,t)},get:function(e){return localStorage?null==e?null:localStorage.getItem(e):null},setJSON:function(e,t){null!=t&&this.set(e,JSON.stringify(t))},getJSON:function(e){var t=this.get(e);if(null!=t)return JSON.parse(t)},remove:function(e){localStorage.removeItem(e)}},d={session:m,local:f},p=(r("21a6"),{show:!1});a.a.defaults.headers["Content-Type"]="application/json;charset=utf-8";var b=a.a.create({baseURL:"/",timeout:1e4});b.interceptors.request.use((function(e){var t=!1===(e.headers||{}).isToken,r=!1===(e.headers||{}).repeatSubmit;if(Object(l["b"])()&&!t&&(e.headers["token"]=Object(l["b"])()),"get"===e.method&&e.params){var o=e.url+"?"+u(e.params);o=o.slice(0,-1),e.params={},e.url=o}if(!r&&("post"===e.method||"put"===e.method)){var a={url:e.url,data:"object"===Object(n["a"])(e.data)?JSON.stringify(e.data):e.data,time:(new Date).getTime()},i=d.session.getJSON("sessionObj");if(void 0===i||null===i||""===i)d.session.setJSON("sessionObj",a);else{var s=i.url,c=i.data,m=i.time,f=1e3;if(c===a.data&&a.time-m<f&&s===a.url){var p="数据正在处理，请勿重复提交";return console.warn("[".concat(s,"]: ")+p),Promise.reject(new Error(p))}d.session.setJSON("sessionObj",a)}}return e}),(function(e){console.log(e),Promise.reject(e)})),b.interceptors.response.use((function(e){var t=e.data.code||200,r=e.data.msg,n=c[t]||c["default"];return"blob"===e.request.responseType||"arraybuffer"===e.request.responseType?e.data:401===t?(p.show||(p.show=!0,i["MessageBox"].confirm("登录状态已过期，您可以继续留在该页面，或者重新登录","系统提示",{confirmButtonText:"重新登录",cancelButtonText:"取消",type:"warning"}).then((function(){p.show=!1,s["a"].dispatch("LogOut").then((function(){location.href="/index"}))})).catch((function(){p.show=!1}))),Promise.reject("无效的会话，或者会话已过期，请重新登录。")):500===t?(Object(i["Message"])({message:n,type:"error"}),Promise.reject(new Error(n))):601===t?(Object(i["Message"])({message:n,type:"warning"}),Promise.reject("error")):200!==t?(i["Notification"].error({title:r}),Promise.reject("error")):e.data}),(function(e){console.log("err"+e);var t=e.message;return"Network Error"==t?t="后端接口连接异常":t.includes("timeout")?t="系统接口请求超时":t.includes("Request failed with status code")&&(t="系统接口"+t.substr(t.length-3)+"异常"),Object(i["Message"])({message:t,type:"error",duration:5e3}),Promise.reject(e)}));t["a"]=b},d3d1:function(e,t,r){"use strict";r.d(t,"d",(function(){return a})),r.d(t,"f",(function(){return i})),r.d(t,"g",(function(){return s})),r.d(t,"a",(function(){return l})),r.d(t,"b",(function(){return c})),r.d(t,"c",(function(){return u})),r.d(t,"e",(function(){return m}));var n=r("b775"),o="/person/employee";function a(e){return Object(n["a"])({url:o+"/list",method:"post",data:e})}function i(e){return Object(n["a"])({url:o+"/save",method:"post",data:e})}function s(e){return Object(n["a"])({url:o+"/update",method:"post",data:e})}function l(e){return Object(n["a"])({url:o+"/deactivate",method:"post",data:{id:e}})}function c(e){return Object(n["a"])({url:o+"/detail",method:"get",params:{uid:e}})}function u(e){return Object(n["a"])({url:o+"/editbtn",method:"get",params:{uid:e}})}function m(e){return Object(n["a"])({url:o+"/resetPwd",method:"post",data:e})}},e0bd:function(e,t,r){"use strict";r.r(t);var n=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{attrs:{id:"information"}},[r("el-breadcrumb",{attrs:{separator:"/"}},[r("el-breadcrumb-item",[e._v("首页")]),r("el-breadcrumb-item",[e._v("个人中心")]),r("el-breadcrumb-item",[e._v("个人资料")])],1),r("br"),e._m(0),r("br"),r("br"),r("el-form",{ref:"informationForm",staticClass:"demo-ruleForm",attrs:{model:e.informationForm,rules:e.rules,"label-width":"100px"}},[r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"}},[r("el-upload",{staticClass:"avatar-uploader",attrs:{action:"{{BaseApi}}/person/employee/uploadImg","show-file-list":!1,"on-success":e.handleAvatarSuccess,"before-upload":e.beforeAvatarUpload}},[e.imageUrl?r("img",{staticClass:"avatar",attrs:{src:e.BaseApi+e.imageUrl}}):r("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)],1)],1),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"},attrs:{label:"电话号码：",prop:"username"}},[r("el-input",{attrs:{max:"11"},model:{value:e.informationForm.username,callback:function(t){e.$set(e.informationForm,"username",t)},expression:"informationForm.username"}})],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"},attrs:{label:"姓名：",prop:"nickName"}},[r("el-input",{model:{value:e.informationForm.nickName,callback:function(t){e.$set(e.informationForm,"nickName",t)},expression:"informationForm.nickName"}})],1)],1)],1),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"},attrs:{label:"身份证号：",prop:"idCard"}},[r("el-input",{model:{value:e.informationForm.idCard,callback:function(t){e.$set(e.informationForm,"idCard",t)},expression:"informationForm.idCard"}})],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"},attrs:{label:"年龄："}},[r("el-input",{attrs:{type:"number",min:"18",max:"70"},model:{value:e.informationForm.age,callback:function(t){e.$set(e.informationForm,"age",t)},expression:"informationForm.age"}})],1)],1)],1),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"},attrs:{label:"所属部门："}},[r("el-select",{staticStyle:{width:"200px"},attrs:{disabled:"",placeholder:"请选择部门",filterable:"",clearable:""},on:{change:function(t){return e.$forceUpdate()}},model:{value:e.informationForm.deptId,callback:function(t){e.$set(e.informationForm,"deptId",t)},expression:"informationForm.deptId"}},e._l(e.options,(function(e){return r("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"},attrs:{label:"住址："}},[r("el-input",{attrs:{type:"text"},model:{value:e.informationForm.address,callback:function(t){e.$set(e.informationForm,"address",t)},expression:"informationForm.address"}})],1)],1)],1),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"},attrs:{label:"备注："}},[r("el-input",{attrs:{type:"textarea",cols:"50",rows:"3"},model:{value:e.informationForm.info,callback:function(t){e.$set(e.informationForm,"info",t)},expression:"informationForm.info"}})],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"40%"},attrs:{label:"性别："}},[r("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择性别",clearable:""},on:{change:function(t){return e.$forceUpdate()}},model:{value:e.informationForm.sex,callback:function(t){e.$set(e.informationForm,"sex",t)},expression:"informationForm.sex"}},[r("el-option",{attrs:{label:"女",value:"0"}}),r("el-option",{attrs:{label:"男",value:"1"}})],1)],1)],1)],1),r("el-form-item",[r("el-button",{staticStyle:{"font-size":"18px"},attrs:{type:"primary"},on:{click:function(t){return e.submitInformationForm("informationForm")}}},[r("i",{staticClass:"iconfont icon-r-yes",staticStyle:{"font-size":"18px"}}),e._v("提交 ")]),r("el-button",{staticStyle:{"font-size":"18px"},on:{click:function(t){return e.resetForm("informationForm")}}},[r("i",{staticClass:"iconfont icon-r-refresh",staticStyle:{"font-size":"18px"}}),e._v("重置 ")])],1)],1)],1)},o=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("i",{staticClass:"iconfont icon-r-paper",staticStyle:{"font-size":"32px"}},[r("b",{staticStyle:{"font-size":"28px"}},[e._v("完善个人资料")])])}],a=r("5530"),i=r("16c7"),s=r("e2a7"),l=r("e28e"),c=r("d3d1"),u=r("5f87"),m={data:function(){return{BaseApi:this.$store.state.BaseApi,loginEid:Object(u["c"])().id,informationForm:{},imageUrl:"",options:[],rules:{username:[{required:!0,message:"手机号不能为空",trigger:"blur"}],nickName:[{required:!0,message:"姓名不能为空",trigger:"blur"}],idCard:[{required:!0,message:"身份证号不能为空",trigger:"blur"}]}}},methods:{deptAll:function(){var e=this;Object(i["b"])({}).then((function(t){200===t.code&&(e.options=t.data)}))},init:function(){var e=this;Object(l["b"])().then((function(t){200===t.code&&(e.informationForm=Object(a["a"])({},t.data),e.imageUrl=e.informationForm.headImg)}))},submitInformationForm:function(e){var t=this;this.$refs[e].validate((function(e){e&&Object(c["g"])(t.informationForm).then((function(e){200===e.code&&Object(s["a"])("更新成功"),t.init()}))}))},resetForm:function(e){this.$refs[e].resetFields(),this.init()},handleAvatarSuccess:function(e){this.informationForm.headImg=e.url,this.imageUrl=e.url},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type||"image/png"===e.type,r=e.size/1024/1024<3;return t||this.$message.error("上传头像图片只能是 JPG或PNG 格式!"),r||this.$message.error("上传头像图片大小不能超过 3MB!"),t&&r}},mounted:function(){this.imageUrl="",this.deptAll(),this.init()}},f=m,d=r("2877"),p=Object(d["a"])(f,n,o,!1,null,null,null);t["default"]=p.exports},e28e:function(e,t,r){"use strict";r.d(t,"b",(function(){return a})),r.d(t,"a",(function(){return i}));var n=r("b775"),o="/person";function a(){return Object(n["a"])({url:o+"/information",method:"get"})}function i(e){return Object(n["a"])({url:o+"/editPwd",method:"post",data:e})}},e2a7:function(e,t,r){"use strict";r.d(t,"a",(function(){return o}));var n=r("5c96");function o(e,t){switch(t){case"warning":n["Message"].warning(e);break;case"error":n["Message"].error({message:e,duration:5e3,showClose:!0});break;case"info":n["Message"].info(e);break;default:n["Message"].success(e)}}}}]);