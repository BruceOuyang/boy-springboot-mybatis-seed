import Vue from 'vue'
import Vuex from 'vuex'
import dictionary from './store.dictionary'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    dictionary
  }
})
export default store
