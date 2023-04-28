import { config, http } from '../../utils/http.js'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    topTips: false,
    hide: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  reg() {
    wx.navigateTo({
      url: '/pages/reg/index',
    })
  },

  /**
   * 登录
   */
  formsubmit(e) {
    console.log(e);
    var _this = this;
    var { sno } = e.detail.value;
    if (sno == '' || sno.length < 1) {
      _this.showTips('学号不能为空');
      return
    }
    var { password } = e.detail.value;
    if (password == '' || password.length < 1) {
      _this.showTips('密码不能为空');
      return
    }
    http.get('api/vlogin', e.detail.value, '登录中').then(res => {
      wx.setStorageSync('student', res.data.data);
      wx.showToast({
        title: '登录成功',
      })
      setTimeout(() => {
        wx.switchTab({
          url: '/pages/person/index',
        })
      }, 1000);
    });
  },

  showTips(error) {
    this.setData({
      topTips: true,
      error: error
    });
    setTimeout(() => {
      this.setData({
        topTips: false,
        hide: false,
      });
    }, 1000);
  }


})