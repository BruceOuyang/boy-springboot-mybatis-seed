import Vue from 'vue'

// 字典参数
export default {
  namespaced: true,
  mutations: {
    putStateElement (state, item) {
      if (!state[item.typeName]) {
        state[item.typeName] = []
      }
      state[item.typeName].push(item)
    }
  },
  actions: {
    fetchData ({commit}) {
      return new Vue({}).$http.get('/api/boss-fps/dictionary/queryAll').then(data => {
        for (let row of data) {
          let typeName = null
          data.forEach(item => {
            if (String(row.type) === String(item.value) && String('0') === String(item.type)) {
              typeName = item.name
            }
          })
          if (typeName !== null) {
            row.typeName = typeName
            commit('putStateElement', row)
          }
        }
      })
    }
  }
}
