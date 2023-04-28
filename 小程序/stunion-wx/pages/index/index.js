import { config, http } from "../../utils/http"

Page({
  data: {
    config: config,
    newsList: []
  },

  onLoad() {
    this.getArticlesTop5();
  },

  //加载新闻
  getArticlesTop5() {
    var _this = this;
    http.get('api/getArticlesTop5').then(res => {
      _this.setData({
        newsList: res.data.data
      });
    });
  },

  rmst() {
    wx.switchTab({
      url: '/pages/stunion/index',
    })
  },

  xxyd() {
    wx.navigateTo({
      url: '/pages/study/index',
    })
  },

  sthd() {
    wx.navigateTo({
      url: '/pages/activity/index',
    })
  },

  stfc() {
    wx.navigateTo({
      url: '/pages/scenery/index',
    })
  },

  hdxf() {
    var student = wx.getStorageSync('student');
    if (!student) {
      wx.showModal({
        title: '登录提示',
        content: '登录后查看活动学分，是否现在登录?',
        success(res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '/pages/login/index',
            })
          }
        }
      })
      return
    }
    wx.navigateTo({
      url: '/pages/person/credit',
    })
  },

  pdtd() {
    wx.navigateTo({
      url: '/pages/student/index',
    })
  },



})
