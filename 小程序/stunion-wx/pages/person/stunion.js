import { config, http } from "../../utils/http"
Page({


  data: {

  },

  onLoad: function (options) {
    this.stunionApplyFind();
  },

  stunionApplyFind() {
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
    http.get('api/stunion/apply/find', {
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
    this.stunionApplyFind();
  },

  stunion(e) {
    var { item } = e.currentTarget.dataset;
    //console.log(item);
    if (item.status == 1) {
      wx.navigateTo({
        url: '/pages/stunion/student?id=' + item.stunionId,
      })
    } else {
      this.delTap(e);
    }
  },

  delTap(e) {
    var _this = this;
    var { item } = e.currentTarget.dataset;
    wx.showModal({
      title: '删除确认',
      content: '是否确认删除社团申请记录?',
      success(res) {
        if (res.confirm) {
          http.post('api/stunion/apply/del', { id: item.id }, '删除中').then(res => {
            wx.showToast({
              title: '删除成功!',
            })
            setTimeout(() => {
              _this.stunionApplyFind();
            }, 1000);
          });
        }
      }
    })
  },

})