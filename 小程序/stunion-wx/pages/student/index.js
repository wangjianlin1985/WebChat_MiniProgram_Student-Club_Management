import { config, http } from "../../utils/http.js";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    config: config,
    lists: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.students();
  },

  //加载学生
  students() {
    var _this = this;
    http.get('api/students').then(res => {
      _this.setData({
        lists: res.data.data
      });
      wx.stopPullDownRefresh({
        success: (res) => { },
      })
    });
  },

  onPullDownRefresh() {
    this.students();
  },

})