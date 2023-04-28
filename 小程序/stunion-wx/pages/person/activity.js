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

  delTap(e) {
    var _this = this;
    var { item } = e.currentTarget.dataset;
    wx.showModal({
      title: '删除确认',
      content: '是否确认删除申请记录?',
      success(res) {
        if (res.confirm) {
          http.post('api/activity/apply/del', { id: item.id }, '删除中').then(res => {
            wx.showToast({
              title: '删除成功!',
            })
            setTimeout(() => {
              _this.activityApplyFind();
            }, 1000);
          });
        }
      }
    })
  },

})