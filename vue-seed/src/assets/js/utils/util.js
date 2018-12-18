import formatter from './format'

/**
 * 时间格式化工具
 * @type {{parseTime: timeUtil.parseTime, formatTime: timeUtil.formatTime}}
 * @Usage
 * 列表中的转换 parseTime: {{scope.row.created | parseTime('{y}-{m}-{d} {h}:{i}')}}
 * 详细页面 parseTime: created=parseTime(created, '{y}-{m}-{d} {h}:{i}')
 */
var timeUtil = {
  parseTime: function (time, cFormat) {
    if (arguments.length === 0) {
      return null
    }
    const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
    let date
    if (typeof time === 'object') {
      date = time
    } else {
      if (('' + time).length === 10) time = parseInt(time) * 1000
      date = new Date(time)
    }
    const formatObj = {
      y: date.getFullYear(),
      m: date.getMonth() + 1,
      d: date.getDate(),
      h: date.getHours(),
      i: date.getMinutes(),
      s: date.getSeconds(),
      a: date.getDay()
    }
    const timeStr = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
      let value = formatObj[key]
      if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1]
      if (result.length > 0 && value < 10) {
        value = '0' + value
      }
      return value || 0
    })
    return timeStr
  },
  formatTime: function (time, option) {
    time = +time * 1000
    const d = new Date(time)
    const now = Date.now()

    const diff = (now - d) / 1000

    if (diff < 30) {
      return '刚刚'
    } else if (diff < 3600) { // less 1 hour
      return Math.ceil(diff / 60) + '分钟前'
    } else if (diff < 3600 * 24) {
      return Math.ceil(diff / 3600) + '小时前'
    } else if (diff < 3600 * 24 * 2) {
      return '1天前'
    }
    if (option) {
      return this.parseTime(time, option)
    } else {
      return d.getMonth() + 1 + '月' + d.getDate() + '日' + d.getHours() + '时' + d.getMinutes() + '分'
    }
  },
  // 指定时间到当前时间之间的月数
  monthsDateToNow: function (time) {
    var moment = require('moment')
    var beginMoment = moment(formatter.formatterDate(time, '{y}-{m}-{d}'))
    var endMoment = moment(formatter.formatterDate((new Date()).getTime(), '{y}-{m}-{d}'))
    var duration = moment.duration(endMoment.diff(beginMoment))
    // 向上取整
    return Math.ceil(duration.asMonths())
  },
  // 指定时间到指定时间之间的月数
  monthsDateToDate: function (beginTime, endTime) {
    var moment = require('moment')
    var beginMoment = moment(formatter.formatterDate(beginTime, '{y}-{m}-{d}'))
    var endMoment = moment(formatter.formatterDate(endTime, '{y}-{m}-{d}'))
    var duration = moment.duration(endMoment.diff(beginMoment))
    // 向上取整
    return Math.ceil(duration.asMonths())
  }
}

/**
 * Url 解析
 * @type {{urlParse: URLUtil.urlParse, paramsJson: URLUtil.paramsJson}}
 */
var urlUtil = {
  /**
     * [解析url参数]
     * @example ?id=12345&a=1
     * @return Object {id:12345,a:1}
     */
  urlParse: function () {
    let url = decodeURIComponent(window.location.search)
    let obj = {}
    let reg = /[?&][^?&]+=[^?&]+/g
    // ['?id=12345','a=1']
    let arr = url.match(reg)
    if (arr) {
      arr.forEach((item) => {
        let tempArr = item.substring(1).split('=')
        let key = decodeURIComponent(tempArr[0])
        let value = decodeURIComponent(tempArr[1])
        obj[key] = value
      })
    }
    return obj
  },
  /**
     * [解析url参数]
     * @example ?id=12345&a=1
     * @returns Object {id: "12345", a: "1"}
     */
  paramsJson: function () {
    var url = location.search
    var paramArray = []
    if (url.indexOf('?') !== -1) {
      var str = url.substr(1)
      var strs = str.split('&')
      for (var i = 0; i < strs.length; i++) {
        paramArray[strs[i].split('=')[0]] = unescape(strs[i].split('=')[1])
      }
    }
    return paramArray
  }
}

/**
 * 差异计算，返回差异结果
 * eg: smart({a:1, b:0}, {a:1, b:1}) -> {a:1, aIsDiff: false, b:1, bIsDiff: true}
 * @type {{smart: (function(*, *))}}
 */
const computeDiff = {
  // todo 显示对比值
  stayCurrent: function (previous, current) {
    var orderDiff = {}
    for (let field in previous) {
      // 跳过申请单附件
      if (field === 'caAppAnnexDTOs') continue
      // 跳过征信报告附件
      if (field === 'creditAnnexInfo') continue
      // 跳过三方检测结果
      if (field === 'riskInfo') continue
      // 跳过审批历史
      if (field === 'caAppApprovals') continue
      // 跳过容联数据
      if (field === 'caRonglianDialoutList') continue
      // 跳过carGpsInfoList
      if (field === 'carGpsInfoList') continue
      // 判断是否为对象
      if (previous[field] instanceof Object) {
        orderDiff[field] = computeDiff.stayCurrent(previous[field], current[field])
      } else {
        if (current === undefined || previous === undefined) {
          continue
        }
        // 设置结果值，这里去当前值
        orderDiff[field] = current[field]
        if (String(previous[field]) !== String(current[field])) {
          orderDiff[field + 'IsDiff'] = true
          // 如果有差异，则储存差异值
          orderDiff[field + 'Diff'] = previous[field]
        } else {
          orderDiff[field + 'IsDiff'] = false
        }
      }
    }
    return orderDiff
  },
  stayPrevious: function (previous, current) {
    var orderDiff = {}
    for (let field in previous) {
      // 跳过申请单附件
      if (field === 'caAppAnnexDTOs') continue
      // 跳过三方检测结果
      if (field === 'riskInfo') continue
      // 跳过审批历史
      if (field === 'caAppApprovals') continue
      // 判断是否为对象
      if (previous[field] instanceof Object) {
        orderDiff[field] = computeDiff.stayPrevious(previous[field], current[field])
      } else {
        // 设置返回值，这里取之前值
        orderDiff[field] = previous[field]
        if (String(previous[field]) !== String(current[field])) {
          orderDiff[field + 'IsDiff'] = true
          // 如果有差异，则储存差异值
          orderDiff[field + 'Diff'] = current[field]
        } else {
          orderDiff[field + 'IsDiff'] = false
        }
      }
    }
    return orderDiff
  }
}

/**
 * 数字工具
 * @param val
 * @returns {boolean}
 */
const numberUtil = {
  isNumber: function (val) {
    if (val === '' || val === undefined || val === null || isNaN(val)) {
      return false
    }
    return true
  }
}

export {timeUtil, urlUtil, computeDiff, numberUtil}
