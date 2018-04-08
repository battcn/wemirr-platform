const verificat={
  username:function (name) {/* 用户名验证 */
    if(name===''||!/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/.test(name)){
      console.log("手机号格式错误");
      return false;
    }
    return true;
  },
  password:function (pw) {
    if(pw===''||!/^[a-zA-Z0-9_]{6,15}$/u.test(pw)){
      console.log("密码格式错误");
      return false;
    }
    return true;
  },
  email:function (email) {
    if(!/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(email)){
      console.log("邮箱格式不正确");
      return false;
    }
    return true;
  },
  same:function (name,same,newsame) {
    if(same!==newsame){
      console.log("两次输入的"+name+"不一致");
      return false;
    }
    return true;
  },
  trim:function(str){
     if(str.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'')===''){
       console.log("请输入验证码");
       return false;
     }
     return true;
  }
}

export {
  verificat
}

