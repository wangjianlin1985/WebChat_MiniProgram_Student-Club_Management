import { config, http } from '../../utils/http.js'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    config: config,
    topTips: false,
    hide: false,
    clazzs: [],
    index: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getClazz();
  },

  getClazz() {
    var _this = this;
    http.get('api/clazz').then(res => {
      _this.setData({
        clazzs: res.data.data
      });
    });
  },

  bindPickerChange(e) {
    this.setData({
      index: e.detail.value
    });
  },

  /**
   * 提交保存
   */
  formsubmit(e) {
    //console.log(e);
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
    var { name } = e.detail.value;
    if (name == '' || name.length < 1) {
      _this.showTips('姓名不能为空');
      return
    }
    http.post('api/vreg', e.detail.value, '注册中').then(res => {
      //var student = res.data.data;
      //wx.setStorageSync('member', res.data.data);
      wx.showToast({
        title: '注册成功',
      })
      setTimeout(() => {
        wx.navigateBack();
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