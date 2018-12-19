import {timeUtil} from './util.js'

let formatter = {}

/**
 * 时间格式化
 * @param value 时间
 * @param cFormat 格式（默认 {y}-{m}-{d} {h}:{i}:{s})
 * @returns {*}
 */
formatter.formatterDate = function (value, cFormat) {
  if (value === undefined || value === null) {
    return null
  }
  if (cFormat) {
    return timeUtil.parseTime(value, cFormat)
  }
  return timeUtil.parseTime(value, '{y}-{m}-{d} {h}:{i}:{s}')
}

/**
 * 格式化银行卡号（标准银行卡号为16位或19位数字）
 * @param cardNo 入参
 * @returns {*}
 * 输出格式：每四位一个分隔符 ' - ' eg：6666 - 6666 - 6666 - 6666
 */
formatter.formatterCardNo = function (cardNo) {
  if (cardNo === undefined || cardNo == null || cardNo.trim().length < 4) return cardNo

  let result = cardNo.replace(/(\w{4})/g, '$1 - ').trim()
  return cardNo.length % 4 !== 0 ? result : result.substr(0, result.lastIndexOf(' -'))
}

/**
 * 格式化身份证号（标准身份证号为15位或18位数字或数字加字母的字符串）
 * @param idNo 入参
 * @returns {*}
 * 输出格式：
 *  1）15位卡号：110101 - 90-03-07 - 001
 *  2）18位卡号：110101 - 1990-03-07 - 1313
 *  3）其他位数：每四位一个分隔符 eg：6666 - 6666 - 6666 - 6666
 */
formatter.formatterIdNo = function (idNo) {
  if (idNo === undefined || idNo == null || idNo.trim().length < 4) return idNo
  if (idNo.trim().length === 15) {
    return idNo.replace(/(\w{6})(\w{2})(\w{2})(\w{2})(\w{3})/g, '$1 - $2-$3-$4 - $5')
  } else if (idNo.trim().length === 18) {
    return idNo.trim().replace(/(\w{6})(\w{4})(\w{2})(\w{2})(\w{4})/g, '$1 - $2-$3-$4 - $5')
  } else {
    let result = idNo.trim().replace(/(\w{4})/g, '$1 - ')
    return idNo.length % 4 !== 0 ? result : result.substr(0, result.lastIndexOf(' -'))
  }
}

/**
 * 格式化手机号码（标准的手机号码为11位数字）
 * @param mobile
 * @returns {string}
 * 输出格式：
 *  1）11位卡号：138 - 1111 - 1111
 *  2）其他位数：每四位一个分隔符 eg：6666 - 6666 - 6666 - 6666
 */
formatter.formatterMobile = function (mobile) {
  if (mobile === undefined || mobile == null || mobile.trim().length < 4) return mobile
  if (mobile.trim().length === 11) {
    return mobile.trim().replace(/(\d{3})(\d{4})(\d{4})/g, '$1 - $2 - $3')
  } else {
    let result = mobile.trim().replace(/(\w{4})/g, '$1 - ')
    return mobile.length % 4 !== 0 ? result : result.substr(0, result.lastIndexOf(' -'))
  }
}

export default formatter
