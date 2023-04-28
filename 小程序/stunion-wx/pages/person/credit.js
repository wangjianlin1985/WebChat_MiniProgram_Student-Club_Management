import { config, http } from "../../utils/http"
Page({


  data: {

  },

  onLoad: function (options) {
    this.activityApplyFind();
  },

  activityApplyFind() {
    var student = wx.getStorageSync('student');
    if (!student) {
      wx.showModal({
        title: '登录提示',
        content: '登录后查询，是否现在登录?',
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
    var _this = this;
    http.get('api/activity/apply/find', {
      id: student.id
    }, '加载中').then(res => {
      _this.setData({
        lists: res.data.data
      });
      wx.stopPullDownRefresh({
        success: (res) => { },
      });
    });
  },

  onPullDownRefresh: function () {
    this.activityApplyFind();
  },

})