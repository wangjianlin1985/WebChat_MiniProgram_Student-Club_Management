import { config, http } from '../../utils/http.js'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    config: config,
    topTips: false,
    hide: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var student = wx.getStorageSync('student');
    if (student) {
      this.setData({
        student: student
      });
    }
  },

  /**
   * 提交保存
   */
  formsubmit(e) {
    //console.log(e);
    var _this = this;
    var { name } = e.detail.value;
    if (name == '' || name.length < 1) {
      _this.showTips('姓名不能为空');
      return
    }
    var { phone } = e.detail.value;
    if (phone == '' || phone.length < 1) {
      _this.showTips('手机号不能为空');
      return
    }
    http.post('api/student/update', e.detail.value, '保存中').then(res => {
      //var student = res.data.data;
      wx.showToast({
        title: '保存成功',
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