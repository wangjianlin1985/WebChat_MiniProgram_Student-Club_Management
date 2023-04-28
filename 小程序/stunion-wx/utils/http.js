import {config} from 'config.js'

const get = (url,data,loading) => {
  let get_header = {
    'content-type': 'application/json'
  }
  let post_header={
    'content-type': 'application/x-www-form-urlencoded'
  }
  return new Promise((resolve, reject) => {
    if(loading){
      //console.log(typeof(loading));
      if(typeof(loading)=='string'){
        wx.showLoading({
          title: loading,
          mask: true
        })
      }else{
        wx.showLoading({
          title: "加载中",
          mask: true
        })
      }
    }
    
    wx.request({
      url: config.url+"/"+url,
      data: data,
      header: get_header,
      method: 'GET',
      success: (res) => {
        //console.log(result);
        //200: 服务端业务处理正常结束
        if (res.statusCode === 200) {
          if(res.data.code == 1){//请求成功
            resolve(res)
          }else{
            //reject(res);
            wx.showToast({
              title: res.data.msg,
              icon: "none",
              duration: 2000
            })
          }
        }else{
          _show_err(345);
          //reject(res)
        }
      },
      fail: (err) => {
        _show_err(345);
        //reject(err);
      },
      complete: () => {
        if (loading) {
          wx.hideLoading();
        }
      }
    })
  });
}


const post = (url,data,loading) => {
  let get_header = {
    'content-type': 'application/json'
  }
  let post_header={
    'content-type': 'application/x-www-form-urlencoded'
  }
  return new Promise((resolve, reject) => {
    if(loading){
      //console.log(typeof(loading));
      if(typeof(loading)=='string'){
        wx.showLoading({
          title: loading,
          mask: true
        })
      }else{
        wx.showLoading({
          title: "提交中",
          mask: true
        })
      }
    }
    
    wx.request({
      url: config.url+"/"+url,
      data: data,
      header: post_header,
      method: 'POST',
      success: (res) => {
        //console.log(result);
        //200: 服务端业务处理正常结束
        if (res.statusCode === 200) {
          if(res.data.code == 1){//请求成功
            resolve(res)
          }else{
            //reject(res);
            wx.showToast({
              title: res.data.msg,
              icon: "none",
              duration: 2000
            })
          }
        }else{
          _show_err(345);
          //reject(res)
        }
      },
      fail: (err) => {
        _show_err(345);
        //reject(err);
      },
      complete: () => {
        if (loading) {
          wx.hideLoading();
        }
      }
    })
  });
}

const _show_err = (err_code) =>{
  if(!err_code){
    err_code = 0
  }
  wx.showToast({
    title: tips[err_code],
    icon: "error",
    duration: 2000
  })
}

const tips = {
  0:'未知错误',
  345 :'请求出现错误'
}

const http ={
  get: get,
  post: post
}

export {
  config,
  http
}