import Vue from 'vue'
import store from '../store'

/**
 * @export 自定义指令
 */
export function directive() {
  Vue.directive('permit', {
    bind(el, binding) {
      if(!window.vm.$store.getters.getPermission.includes(binding.value)){
        el.style.display = 'none';
        el.dataset['isRemove'] = '1';
      }
    },
    inserted: function (el) {
      if (el.dataset['isRemove'] === '1') {
        el.parentNode.removeChild(el);
      }
    }
  })
}

