const { config, http } = require('../../utils/http');


Page({

  /**
   * 页面的初始数据
   */
  data: {
    config: config,
    id: 0,
    study: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var id = options.id;
    var _this = this;
    _this.setData({
      id: id
    });
    this.study();

  },

  study() {
    var _this = this;
    http.get('api/study', { id: _this.data.id }).then(res => {
      _this.setData({
        study: res.data.data
      });
      wx.stopPullDownRefresh({
        success: (res) => { },
      })
    });
  },

  onPullDownRefresh() {
    this.study();
  },


})