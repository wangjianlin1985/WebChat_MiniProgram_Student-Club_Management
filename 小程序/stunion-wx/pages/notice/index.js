import { config, http } from "../../utils/http";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    config: config,
    noticeList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getArticles();
  },

  //加载公告
  getArticles() {
    var _this = this;
    http.get('api/getArticles').then(res => {
      _this.setData({
        noticeList: res.data.data
      });
      wx.stopPullDownRefresh({
        success: (res) => { },
      })
    });
  },

  onPullDownRefresh() {
    this.getArticles();
  },

})