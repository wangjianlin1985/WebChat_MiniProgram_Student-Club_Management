import { config, http } from "../../utils/http"
Page({


  data: {

  },

  onLoad: function (options) {
    this.activityCollectFind();
  },

  activityCollectFind() {
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
    http.get('api/activity/collect/find', {
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
    this.activityCollectFind();
  },

  delTap(e) {
    var _this = this;
    var { item } = e.currentTarget.dataset;
    wx.showModal({
      title: '删除确认',
      content: '是否确认删除收藏记录?',
      success(res) {
        if (res.confirm) {
          http.post('api/activity/collect/del', { id: item.id }, '删除中').then(res => {
            wx.showToast({
              title: '删除成功!',
            })
            setTimeout(() => {
              _this.activityCollectFind();
            }, 1000);
          });
        }
      }
    })
  },

})