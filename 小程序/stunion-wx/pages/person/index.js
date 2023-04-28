import { config, http } from "../../utils/http.js";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    config: config,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var student = wx.getStorageSync('student');
    if (student) {
      this.setData({
        student: student
      });
    }
  },

  login() {
    wx.navigateTo({
      url: '/pages/login/index',
    })
  },


})