import { config, http } from "../../utils/http";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    config: config
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var id = options.id;
    var _this = this;
    http.get('api/article', { id: id }).then(res => {
      _this.setData({
        notice: res.data.data
      });
    });
  },


})