/**
 * 两位小数验证
 * @type {RegExp}
 */
var regMoneyNumber = /^\d+(\.\d{1,2})?$/
var validateMoneyNumber = (rule, value, callback) => {
  if (!regMoneyNumber.test(value)) {
    callback(new Error('请输入最多两位小数的数值'))
  } else {
    callback()
  }
}
/**
 * 首付比验证
 * @param rule
 * @param value
 * @param callback
 */
var validateInitScaleMin = (rule, value, callback) => {
  if (value < 0) {
    callback(new Error('最小值不能小于0'))
  } else {
    callback()
  }
}
/**
 * 最大首付比验证
 * @param rule
 * @param value
 * @param callback
 */
var validateInitScaleMax = (rule, value, callback) => {
  if (value > 100) {
    callback(new Error('最大值不能大于100'))
  } else {
    callback()
  }
}
/**
 * 最大贷款金额验证
 * @param rule
 * @param value
 * @param callback
 */
var validateLoanAmountMax = (rule, value, callback) => {
  if (value > 10000000) {
    callback(new Error('最大值不能大于一千万'))
  } else {
    callback()
  }
}
/**
 * 平台返佣值验证
 * @param rule
 * @param value
 * @param callback
 */
var validateSerFinRebateRates = (rule, value, callback) => {
  if (this.form.isFreeRebate === '0') {
    var index = rule.field.replace('serFinRates.', '')

    if (!regMoneyNumber.test(this.form.serFinRates[index].rate2)) {
      callback(new Error('请输入最多两位小数的数值'))
    } else if (isNaN(this.form.serFinRates[index].rate2)) {
      callback(new Error('输入格式错误'))
    } else if (this.form.serFinRates[index].rate2 * 1 > this.form.serFinRates[index].rate1.replace('%', '') * 1) {
      callback(new Error('返佣率过大'))
    } else {
      callback()
    }
  } else {
    callback()
  }
}
/**
 * 开始日期验证
 * @param rule
 * @param value
 * @param callback
 */
var beginDateValid = (rule, value, callback) => {
  if (!value || !this.form.endDate || this.form.endDate >= value) {
    this.$refs.addForm.clearValidate(['endDate'])
    callback()
  } else {
    callback(new Error('生效日期大于失效日期'))
  }
}
/**
 * 结束日期验证
 * @param rule
 * @param value
 * @param callback
 */
var endDateValid = (rule, value, callback) => {
  if (!value || !this.form.beginDate || this.form.beginDate <= value) {
    this.$refs.addForm.clearValidate(['beginDate'])
    callback()
  } else {
    callback(new Error('生效日期大于失效日期'))
  }
}
/**
 * 基础平台费验证
 * @param rule
 * @param value
 * @param callback
 */
var baseSerFinRateValid = (rule, value, callback) => {
  if (this.form.isFreeRebate === '1') {
    if (this.form.baseSerFinRate >= 100) {
      callback(new Error('基础平台费不能大于100'))
    }
  }
  callback()
}
/**
 * 返佣系数验证
 * @param rule
 * @param value
 * @param callback
 */
var freeRebateRateValid = (rule, value, callback) => {
  if (this.form.isFreeRebate === '1') {
    if (this.form.freeRebateRate >= 1) {
      callback(new Error('返佣系数不能大于1'))
    }
  }
  callback()
}
/**
 * 首付比例校验
 * @param rule
 * @param value
 * @param callback
 */
var initScaleValid = (rule, value, callback) => {
  if (!value || (value >= 0 && value <= 100)) {
    callback()
  } else {
    callback(new Error('首付比值应该在0~100之间'))
  }
}

/**
 * 贷款最大金额一千万
 * @param rule
 * @param value
 * @param callback
 */
var loanAmountValid = (rule, value, callback) => {
  if (!value || (value >= 0 && value <= 10000000)) {
    callback()
  } else {
    callback(new Error('贷款最大金额为一千万'))
  }
}

export {
  validateMoneyNumber,
  validateInitScaleMin,
  validateInitScaleMax,
  validateLoanAmountMax,
  validateSerFinRebateRates,
  beginDateValid,
  endDateValid,
  baseSerFinRateValid,
  freeRebateRateValid,
  initScaleValid,
  loanAmountValid
}
